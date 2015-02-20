package ua.name.anton.mail;

import java.util.HashMap;
import java.util.Map;

import ua.name.avetrov.report.Report;

public class Storage {
	
	private final static Map<Integer, String> REPORTS = new HashMap<Integer, String>();
	
	static {
		REPORTS.put(1, "ua.com.ucslombard.commonmonthreport.CommonMonthReport");
		REPORTS.put(2, "ua.com.ucslombard.thechportfoliodetailreport.TechDetailReport");
		REPORTS.put(999, "ua.com.ucslombard.empty.EmptyReport");
	}
	
	public static Report getReport(Integer key) {
		String target = REPORTS.get(key);
		Report result = null;
		
		if (target == null) {
			target = REPORTS.get(999);
		}

		try {
			result = (Report) Class.forName(target).newInstance();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		if (target.equals("ua.com.ucslombard.empty.EmptyReport")) {
			((ua.com.ucslombard.empty.EmptyReport)result).setDescription(REPORTS);
		}
		
		return result;
	}

}
