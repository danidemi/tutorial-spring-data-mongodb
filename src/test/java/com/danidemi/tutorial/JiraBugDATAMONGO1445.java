package com.danidemi.tutorial;

import com.danidemi.tutorial.model.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@ComponentScan(basePackageClasses={BookRepository.class})
public class JiraBugDATAMONGO1445 {

    @Autowired
    MovieRepository movieRepo;

    @Autowired
    BookRepository bookRepo;

    @Autowired
    AuthorRepository authorRepo;

    @Before
    public void setUp(){
        movieRepo.deleteAll();
        bookRepo.deleteAll();
        authorRepo.deleteAll();
    }

    @Ignore("@Ignored because it breaks the build. Please refer to: https://jira.spring.io/browse/DATAMONGO-1445.")
    @Test
    public void shouldFindMovieByIdAndAuthor() {

        Author author = new Author();
        author.setFirstName("Pier Paolo");
        author.setFirstName("Pasolini");
        authorRepo.save(author);

        Movie movie = new Movie();
        movie.setTitle("I racconti di Canterbury");
        movie.setAuthor(author);
        movieRepo.save(movie);

        Book book = new Book();
        book.setAuthor(author);
        book.setTitle("Il vantone di Plauto");
        book.setYear(1963);
        bookRepo.save(book);

        assertThat( movieRepo.findByIdAndAuthor(movie.getId(), author), equalTo(movie) );
        assertThat( bookRepo.findByIdAndAuthor(book.getId(), author), equalTo(book) );

    }

}
