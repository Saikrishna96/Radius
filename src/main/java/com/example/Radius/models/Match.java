package com.example.Radius.models;

import lombok.Data;

@Data
public class Match {
  private double totalMatchPercent;
  private double distanceMatchPercent;
  private double budgetMatchPercent;
  private double bedRoomsMatchPercent;
  private double bathRoomsMatchPercent;
  boolean isMatchPossible;
  String propertyId;
}
