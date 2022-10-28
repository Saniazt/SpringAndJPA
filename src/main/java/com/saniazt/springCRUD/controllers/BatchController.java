package com.saniazt.springCRUD.controllers;

import com.saniazt.springCRUD.dao.PersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test-batch-update")
public class BatchController {
    private final PersonDAO personDAO;

    @Autowired
    public BatchController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index() {
        return "batch/index";
    }

    @GetMapping("/without")
    public String withoutBatch() {
        personDAO.testMultipleUpdate(); //как обычно с INSERT но 1000 раз
        return "redirect:people";
    }

    @GetMapping("/with")
    public String withBatch() {
        personDAO.testBatchUpdate(); //с помощью BatchUpdate
        return "redirect:people";
    }


}
