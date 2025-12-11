package com.periodictable.service;

import com.periodictable.entity.Element;
import com.periodictable.repository.PeriodicTableElementRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class PeriodicTableElementService {

  private static final Logger log = LoggerFactory.getLogger(PeriodicTableElementService.class);
  private final PeriodicTableElementRepository repository;

  public PeriodicTableElementService(PeriodicTableElementRepository repository) {
    this.repository = repository;
  }

  public Flux<Element> getElements(String query, int pageIndex, int pageSize) {
    long skipped = (long) pageIndex * pageSize;
    log.debug("Skipping {} elements and taking {}", skipped, pageSize);
    return repository.findAll(Sort.by(Sort.Order.asc("number")))
        .skip(skipped)
        .take(pageSize);
  }

}
