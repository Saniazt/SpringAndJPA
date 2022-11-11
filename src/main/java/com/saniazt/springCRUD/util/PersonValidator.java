package com.saniazt.springCRUD.util;

import com.saniazt.springCRUD.dao.PersonDAO;
import com.saniazt.springCRUD.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator {
    private final PersonDAO personDAO;

    @Autowired
    public PersonValidator(PersonDAO personDAO){
        this.personDAO = personDAO;
    }


}
