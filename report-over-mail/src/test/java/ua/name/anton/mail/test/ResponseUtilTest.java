package ua.name.anton.mail.test;

import org.junit.Test;

import ua.name.anton.mail.Request;
import ua.name.anton.mail.main.DoResponse;

public class ResponseUtilTest {
	
	@Test
	public void doResponseTest() {	
		Request request = new Request();
		request.setReportType(2);
		request.setArgs(new String[]{"2015"});
		request.setFrom("anton.panchenko@ucs-lombard.com.ua");
		DoResponse dr = new DoResponse(request);
		dr.run();
//		new Thread(new DoResponse(request)).start();
	}

}
