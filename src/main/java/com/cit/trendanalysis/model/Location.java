package com.cit.trendanalysis.model;


import com.fasterxml.jackson.annotation.JsonInclude;

@lombok.Data
@lombok.Builder
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Location {

    // GPS coordinates of panel
    private GPSCoordinate coordinates;

    // altitude above sea level
    private int altitude;

    // the 'relative' location of the panel in free form text
    private String relativeLocation;
}
