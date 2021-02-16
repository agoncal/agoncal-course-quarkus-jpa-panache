package org.agoncal.quarkus.panache.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
@Entity
public class Book extends Item {

  // ======================================
  // =             Attributes             =
  // ======================================

  @Column(length = 15)
  public String isbn;

  @Column(name = "nb_of_pages")
  public Integer nbOfPages;

  @Column(name = "publication_date")
  public LocalDate publicationDate;

  @Enumerated(EnumType.STRING)
  public Language language;

  @ManyToOne
  @JoinColumn(name = "publisher_pk")
  public Publisher publisher;

  // ======================================
  // =            Constructors            =
  // ======================================

  public Book() {
  }

  public Book(String title, String description, BigDecimal price, String isbn, Integer nbOfPages) {
    this.title = title;
    this.description = description;
    this.price = price;
    this.isbn = isbn;
    this.nbOfPages = nbOfPages;
  }

  // ======================================
  // =              Methods               =
  // ======================================

  public static Optional<Book> findByIsbn(String isbn) {
    return find("isbn", isbn).firstResultOptional();
  }

  public static long deleteByIsbn(String isbn) {
    return delete("isbn", isbn);
  }
}
