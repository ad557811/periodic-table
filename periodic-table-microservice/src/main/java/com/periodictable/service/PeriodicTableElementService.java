package com.periodictable.service;

import com.periodictable.entity.Element;
import com.periodictable.repository.PeriodicTableElementRepository;
import org.apache.commons.lang3.StringUtils;
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
    log.debug("Searching for {}, skipping {} elements and taking {}", query, skipped, pageSize);

    Flux<Element> result;
    if (StringUtils.isBlank(query)) {
      result = repository.findAll(Sort.by(Sort.Order.asc("number")));
    } else {
      result = repository.searchByQuery(query);
    }

    return result
        .skip(skipped)
        .take(pageSize);
  }

}
