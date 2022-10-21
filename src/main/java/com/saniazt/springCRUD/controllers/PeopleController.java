package com.saniazt.springCRUD.controllers;


import com.saniazt.springCRUD.dao.PersonDAO;
import com.saniazt.springCRUD.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PeopleController {


    private final PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model){
        //Получим всех людей из DAO и передадим на отображение в views
        model.addAttribute("people",personDAO.index());
        return "people/index";
    }

    @GetMapping("/{id}")//id поместится в аргументы метода
    public String show(@PathVariable("id") int id, Model model){
        //Получим одного человека по его id DAO и передадим в views
        model.addAttribute("person",personDAO.show(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @GetMapping("/{id}/edit") //
    public String edit(Model model,@PathVariable("id") int id){
        model.addAttribute("person",personDAO.show(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person")Person person, @PathVariable("id")int id){
    personDAO.update(id,person);
    return "redirect:/people";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") Person person){
        personDAO.save(person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id")int id){
        personDAO.delete(id);
        return "redirect:/people";
    }
}
