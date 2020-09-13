package com.example.Radius.utils;

// Calculates the distance based on given pair of latitude, longitude and unit
public class DistanceCalculator {
  public static double distance(double lat1, double lon1, double lat2, double lon2, char unit) {
    double theta = lon1 - lon2;
    double dist =
        Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))
            + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
    dist = Math.acos(dist);
    dist = rad2deg(dist);
    dist = dist * 60 * 1.1515;
    if (unit == 'K') {
      dist = dist * 1.609344;
    } else if (unit == 'N') {
      dist = dist * 0.8684;
    }
    return (dist);
  }

  /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
  /*::  This function converts decimal degrees to radians             :*/
  /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
  private static double deg2rad(double deg) {
    return (deg * Math.PI / 180.0);
  }

  /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
  /*::  This function converts radians to decimal degrees             :*/
  /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
  private static double rad2deg(double rad) {
    return (rad * 180.0 / Math.PI);
  }

  public static void main(String[] args) {
    System.out.println(distance(18.1124, 79.1192, 18.1224, 79.1102, 'M') + " Miles\n");
    System.out.println(
        distance(18.2124, 78.01929969999999, 18.1224372, 79.01929969999999, 'K') + " Kilometers\n");
    System.out.println(
        distance(32.9697, -96.80322, 29.46786, -98.53506, 'N') + " Nautical Miles\n");
  }
}
