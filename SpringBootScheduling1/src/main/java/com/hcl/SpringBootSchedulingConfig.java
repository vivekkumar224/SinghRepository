package com.hcl;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component("report")
public class SpringBootSchedulingConfig {
	
	@Scheduled(cron = "0/20 * * * * *")
	public void scheduleREport()
	{
		System.out.println("salesReport"+new Date());
	}

}
