package com.jasontyzzer.javacities;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Random;

@Slf4j
@RestController
public class CityController {

    private final CityRepository cityRepo;
    private final RabbitTemplate rt;

    public CityController(CityRepository cityRepo, RabbitTemplate rt) {
        this.cityRepo = cityRepo;
        this.rt = rt;
    }

    @GetMapping("/afford")
    public void afford()
    {
        ArrayList<City> cities = new ArrayList<>();
        cities.addAll(cityRepo.findAll());

        for (City c: cities)
        {
            int index = c.getAffordabilityIndex();
            boolean secret = new Random().nextBoolean();
            final CityMessage message = new CityMessage(c.toString(), index, secret);

            if (secret)
            {
                rt.convertAndSend(JavaCitiesApplication.QUEUE_NAME_SECRET, message);
            } else if (index < 6)
            {
                rt.convertAndSend(JavaCitiesApplication.QUEUE_NAME_CITY1, message);
            } else
            {
                rt.convertAndSend(JavaCitiesApplication.QUEUE_NAME_CITY2, message);
            }
        }
    }

    @GetMapping("/homes")
    public void homes()
    {
        ArrayList<City> cities = new ArrayList<>();
        cities.addAll(cityRepo.findAll());

        for(City c : cities) {
            int index = c.getAverageHouseCost();
            boolean secret = new Random().nextBoolean();
            final CityMessage message = new CityMessage(c.toString(), index, secret);
            if (secret)
            {
                rt.convertAndSend(JavaCitiesApplication.QUEUE_NAME_SECRET, message);
            } else if (index > 200000)
            {
                rt.convertAndSend(JavaCitiesApplication.QUEUE_NAME_CITY1, message);
            } else
            {
                rt.convertAndSend(JavaCitiesApplication.QUEUE_NAME_CITY2, message);
            }

        }
    }

    @RequestMapping("/names")
    public void names()
    {
        ArrayList<City> cities = new ArrayList<>();
        cities.addAll(cityRepo.findAll());
        for(City c : cities) {
            int index = c.getAverageHouseCost();
            boolean secret = new Random().nextBoolean();
            final CityMessage message = new CityMessage(c.toString(), index, secret);
            if (secret)
            {
                rt.convertAndSend(JavaCitiesApplication.QUEUE_NAME_SECRET, message);
            }
            else
            {
                rt.convertAndSend(JavaCitiesApplication.QUEUE_NAME_CITY2, message);
            }

        }
    }

}