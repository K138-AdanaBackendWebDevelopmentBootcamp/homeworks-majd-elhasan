package com.kingsonthegenius.datauiapp.controller;

import com.kingsonthegenius.datauiapp.model.Nationality;
import com.kingsonthegenius.datauiapp.service.NationalityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.jws.WebParam;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class NationalityController {
    private final NationalityService service;

    public NationalityController(NationalityService service) {
        this.service = service;
    }

    @GetMapping("/nationalities")
    public String getAllNationalities(Model model){
        List<Nationality> nationalityList = service.findAllNationalities();
       model.addAttribute("nationalities",nationalityList);
        return "nationalities";
    }


    @GetMapping("/nationalityById")
    @ResponseBody
    public Optional<Nationality> getOneNationalityById(@RequestParam int id){
        return service.getNationalityById(id);
    }

    @RequestMapping (value = "/save",method = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT})
    public String addNationality(Nationality entity){
        entity.setUpdatedOn(new Date());
        service.addNationality(entity);
        return "redirect:/nationalities";
    }
    @RequestMapping (value = "/update",method = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT})
    public String updateNationality(Nationality entity){
        entity.setUpdatedOn(new Date());
        service.updateNationality(entity);
        return "redirect:/nationalities";
    }
    @RequestMapping(value = "/delete",method ={ RequestMethod.DELETE,RequestMethod.GET})
    public String deleteNationality(@RequestParam int id){
        service.deleteNationalityById(id);
        return "redirect:/nationalities";
    }

}
