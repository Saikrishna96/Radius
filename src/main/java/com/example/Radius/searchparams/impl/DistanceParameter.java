package com.example.Radius.searchparams.impl;

import com.example.Radius.models.Match;
import com.example.Radius.models.Property;
import com.example.Radius.models.SearchRequirement;
import com.example.Radius.searchparams.SearchParameter;
import com.example.Radius.utils.DistanceCalculator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DistanceParameter implements SearchParameter {

  @Override
  public int getPriority() {
    return 1;
  }

  @Override
  public void calculateMatch(SearchRequirement requirement, Property property, Match match) {
    log.info("******************************************");
    log.info("Distance check initiated");
    double matchPercent = 0.0;
    double distanceInMiles =
        DistanceCalculator.distance(
            requirement.getLatitude(),
            requirement.getLongitude(),
            property.getLatitude(),
            property.getLongitude(),
            'M');
    log.info("Distance in miles : {}", distanceInMiles);
    if (distanceInMiles <= 2) matchPercent = 30.0;
    else if (distanceInMiles > 2 && distanceInMiles <= 5) matchPercent = 25.0;
    else if (distanceInMiles > 5 && distanceInMiles < 10) matchPercent = 20.0;

    if (matchPercent > 0.0) {
      match.setMatchPossible(true);
      match.setDistanceMatchPercent(matchPercent);
      match.setTotalMatchPercent(match.getTotalMatchPercent() + matchPercent);
    } else {
      match.setMatchPossible(false);
    }
    log.info("Distance match % : {}", match.getDistanceMatchPercent());
    log.info("Total Match % till now : {} \n", match.getTotalMatchPercent());
  }
}
