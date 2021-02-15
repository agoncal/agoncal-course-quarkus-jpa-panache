package org.agoncal.quarkus.panache.service;

import org.agoncal.quarkus.panache.model.Book;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional
public class BookService {

  public Book persistBook(Book book) {
    Book.persist(book);
    return book;
  }

  public Book findBookById(Long id) {
    return Book.findById(id);
  }

  public void deleteBook(Long id) {
    Book.deleteById(id);
  }

  public Long countBooks() {
    return Book.count();
  }
}
