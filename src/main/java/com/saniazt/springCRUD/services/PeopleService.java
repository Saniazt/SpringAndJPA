package com.saniazt.springCRUD.services;


import com.saniazt.springCRUD.models.Person;
import com.saniazt.springCRUD.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Service
@Transactional(readOnly = true)
public class PeopleService {
    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    public Person findOne(int id){
        return peopleRepository.findById(id).orElse(null); //Optional
    }

    @Transactional //помечаем тк этот метод пишет в базу данных
    public void save(Person person) {
        person.setCreatedAt(new Date());
        peopleRepository.save(person);
    }

    @Transactional
    public void update(int id,Person updatedPerson){
        updatedPerson.setId(id);
        peopleRepository.save(updatedPerson);// save работает не только для создания но и для сохранения уже соществующего
    }

    @Transactional
    public void delete(int id){
        peopleRepository.deleteById(id);
    }
    public void test(){
        System.out.println("Testing here with debug. Inside Hibernate Transaction");
    }
}
