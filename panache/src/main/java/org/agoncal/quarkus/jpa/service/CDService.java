package org.agoncal.quarkus.jpa.service;

import org.agoncal.quarkus.jpa.model.CD;
import org.agoncal.quarkus.jpa.model.Track;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;

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

  @Transactional(REQUIRED)
  public CD persistCDWithTracks(CD cd, List<Track> tracks) {
    Track.persist(tracks);
    cd.tracks = tracks;
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
