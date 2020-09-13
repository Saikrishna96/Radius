package com.example.Radius.searchparams.impl;

import com.example.Radius.models.Match;
import com.example.Radius.models.Property;
import com.example.Radius.models.SearchRequirement;
import com.example.Radius.searchparams.SearchParameter;
import com.example.Radius.utils.HelperUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BedRoomParameter implements SearchParameter {

  @Override
  public int getPriority() {
    return 3;
  }

  @Override
  public void calculateMatch(SearchRequirement requirement, Property property, Match match) {
    log.info("Bedrooms check initiated.");
    double matchPercent = 0.0;
    if (requirement.getMaxNoOfBedRooms() == -1 && requirement.getMinNoOfBedRooms() != -1) {
      if (HelperUtils.isValueInMinusLimit(
          1, requirement.getMinNoOfBedRooms(), property.getNoOfBedRooms())) matchPercent = 20.0;
      else if (HelperUtils.isValueInMinusLimit(
          2, requirement.getMinNoOfBedRooms(), property.getNoOfBedRooms())) matchPercent = 15.0;
    } else if (requirement.getMinNoOfBedRooms() == -1 && requirement.getMaxNoOfBedRooms() != -1) {
      if (HelperUtils.isValueInPlusLimit(
          1, requirement.getMaxNoOfBedRooms(), property.getNoOfBedRooms())) matchPercent = 20.0;
      else if (HelperUtils.isValueInPlusLimit(
          2, requirement.getMaxNoOfBedRooms(), property.getNoOfBedRooms())) matchPercent = 15.0;
    } else {
      if (property.getNoOfBedRooms() >= requirement.getMinNoOfBedRooms()
          && property.getNoOfBedRooms() <= requirement.getMaxNoOfBedRooms()) {
        matchPercent = 20.0;
      } else {
        if (HelperUtils.isValueInMinusLimit(
                1, requirement.getMinNoOfBedRooms(), property.getNoOfBedRooms())
            || HelperUtils.isValueInPlusLimit(
                1, requirement.getMaxNoOfBedRooms(), property.getNoOfBedRooms()))
          matchPercent = 15.0;
        else if (HelperUtils.isValueInMinusLimit(
                2, requirement.getMinNoOfBedRooms(), property.getNoOfBedRooms())
            || HelperUtils.isValueInPlusLimit(
                2, requirement.getMaxNoOfBedRooms(), property.getNoOfBedRooms()))
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
    log.info("Bedrooms Match % : {}", match.getBedRoomsMatchPercent());
    log.info("Total Match % till now : {} \n", match.getTotalMatchPercent());
  }
}
