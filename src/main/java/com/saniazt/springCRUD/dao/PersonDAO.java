package com.saniazt.springCRUD.dao;

import com.saniazt.springCRUD.models.Person;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.swing.text.html.parser.Entity;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class PersonDAO {

    private final EntityManager entityManager; // тк мы используем JPA (В Hibernate было бы SessionFactory)

    @Autowired
    public PersonDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional(readOnly = true)
    public void testNPlus1() {
        Session session = entityManager.unwrap(Session.class); //таким образом открываем Hibernate session, раньше было SessionFactory sf = ...getCurrentSession()
        //1 запрос получим лист людей HQL комманда
        // List<Person> people = session.createQuery("select p from Person p",Person.class).getResultList();
        //N запросов к БД
       /* for(Person person:people)
            System.out.println("Person "+person.getName()+" has "+person.getItems());*/
    //Solution
        Set<Person> people = new HashSet<Person>(session.createQuery("select p from Person p left join FETCH p.items").getResultList());

        for(Person person:people)
            System.out.println("Person "+person.getName()+" has "+person.getItems());
    }
}
