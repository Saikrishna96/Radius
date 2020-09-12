package com.example.Radius.models;

import com.example.Radius.functions.MatchCalculatorFunction;
import com.example.Radius.utils.DistanceCalculator;
import com.example.Radius.utils.HelperUtils;
import lombok.Getter;

@Getter
public enum SearchParameters {
    //TODO If any new parameter comes up, it can be implemented here.
    // priority is used to sort the searchParameters in asc order of their priority.
    // function can evaluate the match percent given requirement and property data
    DISTANCE(1,
            (requirement, property, match) -> {
                System.out.println("***************************************************************");
                System.out.println("distance check");
                double matchPercent = 0.0;
                double distanceInMiles =
                        DistanceCalculator.distance(requirement.getLatitude(), requirement.getLongitude(), property.getLatitude(), property.getLongitude(), 'M');
                System.out.println("distance in miles : " + distanceInMiles);
                if (distanceInMiles <= 2)
                    matchPercent = 30.0;
                else if (distanceInMiles > 2 && distanceInMiles <= 5)
                    matchPercent = 25.0;
                else if (distanceInMiles > 5 && distanceInMiles < 10)
                    matchPercent = 20.0;

                if (matchPercent > 0.0) {
                    match.setMatchPossible(true);
                    match.setDistanceMatchPercent(matchPercent);
                    match.setTotalMatchPercent(match.getTotalMatchPercent() + matchPercent);
                } else {
                    match.setMatchPossible(false);
                }
                System.out.println("distance % :" + match.getDistanceMatchPercent());
                System.out.println("total match % till now : " + match.getTotalMatchPercent() + "\n");
            }),
    BUDGET(2,
            (requirement, property, match) -> {
                System.out.println("budget check");
                double matchPercent = 0.0;
                if (requirement.getMaxBudget() == null && requirement.getMinBudget() != null) {
                    if (HelperUtils.isValueInMinusPercent(10.0, requirement.getMinBudget(), property.getPrice()))
                        matchPercent = 30.0;
                    else if (HelperUtils.isValueInMinusPercent(20.0, requirement.getMinBudget(), property.getPrice()))
                        matchPercent = 25.0;
                    else if (HelperUtils.isValueInMinusPercent(25.0, requirement.getMinBudget(), property.getPrice()))
                        matchPercent = 20.0;

                } else if (requirement.getMinBudget() == null && requirement.getMaxBudget() != null) {
                    if (HelperUtils.isValueInPlusPercent(10.0, requirement.getMaxBudget(), property.getPrice()))
                        matchPercent = 30.0;
                    else if (HelperUtils.isValueInPlusPercent(20.0, requirement.getMaxBudget(), property.getPrice()))
                        matchPercent = 25.0;
                    else if (HelperUtils.isValueInPlusPercent(25.0, requirement.getMaxBudget(), property.getPrice()))
                        matchPercent = 20.0;
                } else {
                    if (property.getPrice().compareTo(requirement.getMinBudget()) >= 0 &&
                            property.getPrice().compareTo(requirement.getMaxBudget()) <= 0) {
                        matchPercent = 30.0;
                    } else {
                        if (HelperUtils.isValueInPlusPercent(10.0, requirement.getMaxBudget(), property.getPrice())
                                || HelperUtils.isValueInMinusPercent(10.0, requirement.getMinBudget(), property.getPrice()))
                            matchPercent = 25.0;
                        else if (HelperUtils.isValueInPlusPercent(20.0, requirement.getMaxBudget(), property.getPrice())
                                || HelperUtils.isValueInMinusPercent(20.0, requirement.getMinBudget(), property.getPrice()))
                            matchPercent = 20.0;
                        else if (HelperUtils.isValueInPlusPercent(25.0, requirement.getMaxBudget(), property.getPrice())
                                || HelperUtils.isValueInMinusPercent(25.0, requirement.getMinBudget(), property.getPrice()))
                            matchPercent = 15.0;
                    }
                }
                if (matchPercent > 0.0) {
                    match.setMatchPossible(true);
                    match.setBudgetMatchPercent(matchPercent);
                    match.setTotalMatchPercent(match.getTotalMatchPercent() + matchPercent);
                } else {
                    match.setMatchPossible(false);
                }
                System.out.println("budget % :" + match.getBudgetMatchPercent());
                System.out.println("total match % till now : " + match.getTotalMatchPercent() + "\n");
            }),
    BEDROOMS(3,
            (requirement, property, match) -> {
                System.out.println("bedrooms check");
                double matchPercent = 0.0;
                if (requirement.getMaxNoOfBedRooms() == -1 && requirement.getMinNoOfBedRooms() != -1) {
                    if (HelperUtils.isValueInMinusLimit(1, requirement.getMinNoOfBedRooms(), property.getNoOfBedRooms()))
                        matchPercent = 20.0;
                    else if (HelperUtils.isValueInMinusLimit(2, requirement.getMinNoOfBedRooms(), property.getNoOfBedRooms()))
                        matchPercent = 15.0;
                } else if (requirement.getMinNoOfBedRooms() == -1 && requirement.getMaxNoOfBedRooms() != -1) {
                    if (HelperUtils.isValueInPlusLimit(1, requirement.getMaxNoOfBedRooms(), property.getNoOfBedRooms()))
                        matchPercent = 20.0;
                    else if (HelperUtils.isValueInPlusLimit(2, requirement.getMaxNoOfBedRooms(), property.getNoOfBedRooms()))
                        matchPercent = 15.0;
                } else {
                    if (property.getNoOfBedRooms() >= requirement.getMinNoOfBedRooms() &&
                            property.getNoOfBedRooms() <= requirement.getMaxNoOfBedRooms()) {
                        matchPercent = 20.0;
                    } else {
                        if (HelperUtils.isValueInMinusLimit(1, requirement.getMinNoOfBedRooms(), property.getNoOfBedRooms()) ||
                                HelperUtils.isValueInPlusLimit(1, requirement.getMaxNoOfBedRooms(), property.getNoOfBedRooms()))
                            matchPercent = 15.0;
                        else if (HelperUtils.isValueInMinusLimit(2, requirement.getMinNoOfBedRooms(), property.getNoOfBedRooms()) ||
                                HelperUtils.isValueInPlusLimit(2, requirement.getMaxNoOfBedRooms(), property.getNoOfBedRooms()))
                            matchPercent = 10.0;
                    }
                }
                if (matchPercent > 0.0) {
                    match.setMatchPossible(true);
                    match.setBedRoomsMatchPercent(matchPercent);
                    match.setTotalMatchPercent(match.getTotalMatchPercent() + matchPercent);
                } else {
                    match.setMatchPossible(false);
                }
                System.out.println("bedrooms % :" + match.getBedRoomsMatchPercent());
                System.out.println("total match % till now : " + match.getTotalMatchPercent() + "\n");
            }),
    BATHROOMS(4,
            (requirement, property, match) -> {
                System.out.println("bathrooms check");
                double matchPercent = 0.0;
                if (requirement.getMaxNoOfBathRooms() == -1 && requirement.getMinNoOfBathRooms() != -1) {
                    if (HelperUtils.isValueInMinusLimit(1, requirement.getMinNoOfBathRooms(), property.getNoOfBathRooms()))
                        matchPercent = 20.0;
                    else if (HelperUtils.isValueInMinusLimit(2, requirement.getMinNoOfBathRooms(), property.getNoOfBathRooms()))
                        matchPercent = 15.0;
                } else if (requirement.getMinNoOfBathRooms() == -1 && requirement.getMaxNoOfBathRooms() != -1) {
                    if (HelperUtils.isValueInPlusLimit(1, requirement.getMaxNoOfBathRooms(), property.getNoOfBathRooms()))
                        matchPercent = 20.0;
                    else if (HelperUtils.isValueInPlusLimit(2, requirement.getMaxNoOfBathRooms(), property.getNoOfBathRooms()))
                        matchPercent = 15.0;
                } else {
                    if (property.getNoOfBathRooms() >= requirement.getMinNoOfBathRooms() &&
                            property.getNoOfBathRooms() <= requirement.getMaxNoOfBathRooms()) {
                        matchPercent = 20.0;
                    } else {
                        if (HelperUtils.isValueInMinusLimit(1, requirement.getMinNoOfBathRooms(), property.getNoOfBathRooms()) ||
                                HelperUtils.isValueInPlusLimit(1, requirement.getMaxNoOfBathRooms(), property.getNoOfBathRooms()))
                            matchPercent = 15.0;
                        else if (HelperUtils.isValueInMinusLimit(2, requirement.getMinNoOfBathRooms(), property.getNoOfBathRooms()) ||
                                HelperUtils.isValueInPlusLimit(2, requirement.getMaxNoOfBathRooms(), property.getNoOfBathRooms()))
                            matchPercent = 10.0;
                    }
                }
                if (matchPercent > 0.0) {
                    match.setMatchPossible(true);
                    match.setBathRoomsMatchPercent(matchPercent);
                    match.setTotalMatchPercent(match.getTotalMatchPercent() + matchPercent);
                } else {
                    match.setMatchPossible(false);
                }
                System.out.println("bathrooms % :" + match.getBathRoomsMatchPercent());
                System.out.println("total match % till now: " + match.getTotalMatchPercent() + "\n");
            });

    private int priority;
    private MatchCalculatorFunction<SearchRequirement, Property, Match> function;

    SearchParameters(int priority, MatchCalculatorFunction<SearchRequirement, Property, Match> function) {
        this.function = function;
        this.priority = priority;
    }


}
