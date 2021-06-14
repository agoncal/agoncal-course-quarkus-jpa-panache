package org.agoncal.quarkus.panache.repository;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.agoncal.quarkus.jdbc.Artist;
import org.agoncal.quarkus.jpa.Customer;
import org.agoncal.quarkus.panache.model.Book;
import org.agoncal.quarkus.panache.model.Language;
import org.agoncal.quarkus.panache.model.OrderLine;
import org.agoncal.quarkus.panache.model.Publisher;
import org.agoncal.quarkus.panache.model.PurchaseOrder;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class PurchaseOrderRepositoryTest {

  @Inject
  CustomerRepository customerRepository;
  @Inject
  ArtistRepository artistRepository;

  @Test
  @TestTransaction
  public void shouldCreateAndFindAPurchaseOrder() {
    long countCustomers = customerRepository.count();
    long countArtists = artistRepository.count();
    long countPublishers = Publisher.count();
    long countBooks = Book.count();
    long countPurchaseOrders = PurchaseOrder.count();
    long countOrderLines = OrderLine.count();

    // Creates a Customer
    Customer customer = new Customer();
    customer.setFirstName("first name");
    customer.setLastName("last name");
    customer.setEmail("email");
    // Creates an Artist
    Artist artist = new Artist();
    artist.setName("name");
    artist.setBio("bio");
    // Creates a Publisher
    Publisher publisher = new Publisher();
    publisher.name = "name";
    // Creates a Book
    Book book = new Book();
    book.title = "title";
    book.description = "description";
    book.price = new BigDecimal(10);
    book.isbn = "ISBN";
    book.nbOfPages = 500;
    book.publicationDate = LocalDate.now().minusDays(100);
    book.language = Language.ENGLISH;
    // Sets the Publisher and Artist to the Book
    book.publisher = publisher;
    book.artist = artist;
    // Persists the Book with one Publisher and one Artist
    Book.persist(book);

    // Creates a PurchaseOrder with an OrderLine
    OrderLine orderLine = new OrderLine();
    orderLine.item = book;
    orderLine.quantity = 2;
    PurchaseOrder purchaseOrder = new PurchaseOrder();
    // Sets the Customer, Publisher, Artist and Book to the Purchase Order
    purchaseOrder.customer = customer;
    purchaseOrder.addOrderLine(orderLine);

    // Persists the PurchaseOrder and one OrderLine
    PurchaseOrder.persist(purchaseOrder);
    assertNotNull(purchaseOrder.id);
    assertEquals(1, purchaseOrder.orderLines.size());

    assertEquals(countCustomers + 1, customerRepository.count());
    assertEquals(countArtists + 1, artistRepository.count());
    assertEquals(countPublishers + 1, Publisher.count());
    assertEquals(countBooks + 1, Book.count());
    assertEquals(countPurchaseOrders + 1, PurchaseOrder.count());
    assertEquals(countOrderLines + 1, OrderLine.count());

    // Gets the PurchaseOrder
    purchaseOrder = PurchaseOrder.findById(purchaseOrder.id);

    // Deletes the PurchaseOrder
    PurchaseOrder.deleteById(purchaseOrder.id);
    assertEquals(countCustomers + 1, customerRepository.count());
    assertEquals(countArtists + 1, artistRepository.count());
    assertEquals(countPublishers + 1, Publisher.count());
    assertEquals(countBooks + 1, Book.count());
    assertEquals(countPurchaseOrders, PurchaseOrder.count());
    assertEquals(countOrderLines, OrderLine.count());
  }
}
