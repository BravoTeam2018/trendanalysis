package com.cit.trendanalysis.model;

import java.util.UUID;

@lombok.Data
@lombok.Builder
public class Alert {
    private String id;
    private String severity;
    private String title;
    private String description;
    private Event currentEvent;
    private Event previousEvent;
}
