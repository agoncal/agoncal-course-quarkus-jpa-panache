package org.agoncal.quarkus.panache.repository;

import com.github.javafaker.Faker;
import io.quarkus.test.junit.QuarkusTest;
import org.agoncal.quarkus.jdbc.Artist;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.transaction.Transactional;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class ArtistRepositoryTest {

  @Inject
  ArtistRepository repository;

  private Faker faker = new Faker();

  @Test
  @Transactional
  public void shouldCreateAndFindAnArtist() {
    Artist artist = new Artist();
    artist.setId(Math.abs(new Random().nextLong()));
    artist.setFirstName(faker.name().firstName());
    artist.setLastName(faker.name().lastName());
    artist.setBio(faker.lorem().paragraph());

    repository.persist(artist);

    artist = repository.findById(artist.getId());

    assertNotNull(artist.getId());
    assertNotNull(artist.getFirstName());
    assertNotNull(artist.getLastName());
    assertNotNull(artist.getBio());
    assertNotNull(artist.getCreatedDate());
  }
}
