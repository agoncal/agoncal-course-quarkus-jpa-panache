package org.agoncal.quarkus.panache.service;

import org.agoncal.quarkus.panache.model.Publisher;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional
public class PublisherService {

  public Publisher persistPublisher(Publisher publisher) {
    Publisher.persist(publisher);
    return publisher;
  }

  public Publisher findPublisherById(Long id) {
    return Publisher.findById(id);
  }

  public void deletePublisher(Long id) {
    Publisher.deleteById(id);
  }

  public Long countPublishers() {
    return Publisher.count();
  }
}
