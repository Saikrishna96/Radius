package com.example.Radius.services.impl;

import com.example.Radius.models.Match;
import com.example.Radius.models.Property;
import com.example.Radius.models.SearchParameters;
import com.example.Radius.models.SearchRequirement;
import com.example.Radius.repository.PropertyRepository;
import com.example.Radius.repository.impl.PropertyRepositoryImpl;
import com.example.Radius.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchServiceImpl implements SearchService {

//    @Autowired
//    PropertyRepository propertyRepository;

    @Override
    public List<Match> getAllMatchesForGivenRequirement(SearchRequirement requirement) {
//        List<Property> properties = propertyRepository.getAllRadiusProperties();

        List<Property> properties = new PropertyRepositoryImpl().getAllRadiusProperties(); // created this object for main method testing, not required for springboot run

        List<Match> allMatchesForCurrentSearch = new ArrayList<>();
        SearchParameters[] searchParameters = SearchParameters.values();

        //SearchParameters sorted according to their priority
        Arrays.sort(searchParameters, Comparator.comparing(SearchParameters::getPriority));

        properties.forEach(property -> {
            Match match = new Match();
            for (SearchParameters parameter : searchParameters) {
                parameter.getFunction().calculateMatch(requirement, property, match);
                if (!match.isMatchPossible())
                    break;
            }
            if (match.isMatchPossible()) {
                match.setPropertyId(property.getPropertyId());
                allMatchesForCurrentSearch.add(match);
            }
        });

        System.out.println("***************************************************************");
        System.out.println("Total No of matches : " + allMatchesForCurrentSearch.size() + "\n");
        if (allMatchesForCurrentSearch.size() == 0) {
            System.out.println("No property matched with the given requirement");
            return null;
        }

        allMatchesForCurrentSearch.forEach(match -> {
            System.out.println("Match percent : " + match.getTotalMatchPercent());
            System.out.println("Match possible : " + match.isMatchPossible());
            System.out.println("Id of the matched property : " + match.getPropertyId());
            System.out.println();
        });

        List<Match> usefulMatches = allMatchesForCurrentSearch
                .stream()
                .filter(match -> match.getTotalMatchPercent() > 40)
                .collect(Collectors.toList());
        System.out.println("***************************************************************");
        System.out.println("Total No of matches with match percent > 40% : " + usefulMatches.size());
        return usefulMatches;
    }

    // Main method for testing purposes
    public static void main(String[] args) {
        SearchServiceImpl searchService = new SearchServiceImpl();
        SearchRequirement requirement = SearchRequirement.builder()
                .latitude(18.1224)
                .longitude(79.1102)
                .minNoOfBedRooms(2)
                .maxNoOfBedRooms(1)
                .minNoOfBathRooms(3)
                .maxNoOfBathRooms(4)
                .maxBudget(new BigDecimal(20000))
                .minBudget(new BigDecimal(10000))
                .build();

        searchService.getAllMatchesForGivenRequirement(requirement);
    }
}
