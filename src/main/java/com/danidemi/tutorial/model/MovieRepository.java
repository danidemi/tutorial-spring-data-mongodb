package com.danidemi.tutorial.model;

import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, String> {

    Movie findByIdAndAuthor(String movieId, Author author);

}
