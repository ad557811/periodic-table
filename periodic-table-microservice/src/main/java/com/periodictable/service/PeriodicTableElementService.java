package com.periodictable.service;

import com.periodictable.entity.Element;
import com.periodictable.repository.PeriodicTableElementRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class PeriodicTableElementService {

  private final PeriodicTableElementRepository repository;

  public PeriodicTableElementService(PeriodicTableElementRepository repository) {
    this.repository = repository;
  }

  public Flux<Element> getElements(String query) {
    return repository.findAll(Sort.by(Sort.Order.asc("number")));
  }

}
