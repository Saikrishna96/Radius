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
public class BathRoomParameter implements SearchParameter {

  @Override
  public int getPriority() {
    return 4;
  }

  @Override
  public void calculateMatch(SearchRequirement requirement, Property property, Match match) {
    log.info("Bathrooms check initiated.");
    double matchPercent = 0.0;
    if (requirement.getMaxNoOfBathRooms() == -1 && requirement.getMinNoOfBathRooms() != -1) {
      if (HelperUtils.isValueInMinusLimit(
          1, requirement.getMinNoOfBathRooms(), property.getNoOfBathRooms())) matchPercent = 20.0;
      else if (HelperUtils.isValueInMinusLimit(
          2, requirement.getMinNoOfBathRooms(), property.getNoOfBathRooms())) matchPercent = 15.0;
    } else if (requirement.getMinNoOfBathRooms() == -1 && requirement.getMaxNoOfBathRooms() != -1) {
      if (HelperUtils.isValueInPlusLimit(
          1, requirement.getMaxNoOfBathRooms(), property.getNoOfBathRooms())) matchPercent = 20.0;
      else if (HelperUtils.isValueInPlusLimit(
          2, requirement.getMaxNoOfBathRooms(), property.getNoOfBathRooms())) matchPercent = 15.0;
    } else {
      if (property.getNoOfBathRooms() >= requirement.getMinNoOfBathRooms()
          && property.getNoOfBathRooms() <= requirement.getMaxNoOfBathRooms()) {
        matchPercent = 20.0;
      } else {
        if (HelperUtils.isValueInMinusLimit(
                1, requirement.getMinNoOfBathRooms(), property.getNoOfBathRooms())
            || HelperUtils.isValueInPlusLimit(
                1, requirement.getMaxNoOfBathRooms(), property.getNoOfBathRooms()))
          matchPercent = 15.0;
        else if (HelperUtils.isValueInMinusLimit(
                2, requirement.getMinNoOfBathRooms(), property.getNoOfBathRooms())
            || HelperUtils.isValueInPlusLimit(
                2, requirement.getMaxNoOfBathRooms(), property.getNoOfBathRooms()))
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
    log.info("Bathrooms Match % : {}", match.getBathRoomsMatchPercent());
    log.info("Total Match % till now : {} \n", match.getTotalMatchPercent());
  }
}
