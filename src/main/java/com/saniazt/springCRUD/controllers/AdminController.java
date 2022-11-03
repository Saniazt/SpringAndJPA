package com.saniazt.springCRUD.controllers;

import com.saniazt.springCRUD.dao.PersonDAO;
import com.saniazt.springCRUD.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final PersonDAO personDAO;

    @Autowired
    public AdminController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String adminPage(Model model, @ModelAttribute("person") Person person) {
        model.addAttribute("people", personDAO.index()); //помещаем в модель список из людей
        return "adminPage";
    }

    @PatchMapping("/{id}")
    public String makeAdmin(@ModelAttribute("person") Person person, @PathVariable("id") int id){
        personDAO.updateAdmin(id,person);
        return "redirect:/people";
    }
}
