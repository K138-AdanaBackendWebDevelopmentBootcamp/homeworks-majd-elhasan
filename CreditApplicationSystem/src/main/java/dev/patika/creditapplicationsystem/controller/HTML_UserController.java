package dev.patika.creditapplicationsystem.controller;

import dev.patika.creditapplicationsystem.model.Credit;
import dev.patika.creditapplicationsystem.model.User;
import dev.patika.creditapplicationsystem.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class HTML_UserController {

    private final UserService service;
    public HTML_UserController(UserService iUserService) {
        this.service = iUserService;
    }

    @GetMapping(value = "")
    @Transactional(readOnly = true)
    public String getUsers(Model model){
        List<User> userList = service.getUsers();
        Credit credit = new Credit();
        model.addAttribute("users",userList);
        model.addAttribute("credit_info",credit);
        return "users";
    }
    //  without this controller the app would go to the users' page with no bootstrap in it ,and that would cause a security problem .
    @GetMapping(value = "/")
    public String fallback(){
        return "redirectToUsers";
    }

    @ResponseBody
    @RequestMapping(value = "/get_user",method = RequestMethod.GET)
    public User getUserByDatabaseId(@RequestParam(required = false) long id){
        return service.getUserByDatabaseId(id);
    }


    @RequestMapping(value = "/update_user",method = {RequestMethod.PUT,RequestMethod.GET})
    @Transactional()
    public String updateUser(User user) {
        service.updateUser(user);
        return "redirect:/users";
    }


    @RequestMapping(value = "/save_user",method = {RequestMethod.POST,RequestMethod.GET})
    @Transactional
    public String saveUser(User user) {
        service.saveUser(user);
        return "redirect:/users";
    }


    @RequestMapping(value = "/delete_user",method ={ RequestMethod.DELETE,RequestMethod.GET})
    public String deleteUserByDatabaseId(@RequestParam(required = false) long id){
        service.deleteUserByDatabaseId(id);
        return "redirect:/users";
    }
}
