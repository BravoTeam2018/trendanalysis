package com.cit.trendanalysis.controller;


import com.cit.trendanalysis.repository.AlertRepository;
import com.cit.trendanalysis.service.mqttsubscriber.AlertSubscriber;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
@Slf4j
public class MainController {

    @Autowired
    AlertSubscriber alertSubscriber;

    @Autowired
    AlertRepository alertRepository;

    @Scheduled(fixedDelay = 3000L)
    public void ensureConnected() {

        log.info("Checking the connection to MQTT Broker = {}", alertSubscriber.getConnectionString());

        if( ! alertSubscriber.isConnected() ) {
            try {
                alertSubscriber.connect();
            } catch (MqttException e) {
                log.error("Unable to connect to host {}, getting an exception {}",alertSubscriber.getConnectionString(),e);
            }
        } else {
            log.info("Connected OK to MQTT Broker = {}, topic = {}", alertSubscriber.getConnectionString(), alertSubscriber.getTopic());
        }
    }

}
