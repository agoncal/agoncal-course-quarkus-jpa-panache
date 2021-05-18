package org.agoncal.quarkus.panache.repository;

import com.github.javafaker.Faker;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.agoncal.quarkus.jdbc.Artist;
import org.agoncal.quarkus.panache.model.Book;
import org.agoncal.quarkus.panache.model.Language;
import org.agoncal.quarkus.panache.model.Publisher;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class BookRepositoryTest {

  @Inject
  ArtistRepository artistRepository;

  private static Faker faker = new Faker();

  @Test
  @TestTransaction
  public void shouldCreateAndFindABook() {
    // Creates an Artist
    Artist artist = new Artist();
    artist.setName(faker.book().author());
    artist.setBio(faker.lorem().paragraph());
    // Creates a Publisher
    Publisher publisher = new Publisher();
    publisher.name = faker.book().publisher();
    // Creates a Book
    Book book = new Book();
    book.title = faker.book().title();
    book.description = faker.lorem().paragraph();
    book.price = new BigDecimal(faker.number().numberBetween(1, 100));
    book.isbn = faker.code().isbn13();
    book.nbOfPages = faker.number().numberBetween(10, 800);
    book.publicationDate = LocalDate.now().minusDays(faker.number().numberBetween(100, 10_000));
    book.language = Language.ENGLISH;
    // Sets the Publisher and Artist to the Book
    book.publisher = publisher;
    book.artist = artist;

    // Persists the Book with one Publisher and one Artist
    Book.persist(book);

    // Gets the Book
    book = Book.findById(book.id);

    // Checks the Book
    assertNotNull(book.id);
    assertNotNull(book.title);
    assertNotNull(book.description);
    assertNotNull(book.price);
    assertNotNull(book.isbn);
    assertNotNull(book.nbOfPages);
    assertNotNull(book.publicationDate);
    assertNotNull(book.createdDate);
    // Checks the Publisher
    assertNotNull(book.publisher);
    assertNotNull(book.publisher.id);
    assertNotNull(book.publisher.name);
    assertNotNull(book.publisher.createdDate);
    // Checks the Artist
    assertNotNull(book.artist);
    assertNotNull(book.artist.getId());
    assertNotNull(book.artist.getName());
    assertNotNull(book.artist.getBio());
    assertNotNull(book.artist.getCreatedDate());
  }

  @Test
  @Disabled
  @Transactional
  public void shouldFillUpTheDatabase() {
    for (int i = 0; i < 1000; i++) {
      Book book = new Book();
      book.title = faker.book().title();
      book.description = faker.lorem().paragraph();
      book.price = new BigDecimal(faker.number().numberBetween(1, 100));
      book.isbn = faker.code().isbn13();
      book.nbOfPages = faker.number().numberBetween(10, 800);
      book.publicationDate = LocalDate.now().minusDays(faker.number().numberBetween(100, 10_000));
      book.language = faker.options().option(Language.class);
      // Finds a Publisher
      book.publisher = Publisher.findById(faker.number().numberBetween(1L, 1_000L));
      // Finds an Artist
      book.artist = artistRepository.findById(faker.number().numberBetween(1L, 500L));
      Book.persist(book);
    }
  }
}
