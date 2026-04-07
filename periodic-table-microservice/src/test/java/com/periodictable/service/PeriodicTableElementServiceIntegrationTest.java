package com.periodictable.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;

@SpringBootTest
class PeriodicTableElementServiceIntegrationTest {

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