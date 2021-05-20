package org.agoncal.quarkus.panache.repository;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.agoncal.quarkus.jdbc.Artist;
import org.agoncal.quarkus.panache.model.CD;
import org.agoncal.quarkus.panache.model.Track;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class CDRepositoryTest {

  @Inject
  ArtistRepository artistRepository;

  @Test
  @TestTransaction
  public void shouldCreateAndFindACD() {
    // Creates an Artist
    Artist artist = new Artist();
    artist.setName("name");
    artist.setBio("bio");
    // Creates two Tracks
    Track first = new Track();
    first.title = "title 1";
    first.duration = Duration.ofSeconds(1_000);
    Track second = new Track();
    second.title = "title 2";
    second.duration = Duration.ofSeconds(500);
    // Creates a CD
    CD cd = new CD();
    cd.title = "title";
    cd.description = "description";
    cd.price = new BigDecimal(10);
    cd.musicCompany = "music company";
    cd.genre = "genre";
    // Sets the two Tracks and Artist to the CD
    cd.artist = artist;
    cd.addTrack(first);
    cd.addTrack(second);

    // Persists the CD with two Tracks and one Artist
    CD.persist(cd);

    // Gets the CD
    cd = CD.findById(cd.id);

    // Checks the CD
    assertNotNull(cd.id);
    // Checks the Tracks
    assertEquals(2, cd.tracks.size());
    // Checks the Artist
    assertNotNull(cd.artist.getId());
  }
}
