package com.javaCP.CourseProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.javaCP.CourseProject.model.Footballer;
import com.javaCP.CourseProject.service.FootballerService;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FootballerController {
    
    @Autowired
    private FootballerService service;
    
    @GetMapping("/")
    public String viewIndexPage(Model model, String keyword) {
        List<Footballer> footballersList = service.listAll();
        model.addAttribute("getAllFootballers", footballersList);
        
        if(keyword != null) {
            model.addAttribute("getAllFootballers", service.findByKeyword(keyword));
        }
        else {
            model.addAttribute("getAllFootballers", footballersList);
        }
        
        return "index";
    }
    
    @RequestMapping("/new_add")
    public String viewNewFootballerForm(Model model) {
        Footballer footballer = new Footballer();
        model.addAttribute("footballer", footballer);
        return "insert";
    }
    
    @RequestMapping(value = "/save_footballer", method = RequestMethod.POST)
    public String addNewFootballer(@ModelAttribute("footballer") Footballer footballer) {
        service.create(footballer);
        return "redirect:/";
    }
    
    @RequestMapping("/edit/{id}")
    public ModelAndView viewEditFootballerForm(@PathVariable(name = "id") long id) {
        
        ModelAndView mav = new ModelAndView("update");
        Footballer footballer = service.updateid(id);
        mav.addObject("footballer", footballer);
        return mav;
    }
    
    @RequestMapping("/delete/{id}")
    public String deleteFootballer(@PathVariable(name="id") long id) {
       service.delete(id);
       return "redirect:/";
    }
}
