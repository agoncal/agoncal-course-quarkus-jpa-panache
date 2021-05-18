package org.agoncal.quarkus.panache.repository;

import com.github.javafaker.Faker;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.agoncal.quarkus.panache.model.CD;
import org.agoncal.quarkus.panache.model.Track;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.transaction.Transactional;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class TrackRepositoryTest {

  private static Faker faker = new Faker();

  @Test
  @TestTransaction
  public void shouldCreateAndFindATrack() {
    // Creates a Track
    Track track = new Track();
    track.title = faker.funnyName().name();
    track.duration = Duration.ofSeconds(faker.number().numberBetween(1, 1_000));

    // Persists the Track
    Track.persist(track);

    // Gets the Track
    track = Track.findById(track.id);

    // Checks the Track
    assertNotNull(track.id);
    assertNotNull(track.title);
    assertNotNull(track.duration);
    assertNotNull(track.createdDate);
  }

  @Test
  @Disabled
  @Transactional
  public void shouldFillUpTheDatabase() {
    for (int i = 0; i < 1000; i++) {
      Track track = new Track();
      track.title = faker.funnyName().name();
      track.duration = Duration.ofSeconds(faker.number().numberBetween(1, 1_000));
      // Finds a CD
      track.cd = CD.findById(faker.number().numberBetween(1L, 1_000L));
      Track.persist(track);
    }
  }
}
