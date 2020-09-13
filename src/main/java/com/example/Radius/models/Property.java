package com.example.Radius.models;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Value;

@Value // To create immutable objects, No setters are provided
@Builder // To build the object as setters are not provided
public class Property {
  private String propertyId;
  private double latitude;
  private double longitude;
  private BigDecimal price;
  private int noOfBedRooms;
  private int noOfBathRooms;
}
