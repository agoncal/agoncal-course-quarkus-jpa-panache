package org.agoncal.quarkus.panache.repository;

import com.github.javafaker.Faker;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.agoncal.quarkus.jdbc.Artist;
import org.agoncal.quarkus.jpa.Customer;
import org.agoncal.quarkus.panache.model.Book;
import org.agoncal.quarkus.panache.model.Item;
import org.agoncal.quarkus.panache.model.Language;
import org.agoncal.quarkus.panache.model.OrderLine;
import org.agoncal.quarkus.panache.model.Publisher;
import org.agoncal.quarkus.panache.model.PurchaseOrder;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class PurchaseOrderRepositoryTest {

  @Inject
  CustomerRepository customerRepository;

  private static Faker faker = new Faker();

  @Test
  @TestTransaction
  public void shouldCreateAndFindAPurchaseOrder() {
    // Creates a Customer
    Customer customer = new Customer();
    customer.setFirstName(faker.name().firstName());
    customer.setLastName(faker.name().lastName());
    customer.setEmail(faker.internet().emailAddress());
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

    // Creates a PurchaseOrder with an OrderLine
    OrderLine orderLine = new OrderLine();
    orderLine.item = book;
    orderLine.quantity = faker.number().numberBetween(1, 5);
    PurchaseOrder purchaseOrder = new PurchaseOrder();
    // Sets the Customer, Publisher, Artist and Book to the Purchase Order
    purchaseOrder.customer = customer;
    purchaseOrder.addOrderLine(orderLine);

    // Persists the PurchaseOrder and one OrderLine
    PurchaseOrder.persist(purchaseOrder);

    // Gets the Book
    purchaseOrder = PurchaseOrder.findById(purchaseOrder.id);

    // Checks the PurchaseOrder
    assertNotNull(purchaseOrder.id);
    assertNotNull(purchaseOrder.createdDate);
    // Checks the OrderLine
    assertEquals(1, purchaseOrder.orderLines.size());
  }

  @Test
  @Disabled
  @Transactional
  public void shouldFillUpTheDatabase() {
    for (int i = 0; i < 100; i++) {
      PurchaseOrder purchaseOrder = new PurchaseOrder();

      for (int j = 0; j < faker.number().numberBetween(1, 5); j++) {
        OrderLine orderLine = new OrderLine();
        // Finds an Item
        orderLine.item = Item.findById(faker.number().numberBetween(1L, 2_000L));
        orderLine.quantity = faker.number().numberBetween(1, 5);
        purchaseOrder.addOrderLine(orderLine);
      }
      // Finds a
      purchaseOrder.date = LocalDate.now().minusDays(faker.number().numberBetween(1, 1_000));
      purchaseOrder.customer = customerRepository.findById(faker.number().numberBetween(1L, 1_000L));

      // Persists the PurchaseOrder and OrderLines
      PurchaseOrder.persist(purchaseOrder);
    }
  }
}
