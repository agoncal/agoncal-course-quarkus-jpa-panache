package org.agoncal.quarkus.panache.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
@Entity
@Table(name = "t_book")
public class Book extends PanacheEntity {

  // ======================================
  // =             Attributes             =
  // ======================================

  public String title;
  public String description;
  public BigDecimal price;
  public String isbn;
  public Integer nbOfPages;
  public LocalDate publicationDate;
  public Language language;
  public Instant createdDate = Instant.now();

  @Override
  public String toString() {
    return "Book{" +
      "title='" + title + '\'' +
      ", description='" + description + '\'' +
      ", price=" + price +
      ", isbn='" + isbn + '\'' +
      ", nbOfPages=" + nbOfPages +
      ", publicationDate=" + publicationDate +
      ", language=" + language +
      ", createdDate=" + createdDate +
      ", id=" + id +
      '}';
  }
}
