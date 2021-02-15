package org.agoncal.quarkus.panache.service;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.agoncal.quarkus.panache.model.CD;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.math.BigDecimal;

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

    CD cd = new CD("Quarkus Lonely Heart", "Impired by the Beatles", new BigDecimal("9.99"), "Geek Pop");
    assertNull(cd.id);

    cd = service.persistCD(cd);

    assertNotNull(cd.id);
    assertEquals(initialCount + 1, service.countCDs());

    cd = service.findCDById(cd.id);
    assertEquals("Quarkus Lonely Heart", cd.title);

    service.deleteCD(cd.id);

    assertEquals(initialCount, service.countCDs());
  }
}
