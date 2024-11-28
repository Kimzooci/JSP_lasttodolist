package kyungseo.poc.todo.jsp.web.controller;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class ErrorPageController {

    private final MessageSource messageSource;

    // 생성자 주입
    public ErrorPageController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @GetMapping("/403")
    public String accessDenied(Model model) {
        model.addAttribute("error", messageSource.getMessage("error.access.denied", null, null));
        return "error/403";
    }

    @GetMapping("/404")
    public String notFound(Model model) {
        model.addAttribute("error", messageSource.getMessage("error.page.not.found", null, null));
        return "error/404";
    }

    @GetMapping("/500")
    public String internalServerError(Model model) {
        model.addAttribute("error", messageSource.getMessage("error.internal.server", null, null));
        return "error/500";
    }
}
