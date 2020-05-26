package org.javacream.books.warehouse.impl;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.javacream.books.isbngenerator.api.IsbnGenerator;
import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.api.BookException;
import org.javacream.books.warehouse.api.BooksService;
import org.javacream.store.api.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(propagation = Propagation.REQUIRED)
public class JpaBooksService implements BooksService {

	@PersistenceContext private EntityManager entityManager;
	
	@Autowired @IsbnGenerator.RandomStrategy
	private IsbnGenerator isbnGenerator;
	@Autowired
	private StoreService storeService;

	public void setStoreService(StoreService storeService) {
		this.storeService = storeService;
	}

	public void setIsbnGenerator(IsbnGenerator isbnGenerator) {
		this.isbnGenerator = isbnGenerator;
	}

	public String newBook(String title) throws BookException {
		String isbn = isbnGenerator.next();
		Book book = new Book();
		book.setIsbn(isbn);
		book.setTitle(title);
		entityManager.persist(book);
		return isbn;
	}

	public IsbnGenerator getIsbnGenerator() {
		return isbnGenerator;
	}

	public Book findBookByIsbn(String isbn) throws BookException {
		Book result = entityManager.find(Book.class, isbn);
		if (result == null) {
			throw new BookException(BookException.BookExceptionType.NOT_FOUND, isbn);
		}
		result.setAvailable(storeService.getStock("books", isbn) > 0);

		return result;
	}

	public Book updateBook(Book book) throws BookException {
		entityManager.merge(book);
		return book;
	}

	public void deleteBookByIsbn(String isbn) throws BookException {
		//Book toDelete = entityManager.find(Book.class, isbn); -> falsch: Buch zum Löschen komplett laden???
		Book toDelete = entityManager.getReference(Book.class, isbn); //So ists richtig
		if (toDelete== null) {
			throw new BookException(BookException.BookExceptionType.NOT_DELETED, isbn);
		}
//		//Alternativ: Native Query
//		Query query = entityManager.createNativeQuery("delete from BOOK_TABLE where isbn=:isbn");
//		query.setParameter("isbn", isbn);
//		query.executeUpdate();
		entityManager.remove(toDelete);
	}

	public Collection<Book> findAllBooks() {
		String jpaQuery = "select book from BookEntity as book";
		return entityManager.createQuery(jpaQuery, Book.class).getResultList();
	}


}
