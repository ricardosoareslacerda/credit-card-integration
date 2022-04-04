package br.com.fiap.creditcardintegration.api;

import org.springframework.web.bind.annotation.RestController;

/**
 * Home redirection to swagger api documentation 
 */
@RestController
public class HomeController {
    public String index() {
        System.out.println("/swagger-ui/index.html");
        return "redirect:/swagger-ui/";
    }
}
