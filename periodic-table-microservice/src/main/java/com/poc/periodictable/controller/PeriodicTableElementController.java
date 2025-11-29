package com.poc.periodictable.controller;

import com.poc.periodictable.model.Element;
import com.poc.periodictable.service.PeriodicTableElementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/element")
@RequiredArgsConstructor
public class PeriodicTableElementController {

  private final PeriodicTableElementService service;

  @GetMapping
  public List<Element> getElements(@RequestParam(name = "name", required = false) String query,
                                   @RequestParam(name = "size", required = false, defaultValue = "10") int pageSize,
                                   @RequestParam(name = "index", required = false, defaultValue = "0") int pageIndex) {
    return service.getElements(query, pageSize, pageIndex);
  }

}
