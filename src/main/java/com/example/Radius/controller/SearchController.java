package com.example.Radius.controller;

import com.example.Radius.models.SearchRequirement;
import com.example.Radius.services.SearchService;
import java.math.BigDecimal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("search-controller")
@Slf4j
public class SearchController {
  @Autowired SearchService searchService;

  // Will print all the matches and its match percent in the console when hit with below request
  // curl
  // "http://localhost:8080/search-controller/search?lat=18.1224&lon=79.1102&minBudget=10000&maxBudget=20000&minBeds=2&maxBeds=4&minBaths=2&maxBaths=3"
  @GetMapping(value = "search")
  // TODO default for unncessary params
  public void getAllMatchesForGivenRequirement(
      @RequestParam("lat") double lat,
      @RequestParam("lon") double lon,
      @RequestParam(value = "minBeds", required = false, defaultValue = "-1") int minBeds,
      @RequestParam(value = "maxBeds", required = false, defaultValue = "-1") int maxBeds,
      @RequestParam(value = "minBaths", required = false, defaultValue = "-1") int minBaths,
      @RequestParam(value = "maxBaths", required = false, defaultValue = "-1") int maxBaths,
      @RequestParam(value = "minBudget", required = false) BigDecimal minBudget,
      @RequestParam(value = "maxBudget", required = false) BigDecimal maxBudget) {
    log.info(
        "Request received lat : {}, long : {}, minBeds : {}, "
            + "maxBeds : {}, minBaths : {}, "
            + "maxBaths : {}, minBudget : {}, maxBudget : {}",
        lat,
        lon,
        minBeds,
        maxBeds,
        minBaths,
        maxBaths,
        minBudget,
        maxBudget);
    SearchRequirement requirement =
        SearchRequirement.builder()
            .latitude(lat)
            .longitude(lon)
            .minNoOfBedRooms(minBeds)
            .maxNoOfBedRooms(maxBeds)
            .minNoOfBathRooms(minBaths)
            .maxNoOfBathRooms(maxBaths)
            .maxBudget(maxBudget)
            .minBudget(minBudget)
            .build();
    searchService.getAllMatchesForGivenRequirement(requirement);
  }
}
