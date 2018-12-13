package com.cit.trendanalysis.repository;

import com.cit.trendanalysis.model.Alert;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.UUID;



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

    public Alert insertAlert(Alert alert) {
        alert.setId(UUID.randomUUID().toString());
        Map<String, Object> dataMap = objectMapper.convertValue(alert, Map.class);
        IndexRequest indexRequest = new IndexRequest(INDEX, TYPE, alert.getId())
                .source(dataMap);
        try {
            IndexResponse response = restHighLevelClient.index(indexRequest);
        } catch (ElasticsearchException e) {
            e.getDetailedMessage();
        } catch (java.io.IOException ex) {
            ex.getLocalizedMessage();
        }
        return alert;
    }

}