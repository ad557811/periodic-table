package com.periodictable.controller;

import com.periodictable.entity.Element;
import com.periodictable.service.PeriodicTableElementService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/element")
public class PeriodicTableElementController {

  private final PeriodicTableElementService service;

  public PeriodicTableElementController(PeriodicTableElementService service) {
    this.service = service;
  }

  @GetMapping
  public Flux<Element> getElements(@RequestParam(name = "query", required = false) String query,
                                   @RequestParam(name = "max", required = false, defaultValue = "10") int pageSize,
                                   @RequestParam(name = "page", required = false, defaultValue = "0") int pageIndex) {
    return service.getElements(query, pageIndex, pageSize);
  }

}
