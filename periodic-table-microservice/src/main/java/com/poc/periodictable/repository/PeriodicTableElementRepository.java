package com.poc.periodictable.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.poc.periodictable.entity.ElementEntity;

public interface PeriodicTableElementRepository extends PagingAndSortingRepository<ElementEntity, Long> {
}
