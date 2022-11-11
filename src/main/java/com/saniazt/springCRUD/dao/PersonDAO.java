package com.saniazt.springCRUD.dao;

import com.saniazt.springCRUD.models.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public PersonDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Transactional
    public List<Person> index() {
        Session session = sessionFactory.getCurrentSession();
        // hibernate code below:
        List<Person> people =  session.createQuery("select p from Person p", Person.class).getResultList();
        return people;
    }


    public Person show(int id) {
        return null;
    }

    public Optional<Person> show(String email) {
        return Optional.empty();
    }


    public void save(Person person) {

    }

    public void update(int id, Person updatedPerson) {

    }

    public void updateAdmin(int id, Person updatedPerson) {

    }

    public void delete(int id) {

    }
}





