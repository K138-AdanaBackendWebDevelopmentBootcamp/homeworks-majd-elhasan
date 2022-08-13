package dev.patika.creditapplicationsystem.controller;

import dev.patika.creditapplicationsystem.exception.NotFoundException;
import dev.patika.creditapplicationsystem.model.User;
import dev.patika.creditapplicationsystem.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/json/users")
public class JSON_UserController {

    private final UserService service;
    public JSON_UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    @Transactional(readOnly = true)
    public ResponseEntity getUsers(@RequestParam(required = false,defaultValue = "databaseId") String sortBy,@RequestParam(required = false,defaultValue = "true") String ascending){
        try {
            if(!(ascending.equalsIgnoreCase("true")||ascending.equalsIgnoreCase("false")))
                throw new IllegalStateException("Unexpected value: " + ascending);
            return ResponseEntity.ok(service.getUsersSorted(sortBy,Boolean.parseBoolean(ascending)));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }


    @GetMapping("/Database_ID/{DatabaseId}")
    @Transactional(readOnly = true)
    public ResponseEntity getUserByDatabaseId(@PathVariable long DatabaseId){
        try {
            return ResponseEntity.ok(service.getUserByDatabaseId(DatabaseId));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @GetMapping("/National_ID/{IdCardNumber}")
    @Transactional(readOnly = true)
    public ResponseEntity getUserByIdentityNumber(@PathVariable long IdCardNumber){
        try {
            return ResponseEntity.ok(service.getUserByIdentityNumber(IdCardNumber));
        }catch(NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }
    @PostMapping
    @Transactional
    public ResponseEntity saveUser(@RequestBody User user){
        try {
            return  ResponseEntity.ok(service.saveUser(user));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }

    }
    @PutMapping("/{databaseId}")
    @Transactional
    public ResponseEntity updateUserByDatabaseId(@PathVariable long databaseId,@RequestBody User user){
        try {
            return ResponseEntity.ok(service.updateUserByDatabaseId(databaseId,user));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{databaseId}")
    @Transactional
    public ResponseEntity deleteUserByDatabaseId(@PathVariable long  databaseId){
        try{
            service.deleteUserByDatabaseId(databaseId);
            return ResponseEntity.ok("User with database Id "+ databaseId +" has been deleted from database");
        }catch(NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
