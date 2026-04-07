package com.periodictable.service;

import com.periodictable.entity.Element;
import com.periodictable.repository.PeriodicTableElementRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PeriodicTableElementServiceTest {

  PeriodicTableElementRepository repository = mock(PeriodicTableElementRepository.class);
  PeriodicTableElementService sut = new PeriodicTableElementService(repository);

  @Test
  void shouldFilterResults() {
    Element hydrogen = new Element(1,
        "hydrogen",
        "colorless gas",
        "diatomic nonmetal",
        0.08988,
        "Henry Cavendish",
        "Antoine Lavoisier",
        "H",
        "[omissis]");
    when(repository.searchByQuery("hydrogen")).thenReturn(Flux.just(hydrogen));
    StepVerifier
        .create(sut.getElements("hydrogen", 0, 10))
        .expectNext(hydrogen)
        .expectComplete()
        .verify();

    ArgumentCaptor<String> captureArguments = ArgumentCaptor.forClass(String.class);
    verify(repository).searchByQuery(captureArguments.capture());
    String queryString = captureArguments.getValue();
    assertEquals("hydrogen", queryString);
  }

}