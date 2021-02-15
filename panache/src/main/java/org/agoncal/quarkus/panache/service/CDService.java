package org.agoncal.quarkus.panache.service;

import org.agoncal.quarkus.panache.model.CD;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional
public class CDService {

  public CD persistCD(CD cd) {
    CD.persist(cd);
    return cd;
  }

  public CD findCDById(Long id) {
    return CD.findById(id);
  }

  public void deleteCD(Long id) {
    CD.deleteById(id);
  }

  public Long countCDs() {
    return CD.count();
  }
}
