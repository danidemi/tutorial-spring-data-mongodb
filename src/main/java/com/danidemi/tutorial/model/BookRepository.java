package com.danidemi.tutorial.model;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.math.BigInteger;

public interface BookRepository extends PagingAndSortingRepository<Book, BigInteger> {

    public Book findByIsbn(String isbn);

}
