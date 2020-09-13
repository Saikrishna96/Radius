package com.example.Radius.repository.impl;

import com.example.Radius.models.Property;
import com.example.Radius.repository.PropertyRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class PropertyRepositoryImpl implements PropertyRepository {

  @Override
  public List<Property> getAllRadiusProperties() {
    // TODO Ideally should be fetched from DB using persistence layer
    List<Property> listOfProperties = new ArrayList<>();
    listOfProperties.add(
        Property.builder()
            .propertyId("1")
            .latitude(18.1124)
            .longitude(79.1192)
            .price(new BigDecimal(20000))
            .noOfBedRooms(3)
            .noOfBathRooms(2)
            .build());

    listOfProperties.add(
        Property.builder()
            .propertyId("2")
            .latitude(18.2124)
            .longitude(79.1192)
            .price(new BigDecimal(20000))
            .noOfBedRooms(2)
            .noOfBathRooms(1)
            .build());

    listOfProperties.add(
        Property.builder()
            .propertyId("3")
            .latitude(18.3124)
            .longitude(78.1192)
            .price(new BigDecimal(10000))
            .noOfBedRooms(1)
            .noOfBathRooms(1)
            .build());

    return listOfProperties;
  }
}
