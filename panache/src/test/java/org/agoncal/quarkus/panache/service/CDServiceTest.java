package org.agoncal.quarkus.panache.service;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.agoncal.quarkus.panache.model.CD;
import org.agoncal.quarkus.panache.model.Track;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@QuarkusTest
@TestTransaction
public class CDServiceTest {

  @Inject
  CDService service;

  @Test
  void shouldManageACD() {

    Long initialCount = service.countCDs();

    CD cd = new CD("Quarkus Lonely Heart", "Inspired by the Beatles", new BigDecimal("9.99"), "Geek Pop");
    assertNull(cd.id);

    cd = service.persistCD(cd);

    assertNotNull(cd.id);
    assertEquals(initialCount + 1, service.countCDs());

    cd = service.findCDById(cd.id);
    assertEquals("Quarkus Lonely Heart", cd.title);

    service.deleteCD(cd.id);

    assertEquals(initialCount, service.countCDs());
  }

  @Test
  void shouldManageACDWithTracks() {

    Long initialCount = service.countCDs();
    Long trackCount = Track.count();

    List<Track> tracks = List.of(new Track("Getting Better", Duration.ofSeconds(168)), new Track("Fixing a Hole", Duration.ofSeconds(156)));
    CD cd = new CD("Quarkus Lonely Heart", "Inspired by the Beatles", new BigDecimal("9.99"), "Geek Pop");
    assertNull(cd.id);

    cd = service.persistCDWithTracks(cd, tracks);

    assertNotNull(cd.id);
    assertEquals(initialCount + 1, service.countCDs());

    cd = service.findCDById(cd.id);
    assertEquals("Quarkus Lonely Heart", cd.title);
    assertEquals(2, cd.tracks.size());

    service.deleteCD(cd.id);

    assertEquals(initialCount, service.countCDs());
    assertEquals(trackCount, Track.count());
  }

  @Test
  void shouldManageACDWithCascadeOnTracks() {

    Long initialCount = service.countCDs();
    Long trackCount = Track.count();

    List<Track> tracks = List.of(new Track("Getting Better", Duration.ofSeconds(168)), new Track("Fixing a Hole", Duration.ofSeconds(156)));
    CD cd = new CD("Quarkus Lonely Heart", "Inspired by the Beatles", new BigDecimal("9.99"), "Geek Pop");
    cd.tracks = tracks;
    assertNull(cd.id);

    cd = service.persistCD(cd);

    assertNotNull(cd.id);
    assertEquals(initialCount + 1, service.countCDs());

    cd = service.findCDById(cd.id);
    assertEquals("Quarkus Lonely Heart", cd.title);
    assertEquals(2, cd.tracks.size());

    service.deleteCD(cd.id);

    assertEquals(initialCount, service.countCDs());
    assertEquals(trackCount, Track.count());
  }
}
