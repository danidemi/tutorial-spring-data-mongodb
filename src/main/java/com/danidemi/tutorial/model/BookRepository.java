package com.danidemi.tutorial.model;

import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository {

    Book findByIsbn(String isbn);

}
