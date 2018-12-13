package com.cit.trendanalysis.repository;

import com.cit.trendanalysis.model.Alert;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@Repository
public class AlertRepository {

    private final String INDEX = "alertdata";
    private final String TYPE = "alerts";

    private RestHighLevelClient restHighLevelClient;
    private ObjectMapper objectMapper;


    public AlertRepository(ObjectMapper objectMapper, RestHighLevelClient restHighLevelClient) {
        this.objectMapper = objectMapper;
        this.restHighLevelClient = restHighLevelClient;

    }

    public void insertAlert(Alert alert) throws IOException {

        alert.setId(UUID.randomUUID().toString());

        XContentBuilder builder = XContentFactory.jsonBuilder()
                .startObject()
                .field("severity", alert.getSeverity())
                .field("title", alert.getTitle())
                .field("description", alert.getDescription())
                .startObject("currentEvent")
                .field("timestamp", alert.getCurrentEvent().getTimestamp())
                .field("cardId", alert.getCurrentEvent().getCardId())
                .field("panelId", alert.getCurrentEvent().getPanelId())
                .startObject("location")
                .startObject("coordinates")
                .field("lat", alert.getCurrentEvent().getLocation().getCoordinates().getLatitude())
                .field("lon", alert.getCurrentEvent().getLocation().getCoordinates().getLongitude())
                .endObject()
                .field("altitude", alert.getCurrentEvent().getLocation().getAltitude())
                .field("relativeLocation", alert.getCurrentEvent().getLocation().getRelativeLocation())
                .endObject()
                .field("accessAllowed", alert.getCurrentEvent().isAccessAllowed())
                .endObject()
                .startObject("previousEvent")
                .field("timestamp", alert.getPreviousEvent().getTimestamp())
                .field("cardId", alert.getPreviousEvent().getCardId())
                .field("panelId", alert.getPreviousEvent().getPanelId())
                .startObject("location")
                .startObject("coordinates")
                .field("lat", alert.getPreviousEvent().getLocation().getCoordinates().getLatitude())
                .field("lon", alert.getPreviousEvent().getLocation().getCoordinates().getLongitude())
                .endObject()
                .field("altitude", alert.getPreviousEvent().getLocation().getAltitude())
                .field("relativeLocation", alert.getPreviousEvent().getLocation().getRelativeLocation())
                .endObject()
                .field("accessAllowed", alert.getPreviousEvent().isAccessAllowed())
                .endObject()
                .endObject();

        try {

            IndexRequest indexRequest = new IndexRequest(INDEX, TYPE, alert.getId())
                    .source(builder);

            IndexResponse response = restHighLevelClient.index(indexRequest);

            log.debug("sending to elasticserch {}", builder.toString());

        } catch (ElasticsearchException e) {
            log.error("elastic search insert error {}", e);

        }
    }
}
/*


PUT alertdata/_mapping/alerts
{
  "properties": {
    "timestamp": {
      "type" : "date",
      "format" : "epoch_millis"
    },
    "coordinates" : {
      "type" : "geo_point"
    }

  }
}

*/

