package dev.patika.creditapplicationsystem.controller;

import dev.patika.creditapplicationsystem.model.Credit;
import dev.patika.creditapplicationsystem.service.CreditService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/json/users")
public class JSON_CreditController {
    private final CreditService creditService;

    public JSON_CreditController(CreditService creditService) {
        this.creditService = creditService;
    }

    @GetMapping("/ApplyForCredit/{idCardNumber}")
    //@Transactional(readOnly = true)
    public ResponseEntity askByIdentityNumber(@PathVariable long idCardNumber){
        try {
            return ResponseEntity.ok(creditService.askByIdentityNumber(idCardNumber));
        }catch (Exception e){
            return ResponseEntity.ok().body(e.getMessage());
        }
    }

    @GetMapping("/Credit/{idCardNumber}")
    @Transactional(readOnly = true)
    public ResponseEntity getCreditByUserIdentityNumber(@PathVariable long idCardNumber){
        try {
            return ResponseEntity.ok(creditService.getCreditByUserIdentityNumber(idCardNumber));
        }catch (Exception e){
            return ResponseEntity.ok().body(e.getMessage());
        }

    }
}
