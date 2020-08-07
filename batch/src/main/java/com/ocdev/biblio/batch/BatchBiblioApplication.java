package com.ocdev.biblio.batch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import com.ocdev.biblio.batch.services.ScheduledTask;

/**
 * Classe principale de l'application.
 * Cette classe s'appuie sur le framework Spring Boot.
 * @author C.Orsini
 *
 */
@SpringBootApplication
@EnableScheduling
public class BatchBiblioApplication implements CommandLineRunner
{
	@Autowired ScheduledTask scheduledTask;
	
	/**
	 * Méthode <b>Main</b>.
	 * Les arguments ne sont pas utilisés dans cette application.
	 * @param args nil.
	 * @throws Exception si une exception non gérée remonte jusqu'à la méthode.
	 */
	public static void main(String[] args) throws Exception
	{
		SpringApplication.run(BatchBiblioApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception
	{
	
	}
}
