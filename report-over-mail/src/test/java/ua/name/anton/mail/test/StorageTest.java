package ua.name.anton.mail.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ua.com.ucslombard.commonmonthreport.CommonMonthReport;
import ua.name.anton.mail.Storage;
import ua.name.avetrov.report.Report;

public class StorageTest {

	@Test
	public void getTest() {
		Report except = new CommonMonthReport();
		Report result = Storage.getReport(1);
		assertEquals(except, result);
	}
}
