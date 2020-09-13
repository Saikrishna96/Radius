package com.example.Radius.services.impl;

import com.example.Radius.models.Match;
import com.example.Radius.models.Property;
import com.example.Radius.models.SearchRequirement;
import com.example.Radius.repository.PropertyRepository;
import com.example.Radius.searchparams.SearchParameter;
import com.example.Radius.services.SearchService;
import java.util.*;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SearchServiceImpl implements SearchService {

  private final List<SearchParameter> searchParameters;
  private final PropertyRepository propertyRepository;

  SearchServiceImpl(List<SearchParameter> parameters, PropertyRepository propertyRepository) {
    this.searchParameters = parameters;
    this.propertyRepository = propertyRepository;
  }

  @Override
  public List<Match> getAllMatchesForGivenRequirement(SearchRequirement requirement) {
    List<Property> properties = propertyRepository.getAllRadiusProperties();

    // Sorting searchParameters according to the assumed priority.
    Collections.sort(searchParameters, Comparator.comparing(SearchParameter::getPriority));

    List<Match> allMatchesForCurrentSearch = new ArrayList<>();

    properties.forEach(
        property -> {
          Match match = new Match();
          for (SearchParameter parameter : searchParameters) {
            parameter.calculateMatch(requirement, property, match);
            if (!match.isMatchPossible()) break;
          }
          if (match.isMatchPossible()) {
            match.setPropertyId(property.getPropertyId());
            allMatchesForCurrentSearch.add(match);
          }
        });

    log.info("******************************************");
    log.info("Total No of matches : {} \n", allMatchesForCurrentSearch.size());
    if (allMatchesForCurrentSearch.size() == 0) {
      log.info("No property matched with the given requirement. \n");
      return null;
    }

    allMatchesForCurrentSearch.forEach(
        match -> {
          log.info("Match percent : {}", match.getTotalMatchPercent());
          log.info("Match possible : {}", match.isMatchPossible());
          log.info("Id of the matched property : {} \n", match.getPropertyId());
        });

    List<Match> usefulMatches =
        allMatchesForCurrentSearch
            .stream()
            .filter(match -> match.getTotalMatchPercent() > 40)
            .collect(Collectors.toList());

    log.info("******************************************");
    log.info("No of matches with MatchPercent > 40% : {}", usefulMatches.size());
    log.info("******************************************");
    return usefulMatches;
  }
}
