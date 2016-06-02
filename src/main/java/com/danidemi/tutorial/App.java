package com.danidemi.tutorial;

import com.danidemi.tutorial.model.BookRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan(basePackageClasses={BookRepository.class})
public class App 
{
    public static void main( String[] args ) {

        SpringApplication.run(App.class, args);

    }
}
