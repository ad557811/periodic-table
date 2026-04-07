package com.periodictable.controller;

import com.periodictable.entity.Element;
import com.periodictable.service.PeriodicTableElementService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webflux.test.autoconfigure.WebFluxTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import static org.mockito.Mockito.when;

@WebFluxTest(controllers = PeriodicTableElementController.class)
class PeriodicTableElementControllerTest {

  @Autowired
  WebTestClient webTestClient;

  @MockitoBean
  PeriodicTableElementService service;

  @Test
  void shouldReturnASingleElement() {
    Element hydrogen = new Element(1,
        "hydrogen",
        "colorless gas",
        "diatomic nonmetal",
        0.08988,
        "Henry Cavendish",
        "Antoine Lavoisier",
        "H",
        "[omissis]");

    when(service.getElements(null, 0, 10))
        .thenReturn(Flux.just(hydrogen));

    webTestClient.get()
        .uri("/element")
        .exchange()
        .expectStatus().isOk()
        .expectBodyList(Element.class)
        .hasSize(1)
        .contains(hydrogen);
  }

}