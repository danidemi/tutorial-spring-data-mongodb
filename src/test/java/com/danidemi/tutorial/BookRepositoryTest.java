package com.danidemi.tutorial;

import com.danidemi.tutorial.model.Author;
import com.danidemi.tutorial.model.AuthorRepository;
import com.danidemi.tutorial.model.Book;
import com.danidemi.tutorial.model.BookRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigInteger;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@ComponentScan(basePackageClasses={BookRepository.class})
public class BookRepositoryTest {

    @Autowired
    BookRepository bookRepo;

    @Autowired
    AuthorRepository authorRepo;

    @Before
    public void resetTheRepos(){
        bookRepo.deleteAll();
        authorRepo.deleteAll();
    }

    @Test
    public void shouldFindTheRepository() {
        assertThat(bookRepo, notNullValue() );
        assertThat(authorRepo, notNullValue() );
    }

    @Test
    public void shouldFindByIdAndAuthor() {

        Author authorJohn = new Author();
        authorJohn.setFirstName("John");
        authorJohn.setLastName("Doe");
        authorRepo.save(authorJohn);

        Book book1 = new Book();
        book1.setIsbn("AA112233");
        book1.setTitle("The Phoenix");
        book1.setYear(2015);
        book1.setAuthor(authorJohn);
        bookRepo.save(book1);

        Book book2 = new Book();
        book2.setIsbn("AA112233");
        book2.setTitle("The Revenant");
        book2.setYear(2015);
        book2.setAuthor(authorJohn);
        bookRepo.save(book2);

        assertThat( bookRepo.findById( book2.getId() ), equalTo( book2 ) );
        assertThat( bookRepo.findByIdAndAuthor( book2.getId(), authorJohn) , equalTo( book2 ) );

    }

    @Test
    public void shouldLoadByIdAndAnotherField() {

        Author authorJohn = new Author();
        authorJohn.setFirstName("John");
        authorJohn.setLastName("Doe");
        authorRepo.save(authorJohn);

        Author authorRoberto = new Author();
        authorRoberto.setFirstName("Roberto");
        authorRoberto.setLastName("Del Neri");
        authorRepo.save(authorRoberto);

        String robertoId = authorRoberto.getId();
        assertThat( authorRepo.findOne(robertoId), equalTo( authorRoberto ));
        assertThat( authorRepo.findById(robertoId), equalTo( authorRoberto ));
        assertThat( authorRepo.findByIdAndFirstName(robertoId, "Roberto" ), equalTo( authorRoberto ));

    }

    @Test
    public void shouldLoadTheBooksOfAnAuthor() {

        Author authorJohn = new Author();
        authorJohn.setFirstName("John");
        authorJohn.setLastName("Doe");
        authorRepo.save(authorJohn);

        Book book1 = new Book();
        book1.setIsbn("AA112233");
        book1.setTitle("The Phoenix");
        book1.setYear(2015);
        book1.setAuthor(authorJohn);
        bookRepo.save(book1);

        Book book2 = new Book();
        book2.setIsbn("AA112233");
        book2.setTitle("The Revenant");
        book2.setYear(2015);
        book2.setAuthor(authorJohn);
        bookRepo.save(book2);

        List<Book> results = bookRepo.findByAuthor(authorJohn);

        assertThat( results, hasItems(book1, book2) );

    }

    @Test
    public void documentShouldBeEqualToItselfAfterSaving() {

        Book book = new Book();
        book.setIsbn("AA112233");
        book.setTitle("The Phoenix");
        book.setYear(2015);
        Book savedBook = bookRepo.save(book);

        assertThat( savedBook, equalTo(book) );

    }

    @Test
    public void documentShouldBeEqualToItselfAfterLoad() {

        Book book = new Book();
        book.setIsbn("AA112233");
        book.setTitle("The Phoenix");
        book.setYear(2015);
        BigInteger id = bookRepo.save(book).getId();

        Book loaded1 = bookRepo.findOne(id);
        Book loaded2 = bookRepo.findOne(id);

        assertThat( loaded1, equalTo(loaded2) );

    }

}