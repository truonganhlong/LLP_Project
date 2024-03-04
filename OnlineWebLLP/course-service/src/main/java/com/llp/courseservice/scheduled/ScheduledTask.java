package com.llp.courseservice.scheduled;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ScheduledTask {
    private final RestTemplate restTemplate;

    @Autowired
    public ScheduledTask(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Scheduled(cron = "0 0 0 * * ?", zone = "UTC") // call api at 12 a.m utc
    public void callDiscountApi() {
        String apiUrl = "http://localhost:8222/api/course/discount/public/end";
        String response = restTemplate.getForObject(apiUrl, String.class);

        // Process the API response as needed
        //System.out.println("API Response: " + response);
    }
}

