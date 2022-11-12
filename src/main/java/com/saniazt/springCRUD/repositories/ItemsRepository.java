package com.saniazt.springCRUD.repositories;


import com.saniazt.springCRUD.models.Item;
import com.saniazt.springCRUD.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Persistence;
import java.util.List;

@Repository
public interface ItemsRepository extends JpaRepository <Item,Integer> {
    List<Item> findByItemName (String itemName);

    List<Item> findByOwner (Person owner); //person.getItems() - the same
}
