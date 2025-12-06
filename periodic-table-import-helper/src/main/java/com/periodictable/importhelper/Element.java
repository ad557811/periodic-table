package com.periodictable.importhelper;

public record Element(
    Integer number,
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
