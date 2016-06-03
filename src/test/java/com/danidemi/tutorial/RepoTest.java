package com.danidemi.tutorial;

import com.danidemi.tutorial.model.Book;
import com.danidemi.tutorial.model.BookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@ComponentScan(basePackageClasses={BookRepository.class})
public class RepoTest {

    @Autowired
    BookRepository repo;

    @Test
    public void run() {

        assertThat( repo, notNullValue() );

        Book book = new Book();
        book.setIsbn("AA112233");
        book.setTitle("Il volo della fenice");
        book.setYear(2015);
        repo.save( book );
    }

}