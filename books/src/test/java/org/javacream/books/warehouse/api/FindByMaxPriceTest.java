package org.javacream.books.warehouse.api;

import org.javacream.books.isbngenerator.impl.RandomIsbnGeneratorService;
import org.javacream.books.warehouse.impl.MapBooksService;
import org.javacream.store.impl.SimpleStoreService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FindByMaxPriceTest {
    private BooksService booksService;

    @BeforeEach
    public void init() throws BookException {
        MapBooksService mapBooksService = new MapBooksService();
        RandomIsbnGeneratorService isbnGeneratorService = new RandomIsbnGeneratorService();
        SimpleStoreService storeService = new SimpleStoreService();
        mapBooksService.setStoreService(storeService);
        mapBooksService.setIsbnGeneratorService(isbnGeneratorService);
        booksService = mapBooksService;

        Book book1 = new Book("ISBN1", "Title1", 200, 19.99, true);
        Book book2 = new Book("ISBN2", "Title1", 200, 9.99, true);
        Book book3 = new Book("ISBN3", "Title1", 200, 29.99, true);
        booksService.newBook(book1);
        booksService.newBook(book2);
        booksService.newBook(book3);
    }

    @Test
    public void testFindByMaxPrice20Finds2Books() throws BookException {
        Assertions.assertEquals(2, booksService.findByMaxPrice(20d).size());
    }
    @Test
    public void testFindByMaxPrice10Finds1Book() throws BookException {
        Assertions.assertEquals(1, booksService.findByMaxPrice(10d).size());
    }
}
