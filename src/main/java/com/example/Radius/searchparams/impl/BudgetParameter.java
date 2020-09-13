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
public class BudgetParameter implements SearchParameter {

  @Override
  public int getPriority() {
    return 2;
  }

  @Override
  public void calculateMatch(SearchRequirement requirement, Property property, Match match) {
    log.info("Budget check initiated.");
    double matchPercent = 0.0;
    if (requirement.getMaxBudget() == null && requirement.getMinBudget() != null) {
      if (HelperUtils.isValueInMinusPercent(10.0, requirement.getMinBudget(), property.getPrice()))
        matchPercent = 30.0;
      else if (HelperUtils.isValueInMinusPercent(
          20.0, requirement.getMinBudget(), property.getPrice())) matchPercent = 25.0;
      else if (HelperUtils.isValueInMinusPercent(
          25.0, requirement.getMinBudget(), property.getPrice())) matchPercent = 20.0;

    } else if (requirement.getMinBudget() == null && requirement.getMaxBudget() != null) {
      if (HelperUtils.isValueInPlusPercent(10.0, requirement.getMaxBudget(), property.getPrice()))
        matchPercent = 30.0;
      else if (HelperUtils.isValueInPlusPercent(
          20.0, requirement.getMaxBudget(), property.getPrice())) matchPercent = 25.0;
      else if (HelperUtils.isValueInPlusPercent(
          25.0, requirement.getMaxBudget(), property.getPrice())) matchPercent = 20.0;
    } else {
      if (property.getPrice().compareTo(requirement.getMinBudget()) >= 0
          && property.getPrice().compareTo(requirement.getMaxBudget()) <= 0) {
        matchPercent = 30.0;
      } else {
        if (HelperUtils.isValueInPlusPercent(10.0, requirement.getMaxBudget(), property.getPrice())
            || HelperUtils.isValueInMinusPercent(
                10.0, requirement.getMinBudget(), property.getPrice())) matchPercent = 25.0;
        else if (HelperUtils.isValueInPlusPercent(
                20.0, requirement.getMaxBudget(), property.getPrice())
            || HelperUtils.isValueInMinusPercent(
                20.0, requirement.getMinBudget(), property.getPrice())) matchPercent = 20.0;
        else if (HelperUtils.isValueInPlusPercent(
                25.0, requirement.getMaxBudget(), property.getPrice())
            || HelperUtils.isValueInMinusPercent(
                25.0, requirement.getMinBudget(), property.getPrice())) matchPercent = 15.0;
      }
    }
    if (matchPercent > 0.0) {
      match.setMatchPossible(true);
      match.setBudgetMatchPercent(matchPercent);
      match.setTotalMatchPercent(match.getTotalMatchPercent() + matchPercent);
    } else {
      match.setMatchPossible(false);
    }
    log.info("Budget Match % : {}", match.getBudgetMatchPercent());
    log.info("Total Match % till now : {} \n", match.getTotalMatchPercent());
  }
}
