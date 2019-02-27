package com.jasontyzzer.javacities;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CityMessageListener {

    @RabbitListener(queues = JavaCitiesApplication.QUEUE_NAME_SECRET)
    public void receiveSecretMessage(CityMessage cm)
    {
        log.info("Received Secret Message: {} ", cm.toString());
    }

    @RabbitListener(queues = JavaCitiesApplication.QUEUE_NAME_CITY1)
    public void receiveCity1Message(CityMessage cm)
    {
        log.info("Received City1 Message: {} ", cm.toString());
    }

    @RabbitListener(queues = JavaCitiesApplication.QUEUE_NAME_CITY2)
    public void receiveCity2Message(CityMessage cm)
    {
        log.info("Received City2 Message: {} ", cm.toString());
    }

}