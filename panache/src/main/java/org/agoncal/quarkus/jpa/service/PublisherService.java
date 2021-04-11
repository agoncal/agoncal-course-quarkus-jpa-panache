package org.agoncal.quarkus.jpa.service;

import org.agoncal.quarkus.jpa.model.Publisher;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

@ApplicationScoped
@Transactional(SUPPORTS)
public class PublisherService {

  @Transactional(REQUIRED)
  public Publisher persistPublisher(Publisher publisher) {
    Publisher.persist(publisher);
    return publisher;
  }

  @Transactional(REQUIRED)
  public List<Publisher> persistPublishers(List<Publisher> publishers) {
    Publisher.persist(publishers);
    return publishers;
  }

  public Optional<Publisher> findPublisherById(Long id) {
    return Publisher.findByIdOptional(id);
  }

  public List<Publisher> findContainingName(String name) {
    return Publisher.findContainingName(name);
  }

  public Optional<Publisher> findByName(String name) {
    return Publisher.findByName(name);
  }

  @Transactional(REQUIRED)
  public void deletePublisher(Long id) {
    Publisher.deleteById(id);
  }

  public Long countPublishers() {
    return Publisher.count();
  }
}
