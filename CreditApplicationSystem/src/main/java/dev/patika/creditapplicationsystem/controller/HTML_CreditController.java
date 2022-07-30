package dev.patika.creditapplicationsystem.controller;

import dev.patika.creditapplicationsystem.model.Credit;
import dev.patika.creditapplicationsystem.model.User;
import dev.patika.creditapplicationsystem.service.CreditService;
import dev.patika.creditapplicationsystem.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class HTML_CreditController {

    private final CreditService service;
    public HTML_CreditController(CreditService service) {
        this.service = service;
    }

    @GetMapping("/ApplyForCredit")
    //@Transactional(readOnly = true)
    public String askByIdentityNumber(@RequestParam long idCardNumber){
        service.askByIdentityNumber(idCardNumber);
        return "redirect:/users";
    }
}
