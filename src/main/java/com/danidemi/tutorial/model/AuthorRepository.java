package com.danidemi.tutorial.model;

import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, String>{
    Author findById(String id);
    Author findByIdAndFirstName(String id, String roberto);
}
