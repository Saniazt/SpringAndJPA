package com.saniazt.springCRUD.dao;

import com.saniazt.springCRUD.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT*FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }


    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?",
                        new BeanPropertyRowMapper<>(Person.class),
                        new Object[]{id})
                .stream().findAny().orElse(null);
    }

    public Optional<Person> show(String email){
        return jdbcTemplate.query("SELECT * FROM Person WHERE email=?",
                new Object[]{email},
                new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny();
    }
    public Optional<Person> showAdmin(int adminOrNot){
        return jdbcTemplate.query("Select * From Person where is_admin=1",new Object[]{adminOrNot},new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person(name, age, email,address) VALUES(?,?,?,?)",
                person.getName(),
                person.getAge(),
                person.getEmail(),
                person.getAddress());

    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE Person SET name=?,age=?,email=?,address=? WHERE id=?",
                updatedPerson.getName(),
                updatedPerson.getAge(),
                updatedPerson.getEmail(),
                updatedPerson.getAddress(),
                id);
    }

    public void updateAdmin(int id, Person updatedPerson){
        jdbcTemplate.update("UPDATE person SET is_admin=1 WHERE id=?", updatedPerson.getId(),id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
    }




    //////////////////////////////
    ///////// Batch Update Test
    //////////////////////////////

    public void testMultipleUpdate(){
        List<Person> people = create1000People();
        long before = System.currentTimeMillis();
        for(Person person: people) {
            jdbcTemplate.update("INSERT INTO Person(name, age, email) VALUES(?,?,?)",
                    person.getName(),
                    person.getAge(),
                    person.getEmail());
        }
        long after = System.currentTimeMillis();
        System.out.println("Time: "+(after-before));
    }

    public void testBatchUpdate(){
        List<Person> people = create1000People();
        long before = System.currentTimeMillis();

        jdbcTemplate.batchUpdate("INSERT INTO Person(name, age, email) VALUES(?,?,?)", new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1,people.get(i).getName());
                ps.setInt(2,people.get(i).getAge());
                ps.setString(3,people.get(i).getEmail());
            }

            @Override
            public int getBatchSize() {
                return 10;
            }
        });

        long after = System.currentTimeMillis();
        System.out.println("Time: "+(after-before));
    }

    private List<Person> create1000People(){
        List<Person> people = new ArrayList<Person>();
        for (int i =0;i<10;i++){
            people.add(new Person(i,"Name"+i,30,"test3"+i+"gmail.com","Some address",0));
        }
        return people;
    }
}
