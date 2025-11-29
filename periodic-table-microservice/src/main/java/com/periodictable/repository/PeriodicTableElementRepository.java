package com.periodictable.repository;

import com.periodictable.entity.Element;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeriodicTableElementRepository extends ReactiveSortingRepository<Element, Integer> {
  //TODO https://bytethefrog.de/blog/2023-02-06-Full-Text-Search-with-JPA-and-PostgreSQL/

}
