package com.cit.trendanalysis.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQTTSubscriberConfig {

    @Value("${mqtt.broker.host:13.82.192.85}")
    public static String mqttBrokerHost;

    @Value("${mqtt.broker.port:8883}")
    public static int mqttBrokerPort;

    @Value("${mqtt.topic.name:validation.alerts.bravo}")
    public static String mqttTopic;

    @Bean
    MQTTBrokerConfig Config() {
        MQTTBrokerConfig config =  new MQTTBrokerConfig();
        config.setHostName(mqttBrokerHost);
        config.setPort(mqttBrokerPort);
        config.setTopic(mqttTopic);
        return config;
    }

}
