package com.example.Radius.services;

import com.example.Radius.models.Match;
import com.example.Radius.models.SearchRequirement;
import java.util.List;

public interface SearchService {
  List<Match> getAllMatchesForGivenRequirement(SearchRequirement requirement);
}
