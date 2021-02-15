package org.agoncal.quarkus.panache.service;

import org.agoncal.quarkus.panache.model.Author;
import org.agoncal.quarkus.panache.repository.AuthorRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional
public class AuthorService {

  @Inject
  AuthorRepository repository;

  public Author persistAuthor(Author author) {
    repository.persist(author);
    return author;
  }

  public Author findAuthorById(Long id) {
    return repository.findById(id);
  }

  public void deleteAuthor(Long id) {
    repository.deleteById(id);
  }

  public Long countAuthors() {
    return repository.count();
  }
}
