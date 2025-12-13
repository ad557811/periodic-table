package com.periodictable.repository;

import com.periodictable.entity.Element;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface PeriodicTableElementRepository extends ReactiveSortingRepository<Element, Integer> {

  // https://bytethefrog.de/blog/2023-02-06-Full-Text-Search-with-JPA-and-PostgreSQL/
  @Query("select * from element where search @@ websearch_to_tsquery('english', :query) order by number asc")
  Flux<Element> searchByQuery(String query);

}
