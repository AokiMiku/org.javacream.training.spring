package org.javacream.books;

import javax.annotation.PostConstruct;

import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.api.BookException;
import org.javacream.books.warehouse.impl.MapBooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BooksApplication {

	@Autowired private MapBooksService mapBooksService;
	@PostConstruct
	public void doBooksApplication() {
		System.out.println("Starting Books-App");
		try {
			String isbn = mapBooksService.newBook("Spring");
			System.out.println("Generated isbn: " + isbn);
			Book book = mapBooksService.findBookByIsbn(isbn);
			System.out.println("Found book: " + book);
		} catch (BookException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Books-App finished");
		
	}
}
