package org.agoncal.quarkus.panache.service;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.agoncal.quarkus.panache.model.Book;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@QuarkusTest
@TestTransaction
public class BookServiceTest {

  @Inject
  BookService service;

  @Test
  void shouldManageABook() {

    Long initialCount = service.countBooks();

    Book book = new Book("Understanding Quarkus", "Best Quarkus book ever", new BigDecimal("9.99"), "13-235-8764", 500);
    assertNull(book.id);

    book = service.persistBook(book);

    assertNotNull(book.id);
    assertEquals(initialCount + 1, service.countBooks());

    book = service.findBookById(book.id);
    assertEquals("Understanding Quarkus", book.title);

    service.deleteBook(book.id);

    assertEquals(initialCount, service.countBooks());
  }
}
