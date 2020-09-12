package com.example.Radius.controller;

import com.example.Radius.models.SearchRequirement;
import com.example.Radius.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("search-controller")
public class SearchController {
    @Autowired
    SearchService searchService;

    //Will print all the matches and its match percent in the console when hit with below curl request
    //curl "http://localhost:8080/search-controller/search?lat=18.1224&lon=79.1102&minBudget=10000&maxBudget=20000&minBeds=2&maxBeds=4&minBaths=2&maxBaths=3"
    @GetMapping(value = "search")
    public void getAllMatchesForGivenRequirement(@RequestParam("lat") double lat,
                                                        @RequestParam("lon") double lon,
                                                        @RequestParam("minBeds") int minBeds,
                                                        @RequestParam("maxBeds") int maxBeds,
                                                        @RequestParam("minBaths") int minBaths,
                                                        @RequestParam("maxBaths") int maxBaths,
                                                        @RequestParam("minBudget") BigDecimal minBudget,
                                                        @RequestParam("maxBudget") BigDecimal maxBudget) {
        System.out.println("Search request received.");
        SearchRequirement requirement = SearchRequirement.builder()
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
