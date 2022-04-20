package com.test.marathon.demo.dao;

import com.test.marathon.demo.entity.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PersonRepository extends MongoRepository<Person, String> {
  List<Person> findByRuc(String ruc);
}
