package com.cit.trendanalysis.config;

import javax.validation.constraints.NotNull;

public class MQTTBrokerConfig {

    @NotNull
    private String hostName;

    @NotNull
    private Integer port;

    @NotNull
    String topic;

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }


    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getConnectionString() {
        return "tcp://" + this.hostName + ":" + this.port;
    }

    public String toString() {
        return this.hostName + ":" + this.port;
    }




}