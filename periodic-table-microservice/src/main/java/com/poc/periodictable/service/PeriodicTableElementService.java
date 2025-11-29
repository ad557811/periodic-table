package com.poc.periodictable.service;

import com.poc.periodictable.model.Element;
import com.poc.periodictable.repository.PeriodicTableElementRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeriodicTableElementService {

  private PeriodicTableElementRepository repository;

  public List<Element> getElements(String query, int pageSize, int pageIndex) {

  }

}
