package org.agoncal.quarkus.panache.service;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.agoncal.quarkus.panache.model.Musician;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@QuarkusTest
@TestTransaction
public class MusicianServiceTest {

  @Inject
  MusicianService service;

  @Test
  void shouldManageAMusician() {

    Long initialCount = service.countMusicians();

    Musician musician = new Musician("Ella", "Fitzgerald", "American jazz singer, sometimes referred to as the First Lady of Song, Queen of Jazz, and Lady Ella");
    assertNull(musician.getId());

    musician = service.persistMusician(musician);

    assertNotNull(musician.getId());
    assertEquals(initialCount + 1, service.countMusicians());

    musician = service.findMusicianById(musician.getId());
    assertEquals("Ella", musician.getFirstName());

    service.deleteMusician(musician.getId());

    assertEquals(initialCount, service.countMusicians());
  }
}
