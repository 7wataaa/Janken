package oit.is.z2789.kaizi.janken.controller;

import java.security.Principal;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import oit.is.z2789.kaizi.janken.model.Entry;
import oit.is.z2789.kaizi.janken.model.MatchMapper;
import oit.is.z2789.kaizi.janken.model.UserMapper;

@Controller
public class JankenController {
  @Autowired
  private Entry entry;

  @Autowired
  private UserMapper user;
  @Autowired
  private MatchMapper matches;

  @GetMapping("/janken")
  String janken(Principal prin, ModelMap model) {
    String user_id = prin.getName();
    model.addAttribute("user_id", user_id);
    this.entry.addUser(user_id);
    model.addAttribute("room", entry);
    model.addAttribute("users", user.selectAllUsers());
    model.addAttribute("matches", matches.selectAllMatches());

    return "janken.html";
  }

  @PostMapping(path = "/janken", params = "user_id")
  String janken(@RequestParam String user_id, ModelMap model) {
    model.addAttribute("user_id", user_id);

    return "janken.html";
  }

  @PostMapping(path = "/janken", params = { "user_id", "hand" })
  String janken(@RequestParam String user_id, @RequestParam Integer hand, ModelMap model) {
    model.addAttribute("user_id", user_id);
    model.addAttribute("room", entry);

    var jankenResult = new HashMap<String, String>();

    enum Hand {
      GU(0, "グー"),
      CHOKI(1, "チョキ"),
      PA(2, "パー");

      private final int value;
      private final String label;

      Hand(int value, String label) {
        this.value = value;
        this.label = label;
      }

      public int getValue() {
        return value;
      }

      @Override
      public String toString() {
        return this.label;
      }

      public String versus(Hand other) {
        if (this == other) {
          return "あいこ";
        } else if ((this == GU && other == CHOKI) ||
            (this == CHOKI && other == PA) ||
            (this == PA && other == GU)) {
          return "勝ち";
        } else {
          return "負け";
        }
      }
    }

    Hand cpuHand = Hand.GU;
    Hand userHand = switch (hand) {
      case 0 -> Hand.GU;
      case 1 -> Hand.CHOKI;
      case 2 -> Hand.PA;
      default -> throw new IllegalArgumentException("Unexpected value: " + hand);
    };

    jankenResult.put("cpu_hand", cpuHand.toString());
    jankenResult.put("user_hand", userHand.toString());
    jankenResult.put("result", userHand.versus(cpuHand));

    model.addAttribute("janken_result", jankenResult);

    return "janken.html";
  }

  @GetMapping("/match")
  String match(@RequestParam Integer id, Principal prin, ModelMap model) {
    model.addAttribute("vsUser", user.selectUserById(id));
    model.addAttribute("userName", prin.getName());

    return "match.html";
  }
}
