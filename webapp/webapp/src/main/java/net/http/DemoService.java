package net.http;

import java.util.Date;

import net.controller.ParserController;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class DemoService {
	
	private Logger log = Logger.getLogger(ParserController.class);

//	@Scheduled(fixedRate = 1000 * 60 * 10)
    public void demoServiceMethod()
    {
        log.info("Method executed at every 10 minutes. Current time is: "+ new Date());
//        System.out.println("Method executed at every 10 seconds. Current time is: "+ new Date());
    }	
}
