package com.poc.periodictable.model;

public record Element(
    String name,
    String appearance,
    String category,
    double density,
    String discoveredBy,
    String namedBy,
    int number,
    String symbol
) {
}
