package com.cit.trendanalysis.service.mqttsubscriber;

import com.cit.trendanalysis.config.MQTTBrokerConfig;
import org.eclipse.paho.client.mqttv3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Service
public class AlertSubscriber implements MqttCallback {
    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(AlertSubscriber.class);
    private MQTTBrokerConfig config;
    private Queue<String> queue = new LinkedList<>();
    private MqttClient mqttClient;
    private MessageListener listener;

    @Autowired
    public AlertSubscriber(MQTTBrokerConfig config) {
        this.config = config;
    }

    @Scheduled(fixedDelay = 3000L)
    public void ensureConnected() {

        if( ! mqttClient.isConnected() ) {
            try {
                this.connect();
            } catch (MqttException e) {
                LOGGER.error("Unable to connect to host {}, getting an exception {}",this.getConnectionString(),e);
            }
        }
    }

    private void connect() throws MqttException {

        this.mqttClient = new MqttClient(config.getConnectionString(), "client1");
        MqttConnectOptions connOpts = new MqttConnectOptions();
        connOpts.setCleanSession(true);
        LOGGER.info("Connecting to broker: {}", config.getConnectionString());
        this.mqttClient.connect(connOpts);
        LOGGER.info("Connected to {}", config.getConnectionString());
        this.mqttClient.setCallback(this);
        this.mqttClient.subscribe(getTopic(), 0);
        LOGGER.info("Subscribed to {}", getTopic());
    }

    public void setMessageListener(MessageListener listener) {
        this.listener = listener;
    }

    @Override
    public void connectionLost(Throwable cause) {
        LOGGER.error("Lost connection", cause);
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        synchronized (this.queue) {
            LOGGER.info("Received message: {}", message.toString());
            if (this.queue.size() >= 100) {
                this.queue.remove();
            }
            this.queue.add(message.toString());
            if (this.listener != null) {
                this.listener.messageReceived(message.toString());
            }
        }
    }

    public List<String> messages() {
        return new ArrayList<String>(this.queue);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        LOGGER.debug("delivery completed");
    }

    public String getConnectionString() {
        return config.getConnectionString();
    }

    public String getTopic() {
        return this.config.getTopic();
    }

    public void disconnect() {
        if (this.mqttClient != null) {
            try {
                this.mqttClient.disconnect();
                this.mqttClient.close();
            } catch (MqttException e) {
                LOGGER.error("Error when closing connection", e);
            }
        }
    }

}