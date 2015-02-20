package ua.name.anton.mail.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ua.name.anton.mail.Request;
import ua.name.anton.mail.RequestUtil;

public class RequestUtilTest {
	
	@Test
	public void getSubjectTest() {
		
		Request except = new Request();
		except.setFrom("");
		except.setRequestDate(null);
		except.setArgs(new String[]{"01022015", "all"});
		except.setReportType(1);
		
		Request result = new Request();
		result.setFrom("");
		result.setRequestDate(null);
		RequestUtil.getSubject("1 01022015 all", result);
		
		assertEquals(except, result);
		
	}
	
	
	@Test
	public void checkUnseenRequestTest() {
		List<Request> except = new ArrayList<Request>();
//		except.add(new Request());
		
		List<Request> result = RequestUtil.checkUnseenRequest();
		for (Request request:result) {
			System.out.println(request);
		}
		assertEquals(except.size(), result.size());
	}

}
