package com.cit.trendanalysis.model;

@lombok.Data
@lombok.Builder
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
public class GPSCoordinate
{

    // GPS latitude, in decimal format
    private double latitude;

    // GPS longitude, in decimal format
    private double longitude;

}