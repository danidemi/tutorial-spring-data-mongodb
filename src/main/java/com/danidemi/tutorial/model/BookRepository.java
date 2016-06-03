package com.danidemi.tutorial.model;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.math.BigInteger;
import java.util.List;

public interface BookRepository extends PagingAndSortingRepository<Book, BigInteger> {

    public Book findByIsbn(String isbn);

    List<Book> findByAuthor(Author author);

    Book findByIdAndAuthor(BigInteger id, Author authorJohn);

    Book findById(BigInteger id);
}

