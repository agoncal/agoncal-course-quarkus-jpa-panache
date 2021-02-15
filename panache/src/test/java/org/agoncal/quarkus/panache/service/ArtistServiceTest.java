package org.agoncal.quarkus.panache.service;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.agoncal.quarkus.panache.model.Author;
import org.agoncal.quarkus.panache.model.Musician;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
@TestTransaction
class ArtistServiceTest {

  @Inject
  ArtistService service;

  @Test
  void shouldCreateAnAuthor() {
    Author author = new Author("first", "last", "bio");
    author = service.createAuthor(author);
    assertNotNull(author.getId());
  }

  @Test
  void shouldCreateAMusician() {
    Musician musician = new Musician("first", "last", "instrument");
    musician = service.createMusician(musician);
    assertNotNull(musician.getId());
  }
}
