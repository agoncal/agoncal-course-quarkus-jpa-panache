package org.agoncal.quarkus.panache.service;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.agoncal.quarkus.panache.model.Author;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@QuarkusTest
@TestTransaction
public class AuthorServiceTest {

  @Inject
  AuthorService service;

  @Test
  void shouldManageAnAuthor() {

    Long initialCount = service.countAuthors();

    Author author = new Author("Kameron", "Hurley", "American science fiction and fantasy writer");
    assertNull(author.getId());

    author = service.persistAuthor(author);

    assertNotNull(author.getId());
    assertEquals(initialCount + 1, service.countAuthors());

    author = service.findAuthorById(author.getId());
    assertEquals("Kameron", author.getFirstName());

    service.deleteAuthor(author.getId());

    assertEquals(initialCount, service.countAuthors());
  }
}
