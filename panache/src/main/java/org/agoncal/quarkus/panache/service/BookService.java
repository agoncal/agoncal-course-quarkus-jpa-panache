package org.agoncal.quarkus.panache.service;

import org.agoncal.quarkus.panache.model.Book;
import org.agoncal.quarkus.panache.model.Publisher;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import java.util.Optional;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

@ApplicationScoped
@Transactional(SUPPORTS)
public class BookService {

  @Transactional(REQUIRED)
  public Book persistBook(Book book) {
    Book.persist(book);
    return book;
  }

  @Transactional(REQUIRED)
  public Book persistBookWithExistingPublisher(Book book, Long publisherId) {
    Publisher publisher = Publisher.findById(publisherId);
    book.publisher = publisher;
    Book.persist(book);
    return book;
  }

  public Book findBookById(Long id) {
    return Book.findById(id);
  }

  @Transactional(REQUIRED)
  public void deleteBook(Long id) {
    Book.deleteById(id);
  }

  public Long countBooks() {
    return Book.count();
  }

  public Optional<Book> findBookByIsbn(String isbn) {
    return Book.findByIsbn(isbn);
  }

  @Transactional(REQUIRED)
  public long deleteBookByIsbn(String isbn) {
    return Book.deleteByIsbn(isbn);
  }
}
