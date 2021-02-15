package org.agoncal.quarkus.panache.service;

import org.agoncal.quarkus.panache.model.Book;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

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
}
