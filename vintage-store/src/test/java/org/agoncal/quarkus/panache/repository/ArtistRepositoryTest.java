package org.agoncal.quarkus.panache.repository;

import com.github.javafaker.Faker;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.agoncal.quarkus.jdbc.Artist;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class ArtistRepositoryTest {

  @Inject
  ArtistRepository repository;

  private Faker faker = new Faker();

  @Test
  @TestTransaction
  public void shouldCreateAndFindAnArtist() {
    Artist artist = new Artist();
    artist.setName(faker.book().author());
    artist.setBio(faker.lorem().paragraph());

    repository.persist(artist);

    artist = repository.findById(artist.getId());

    assertNotNull(artist.getId());
    assertNotNull(artist.getName());
    assertNotNull(artist.getBio());
    assertNotNull(artist.getCreatedDate());
  }

  @Test
  @Disabled
  @Transactional
  public void shouldFillUpTheDatabase() {
    for (int i = 0; i < 500; i++) {
      Artist artist = new Artist();
      artist.setName(faker.book().author());
      artist.setBio(faker.lorem().paragraph());
      repository.persist(artist);
    }
    for (int i = 0; i < 500; i++) {
      Artist artist = new Artist();
      artist.setName(faker.rockBand().name());
      artist.setBio(faker.lorem().paragraph());
      repository.persist(artist);
    }
  }

}
