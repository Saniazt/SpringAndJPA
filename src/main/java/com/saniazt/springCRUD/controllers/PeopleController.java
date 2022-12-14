package com.saniazt.springCRUD.controllers;



import com.saniazt.springCRUD.dao.PersonDAO;
import com.saniazt.springCRUD.models.Person;
import com.saniazt.springCRUD.services.ItemService;
import com.saniazt.springCRUD.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;

    private final PeopleService peopleService;
    private final ItemService itemService;


    @Autowired
    public PeopleController(PersonDAO personDAO, PeopleService peopleService, ItemService itemService) {
        this.personDAO = personDAO;
        this.peopleService = peopleService;
        this.itemService = itemService;
    }

    @GetMapping()
    public String index(Model model) {
        //Получим всех людей из DAO и передадим на отображение в views
        model.addAttribute("people", peopleService.findAll());
       /* personDAO.testNPlus1();*/
    /*    itemService.findByItemName("Airpods"); //for debug
        itemService.findByOwner(peopleService.findAll().get(0)); //for debug
        peopleService.test();*/ //for debug
        return "people/index";
    }

    @GetMapping("/{id}")//id поместится в аргументы метода
    public String show(@PathVariable("id") int id, Model model) {
        //Получим одного человека по его id DAO и передадим в views
        model.addAttribute("person", peopleService.findOne(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @GetMapping("/{id}/edit") //
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", peopleService.findOne(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) return "people/edit";

        else peopleService.update(id,person);
        return "redirect:/people";
    }


    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "people/new";

        else peopleService.save(person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        peopleService.delete(id);
        return "redirect:/people";
    }
}

