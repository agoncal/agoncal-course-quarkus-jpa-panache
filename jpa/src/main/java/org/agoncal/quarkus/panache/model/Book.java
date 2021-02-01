package org.agoncal.quarkus.panache.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 *         --
 */
@Entity
public class Book extends Item {

  // ======================================
  // =             Attributes             =
  // ======================================

  @Column(length = 15)
  private String isbn;

  @Column(name = "nb_of_pages")
  private Integer nbOfPages;

  @Column(name = "publication_date")
  private LocalDate publicationDate;

  @Enumerated(EnumType.STRING)
  private Language language;

  @ManyToOne
  @JoinColumn(name = "publisher_pk")
  private Publisher publisher;

  // ======================================
  // =              Constant              =
  // ======================================

  public static final String FIND_ALL = "Book.findAll";

  // ======================================
  // =            Constructors            =
  // ======================================

  public Book() {
  }

  public Book(String title, String description, Float unitCost, String isbn, Integer nbOfPages) {
    this.title = title;
    this.description = description;
    this.unitCost = unitCost;
    this.isbn = isbn;
    this.nbOfPages = nbOfPages;
  }

  // ======================================
  // =          Getters & Setters         =
  // ======================================

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public Integer getNbOfPages() {
    return nbOfPages;
  }

  public void setNbOfPages(Integer nbOfPage) {
    this.nbOfPages = nbOfPage;
  }

  public LocalDate getPublicationDate() {
    return publicationDate;
  }

  public void setPublicationDate(LocalDate publicationDate) {
    this.publicationDate = publicationDate;
  }

  public Language getLanguage() {
    return language;
  }

  public void setLanguage(Language language) {
    this.language = language;
  }

  public Publisher getPublisher() {
    return publisher;
  }

  public void setPublisher(Publisher publisher) {
    this.publisher = publisher;
  }
}
