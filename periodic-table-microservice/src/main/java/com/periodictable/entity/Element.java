package com.periodictable.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table
public record Element(
    @Id
    int number,
    String name,
    String appearance,
    String category,
    Double density,
    String discoveredBy,
    String namedBy,
    String symbol,
    String summary
) {
}
