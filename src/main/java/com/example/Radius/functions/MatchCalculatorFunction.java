package com.example.Radius.functions;


@FunctionalInterface
public interface MatchCalculatorFunction<SearchRequirement, Property, Match> {
    void calculateMatch(SearchRequirement requirement, Property property, Match match);
}
