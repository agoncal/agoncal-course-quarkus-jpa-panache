package org.agoncal.quarkus.panache.repository;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.agoncal.quarkus.jdbc.Artist;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class ArtistRepositoryTest {

  @Inject
  ArtistRepository repository;

  @Test
  @TestTransaction
  public void shouldCreateAndFindAnArtist() {
    long nbArtists = repository.count();

    // Creates an Artist
    Artist artist = new Artist();
    artist.setName("name");
    artist.setBio("bio");

    // Persists the Artist
    repository.persist(artist);
    assertNotNull(artist.getId());

    assertEquals(nbArtists + 1, repository.count());

    // Gets the Artists
    artist = repository.findById(artist.getId());
    assertEquals("name", artist.getName());

    // Deletes the Artist
    repository.deleteById(artist.getId());
    assertEquals(nbArtists, repository.count());
  }
}
