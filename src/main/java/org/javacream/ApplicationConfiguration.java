package org.javacream;

import java.util.HashMap;
import java.util.Map;

import org.javacream.books.warehouse.api.Book;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/*
 * Eine Configuration macht Dependency Outjection (kein Standard Begriff)
 */
@Configuration
@Profile("prod")
public class ApplicationConfiguration {

//	@Bean public Book book() {
//		Book book = new Book();
//		book.setIsbn("ISBN-OUT");
//		book.setTitle(("Outjected"));
//		return book;
//	}
	
	@Bean public Map<String, Book> books(){
		return new HashMap<>();
	}
	
}
