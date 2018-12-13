package com.cit.trendanalysis.model;



@lombok.Data
@lombok.Builder
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
public class Alert {
    private String id;
    private String severity;
    private String title;
    private String description;
    private Event currentEvent;
    private Event previousEvent;
}
