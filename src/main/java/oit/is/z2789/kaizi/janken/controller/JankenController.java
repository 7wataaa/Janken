package oit.is.z2789.kaizi.janken.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/janken")
public class JankenController {

  @PostMapping
  String janken(@RequestParam String user_id, ModelMap model) {
    model.addAttribute("user_id", user_id);

    return "janken.html";
  }
}
