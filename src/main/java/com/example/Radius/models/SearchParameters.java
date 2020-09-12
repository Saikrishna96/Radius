package com.example.Radius.models;

import com.example.Radius.functions.MatchCalculatorFunction;
import com.example.Radius.utils.DistanceCalculator;
import com.example.Radius.utils.HelperUtils;
import lombok.Getter;

@Getter
public enum SearchParameters {

    DISTANCE(1,
            (requirement, property, match) -> {
                System.out.println("distance check");
                double matchPercent = 0.0;
                match.setMatchPossible(true);
                double distanceInMiles =
                        DistanceCalculator.distance(requirement.getLatitude(), requirement.getLongitude(), property.getLatitude(), property.getLongitude(), 'M');
                System.out.println("distance in miles : " + distanceInMiles);
                if (distanceInMiles <= 2)
                    matchPercent = 30.0;
                else if (distanceInMiles > 2 && distanceInMiles <= 5)
                    matchPercent = 25.0;
                else if (distanceInMiles > 5 && distanceInMiles < 10)
                    matchPercent = 20.0;
                else {
                    match.setMatchPossible(false);
                    return;
                }
                match.setDistanceMatchPercent(matchPercent);
                match.setTotalMatchPercent(match.getTotalMatchPercent() + matchPercent);
                System.out.println("distance % :" + match.getDistanceMatchPercent());
                System.out.println("total match % : " + match.getTotalMatchPercent());
            }),
    BUDGET(2,
            (requirement, property, match) -> {
                System.out.println("budget check");
                match.setMatchPossible(true);
                double matchPercent = 0.0;
                if (requirement.getMaxBudget() == null && requirement.getMinBudget() != null) {
                    if (HelperUtils.isValueInMinusPercent(10.0, requirement.getMinBudget(), property.getPrice()))
                        matchPercent = 30.0;
                    else if (HelperUtils.isValueInMinusPercent(20.0, requirement.getMinBudget(), property.getPrice()))
                        matchPercent = 25.0;
                    else if (HelperUtils.isValueInMinusPercent(25.0, requirement.getMinBudget(), property.getPrice()))
                        matchPercent = 20.0;
                    else
                        match.setMatchPossible(false);
                } else if (requirement.getMinBudget() == null && requirement.getMaxBudget() != null) {
                    if (HelperUtils.isValueInPlusPercent(10.0, requirement.getMaxBudget(), property.getPrice()))
                        matchPercent = 30.0;
                    else if (HelperUtils.isValueInPlusPercent(20.0, requirement.getMaxBudget(), property.getPrice()))
                        matchPercent = 25.0;
                    else if (HelperUtils.isValueInPlusPercent(25.0, requirement.getMaxBudget(), property.getPrice()))
                        matchPercent = 10.0;
                    else
                        match.setMatchPossible(false);
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
                        else
                            match.setMatchPossible(false);
                    }
                }
                match.setBudgetMatchPercent(matchPercent);
                match.setTotalMatchPercent(match.getTotalMatchPercent() + matchPercent);
                System.out.println("budget % :" + match.getDistanceMatchPercent());
                System.out.println("total match % : " + match.getTotalMatchPercent());
            });

    private int priority;
    private MatchCalculatorFunction<SearchRequirement, Property, Match> function;

    SearchParameters(int priority, MatchCalculatorFunction<SearchRequirement, Property, Match> function) {
        this.function = function;
        this.priority = priority;
    }


}
