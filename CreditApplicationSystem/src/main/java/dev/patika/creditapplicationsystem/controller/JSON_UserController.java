package dev.patika.creditapplicationsystem.controller;

import dev.patika.creditapplicationsystem.model.User;
import dev.patika.creditapplicationsystem.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/json")
public class JSON_UserController {

    private final UserService iUserService;
    public JSON_UserController(UserService iUserService) {
        this.iUserService = iUserService;
    }

    @GetMapping("/users")
    @Transactional(readOnly = true)
    public ResponseEntity<List<User>> getUsers_JSON(){
        return ResponseEntity.ok(iUserService.getUsers());
    }


    @GetMapping("/users/Database_ID/{DatabaseId}")
    @Transactional(readOnly = true)
    public ResponseEntity<User> getUserByDatabaseId(@PathVariable long DatabaseId){
        return ResponseEntity.ok(iUserService.getUserByDatabaseId(DatabaseId));
    }
    @GetMapping("/users/National_ID/{IdCardNumber}")
    @Transactional(readOnly = true)
    public ResponseEntity<User> getUserByIdentityNumber(@PathVariable long IdCardNumber){
        return ResponseEntity.ok(iUserService.getUserByIdentityNumber(IdCardNumber));
    }
    @PostMapping("/users")
    @Transactional
    public ResponseEntity<User> saveUser(@RequestBody User user){
        return ResponseEntity.ok(iUserService.saveUser(user));
    }
    @PutMapping("/users/{databaseId}")
    @Transactional
    public ResponseEntity<User> updateUserByDatabaseId(@PathVariable long databaseId){
        return ResponseEntity.ok(iUserService.updateUserByDatabaseId(databaseId));
    }

    @DeleteMapping("/users/{databaseId}")
    @Transactional
    public ResponseEntity<String> deleteUserByDatabaseId(@PathVariable long  databaseId){
        iUserService.deleteUserByDatabaseId(databaseId);
        return ResponseEntity.ok("User with database Id "+ databaseId +" has been deleted from database");
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////
}
