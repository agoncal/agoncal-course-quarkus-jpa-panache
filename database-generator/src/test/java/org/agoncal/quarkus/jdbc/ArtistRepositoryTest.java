package org.agoncal.quarkus.jdbc;

import com.github.javafaker.Faker;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class ArtistRepositoryTest {

  @Inject
  ArtistRepository repository;

  private Faker faker = new Faker();

  @Test
  public void shouldCreateAndFindAnArtist() throws SQLException {
    Artist artist = new Artist();
    artist.setName(faker.name().name());
    artist.setBio(faker.lorem().paragraph());

    repository.persist(artist);

    artist = repository.findById(artist.getId());

    assertNotNull(artist.getId());
    assertNotNull(artist.getName());
    assertNotNull(artist.getBio());
    assertNotNull(artist.getCreatedDate());
  }
}
