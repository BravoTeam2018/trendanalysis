package com.cit.trendanalysis.model;


@lombok.Data
@lombok.Builder
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
public class Event {
    private long timestamp;
    private String cardId;
    private String panelId;
    private Location location;
    private boolean accessAllowed;
}

