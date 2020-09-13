package com.example.Radius.models;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Value;

@Value // to create immutable objects, No setters are provided
@Builder // To build the object as setters are not provided
public class SearchRequirement {
  private String id;
  private double latitude;
  private double longitude;
  private BigDecimal minBudget;
  private BigDecimal maxBudget;
  private int minNoOfBedRooms;
  private int maxNoOfBedRooms;
  private int minNoOfBathRooms;
  private int maxNoOfBathRooms;
}
