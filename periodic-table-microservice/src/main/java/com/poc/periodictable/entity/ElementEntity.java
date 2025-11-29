package com.poc.periodictable.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class ElementEntity {

  //TODO https://bytethefrog.de/blog/2023-02-06-Full-Text-Search-with-JPA-and-PostgreSQL/

  @Id
  private int number;
  
  private String name;
  
  private String appearance;
  
  private String category;
  
  private Double density;
  
  private String discoveredBy;
  
  private String namedBy;
  
  private String symbol;

}
