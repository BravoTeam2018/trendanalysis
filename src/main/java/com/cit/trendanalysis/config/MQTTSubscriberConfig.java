package com.cit.trendanalysis.config;

import com.cit.trendanalysis.service.mqttsubscriber.AlertSubscriber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class MQTTSubscriberConfig {

    @Value("${mqtt.broker.host:13.82.192.85}")
    public String mqttBrokerHost;

    @Value("${mqtt.broker.port:8883}")
    public int mqttBrokerPort;

    @Value("${mqtt.topic.name:validation.alerts.bravo}")
    public String mqttTopic;

    @Bean
    MQTTBrokerConfig config() {
        MQTTBrokerConfig config =  new MQTTBrokerConfig();
        config.setHostName(mqttBrokerHost);
        config.setPort(mqttBrokerPort);
        config.setTopic(mqttTopic);
        return config;
    }


}
