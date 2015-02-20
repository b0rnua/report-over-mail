package ua.name.anton.mail.main;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import ua.name.anton.mail.Request;

public class Main {

	static BlockingQueue<Request> requests = new ArrayBlockingQueue<Request>(5);
	
	public static void main(String[] args) {
		
		ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
		service.scheduleWithFixedDelay(new CheckRequest(), 0, 90, TimeUnit.SECONDS);


		ExecutorService es1 = Executors.newFixedThreadPool(5);
		
		while (true) {
			try {
				es1.submit(new DoResponse(requests.take()));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
