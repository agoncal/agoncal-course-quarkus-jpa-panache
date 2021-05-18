package org.agoncal.quarkus.panache.repository;

import com.github.javafaker.Faker;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.agoncal.quarkus.jdbc.Artist;
import org.agoncal.quarkus.panache.model.CD;
import org.agoncal.quarkus.panache.model.Track;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class CDRepositoryTest {

  @Inject
  ArtistRepository artistRepository;

  private static Faker faker = new Faker();

  @Test
  @TestTransaction
  public void shouldCreateAndFindACD() {
    // Creates an Artist
    Artist artist = new Artist();
    artist.setName(faker.rockBand().name());
    artist.setBio(faker.lorem().paragraph());
    // Creates two Tracks
    Track first = new Track();
    first.title = faker.funnyName().name();
    first.duration = Duration.ofSeconds(faker.number().numberBetween(1, 1_000));
    Track second = new Track();
    second.title = faker.funnyName().name();
    second.duration = Duration.ofSeconds(faker.number().numberBetween(1, 1_000));
    // Creates a CD
    CD cd = new CD();
    cd.title = faker.rockBand().name();
    cd.description = faker.lorem().paragraph();
    cd.price = new BigDecimal(faker.number().numberBetween(1, 100));
    cd.musicCompany = faker.company().name();
    cd.genre = faker.music().genre();
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
    assertNotNull(cd.title);
    assertNotNull(cd.genre);
    assertNotNull(cd.createdDate);
    // Checks the Tracks
    assertEquals(2, cd.tracks.size());
    // Checks the Artist
    assertNotNull(cd.artist);
    assertNotNull(cd.artist.getId());
    assertNotNull(cd.artist.getName());
    assertNotNull(cd.artist.getBio());
    assertNotNull(cd.artist.getCreatedDate());
  }

  @Test
  @Disabled
  @Transactional
  public void shouldFillUpTheDatabase() {
    for (int i = 0; i < 1000; i++) {
      CD cd = new CD();
      cd.title = faker.rockBand().name();
      cd.description = faker.lorem().paragraph();
      cd.price = new BigDecimal(faker.number().numberBetween(1, 100));
      cd.musicCompany = faker.company().name();
      cd.genre = faker.music().genre();
      // Finds an Artist
      cd.artist = artistRepository.findById(faker.number().numberBetween(501L, 1_000L));
      CD.persist(cd);
    }
  }
}
