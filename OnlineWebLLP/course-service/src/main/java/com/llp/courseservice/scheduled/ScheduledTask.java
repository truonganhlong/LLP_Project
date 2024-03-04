package com.llp.courseservice.scheduled;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
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
        restTemplate.execute(apiUrl, HttpMethod.DELETE, null, null);
    }
    @Scheduled(fixedDelay = 7 * 24 * 60 * 60 * 1000) // Run every week (7 days * 24 hours * 60 minutes * 60 seconds * 1000 milliseconds)
    public void callBestsellerTagApi() {
        String apiUrl = "http://localhost:8222/api/course/tag/public/updateBestsellerTag";
        restTemplate.execute(apiUrl, HttpMethod.POST, null, null);
    }
    @Scheduled(fixedDelay = 7 * 24 * 60 * 60 * 1000) // Run every week (7 days * 24 hours * 60 minutes * 60 seconds * 1000 milliseconds)
    public void callHighestRatedTagApi() {
        String apiUrl = "http://localhost:8222/api/course/tag/public/updateHighestRatedTag";
        restTemplate.execute(apiUrl, HttpMethod.POST, null, null);
    }
}

