package com.periodictable.service;

import com.periodictable.test.utils.AbstractDatabaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.test.StepVerifier;

class PeriodicTableElementServiceIntegrationTest extends AbstractDatabaseTest {

  @Autowired
  PeriodicTableElementService service;

  @Test
  void shouldQueryDatabase() {
    StepVerifier
        .create(service.getElements("hydrogen", 0, 10))
        .expectComplete()
        .verify();
  }

  @Test
  void shouldRetrieveTopTen() {
    StepVerifier
        .create(service.getElements("", 0, 10))
        .expectComplete()
        .verify();
  }

}