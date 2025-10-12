import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JankenController {

  @GetMapping
  String janken() {
    return "janken.html";
  }
}
