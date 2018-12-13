package com.cit.trendanalysis.service.mqttsubscriber;

import com.cit.trendanalysis.model.Alert;
import com.cit.trendanalysis.repository.AlertRepository;
import com.cit.trendanalysis.utility.JsonUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AlertHandler implements MessageListener{
    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(AlertHandler.class);

    private AlertSubscriber sub;
    private AlertRepository repo;


    @Autowired
    public AlertHandler(AlertSubscriber sub, AlertRepository repo) throws IOException {

        this.sub=sub;
        this.repo=repo;

        this.sub.setMessageListener(this);


    }

    @Override
    public void messageReceived(String message) {
        try {
            LOGGER.info("Send new message to elastic {}", message);

            Alert alert = JsonUtility.fromJsonStringToAlert(message);

            repo.insertAlert(alert);

        } catch (IOException e) {
            LOGGER.error("Error when sending message to elastic", e);
        }
    }

}
