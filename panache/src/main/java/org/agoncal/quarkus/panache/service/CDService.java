package org.agoncal.quarkus.panache.service;

import org.agoncal.quarkus.panache.model.CD;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

@ApplicationScoped
@Transactional(SUPPORTS)
public class CDService {

  @Transactional(REQUIRED)
  public CD persistCD(CD cd) {
    CD.persist(cd);
    return cd;
  }

  public CD findCDById(Long id) {
    return CD.findById(id);
  }

  @Transactional(REQUIRED)
  public void deleteCD(Long id) {
    CD.deleteById(id);
  }

  public Long countCDs() {
    return CD.count();
  }
}
