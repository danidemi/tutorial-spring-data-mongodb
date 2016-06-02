package com.danidemi.tutorial;

import com.danidemi.tutorial.model.BookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class RepoTest {

    @Autowired
    BookRepository repo;

    @Test
    public void run() {
        assertThat( repo, notNullValue() );
    }

}