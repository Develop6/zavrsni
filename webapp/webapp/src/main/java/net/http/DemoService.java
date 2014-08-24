package net.http;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;

public class DemoService {

    @Scheduled(cron="*/5 * * * * ?")
    public void demoServiceMethod()
    {
        System.out.println("Method executed at every 5 seconds. Current time is :: "+ new Date());
    }	
}
