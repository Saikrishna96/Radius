package com.example.Radius.services.impl;

import com.example.Radius.models.Match;
import com.example.Radius.models.Property;
import com.example.Radius.models.SearchParameters;
import com.example.Radius.models.SearchRequirement;
import com.example.Radius.services.SearchService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SearchServiceImpl implements SearchService {

    public static void main(String[] args){
        List<Property> properties = new ArrayList<>();
        properties.add(Property.builder()
                .latitude(18.1124)
                .longitude(79.1192)
                .price(new BigDecimal(20000))
                .noOfBedRooms(3)
                .noOfBathRooms(2)
                .build());

        //18.1124, 79.1192, 18.1224, 79.1102 => 0.909 Miles
        SearchRequirement requirement = SearchRequirement.builder()
                .latitude(18.1224)
                .longitude(79.1102)
                .minNoOfBedRooms(1)
                .maxNoOfBedRooms(3)
                .minNoOfBathRooms(1)
                .maxNoOfBathRooms(3)
                .maxBudget(new BigDecimal(30000))
                .minBudget(new BigDecimal(10000))
                .build();

        Match match = new Match();
        properties.forEach(property -> {
            for (SearchParameters parameter : SearchParameters.values()){
                parameter.getFunction().calculateMatch(requirement, property, match);
                if (!match.isMatchPossible())
                    break;
            }
        });

        System.out.println("match.getTotalMatchPercent() : " + match.getTotalMatchPercent());
        System.out.println("match.isMatchPossible() : " + match.isMatchPossible());


    }
}
