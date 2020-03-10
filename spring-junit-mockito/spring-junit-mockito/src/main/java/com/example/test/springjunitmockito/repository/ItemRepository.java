package com.example.test.springjunitmockito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.example.test.springjunitmockito.model.Item;

@Component
public interface ItemRepository extends JpaRepository<Item, Integer> {

}
