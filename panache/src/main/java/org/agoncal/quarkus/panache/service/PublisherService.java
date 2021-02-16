package org.agoncal.quarkus.panache.service;

import org.agoncal.quarkus.panache.model.Publisher;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;

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

  public Publisher findPublisherById(Long id) {
    return Publisher.findById(id);
  }

  @Transactional(REQUIRED)
  public void deletePublisher(Long id) {
    Publisher.deleteById(id);
  }

  public Long countPublishers() {
    return Publisher.count();
  }
}
