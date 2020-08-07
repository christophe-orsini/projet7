package com.ocdev.biblio.batch.services;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask
{
	@Scheduled(cron = "${app.cron.expression}")
	public void cronTask()
	{
		System.out.println("Dans Cron");
	}
}
