package org.agoncal.quarkus.panache.model;

import javax.persistence.Entity;
import java.time.LocalDate;

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

  public String isbn;
  public Integer nbOfPages;
  public LocalDate publicationDate;
  public Language language;

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
