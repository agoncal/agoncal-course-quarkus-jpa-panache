package org.agoncal.quarkus.panache.repository;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.agoncal.quarkus.panache.model.Track;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class TrackRepositoryTest {

  @Test
  @TestTransaction
  public void shouldCreateAndFindATrack() {
    long countTracks = Track.count();

    // Creates a Track
    Track track = new Track();
    track.title = "title";
    track.duration = Duration.ofSeconds(1_000);

    // Persists the Track
    Track.persist(track);
    assertNotNull(track.id);

    assertEquals(countTracks + 1, Track.count());

    // Gets the Track
    track = Track.findById(track.id);
    assertEquals("title", track.title);

    // Deletes the Track
    Track.deleteById(track.id);
    assertEquals(countTracks, Track.count());
  }
}
