package org.javacream.books.warehouse.test;

import org.javacream.books.isbngenerator.api.IsbnGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class IsbnGeneratorTest {

    @Autowired
    IsbnGenerator isbnGenerator;

    @Test
    public void nextTest() {

        String isbn = isbnGenerator.next();
        assert(isbn.startsWith("ISBN:"));
        assert(isbn.endsWith("-dk"));
    }
}
