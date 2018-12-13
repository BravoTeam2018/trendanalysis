package com.cit.trendanalysis.service.mqttsubscriber;

public interface MessageListener {

    public void messageReceived(String message);

}