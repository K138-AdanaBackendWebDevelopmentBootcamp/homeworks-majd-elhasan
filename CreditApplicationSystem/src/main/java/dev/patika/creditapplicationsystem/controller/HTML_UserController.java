package dev.patika.creditapplicationsystem.controller;

import dev.patika.creditapplicationsystem.exception.*;
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
    private String budgetUpdatedInfo= "";
    public String excMsg;    // defined to be used in HTML view by methods in common
    private final UserService service;
    public HTML_UserController(UserService iUserService) {
        this.service = iUserService;
    }

    @GetMapping(value = "")
    @Transactional(readOnly = true)
    public String getUsers(Model model,@RequestParam(required = false,defaultValue = "databaseId") String sortBy,@RequestParam(required = false,defaultValue = "true") Boolean ascending){
        List<User> userList = service.getUsersSorted(sortBy, ascending);
        model.addAttribute("excMsg",excMsg);
        model.addAttribute("BUI",budgetUpdatedInfo);
        model.addAttribute("users",userList);
        budgetUpdatedInfo="";
        excMsg="";
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


    @RequestMapping(value = "/save_user",method = {RequestMethod.PUT,RequestMethod.GET,RequestMethod.POST})
    @Transactional
    public String saveUser( User user, Model model ) {
        try {
            service.saveUser(user);
            budgetUpdatedInfo = UserService.budgetUpdatedInfo;
        }catch (Exception
                 e){
            model.addAttribute("error",e);
            excMsg = e.getMessage();
        }
        return "redirect:/users";
    }

    @RequestMapping(value = "/delete_user",method ={ RequestMethod.DELETE,RequestMethod.GET})
    public String deleteUserByDatabaseId(@RequestParam(required = false) long id){
        service.deleteUserByDatabaseId(id);
        return "redirect:/users";
    }
}
