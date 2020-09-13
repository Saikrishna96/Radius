package com.example.Radius.searchparams;

import com.example.Radius.models.Match;
import com.example.Radius.models.Property;
import com.example.Radius.models.SearchRequirement;

public interface SearchParameter {
  int getPriority();

  void calculateMatch(SearchRequirement requirement, Property property, Match match);
}
