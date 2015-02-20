package ua.name.anton.mail.main;

import java.io.ByteArrayOutputStream;

import org.apache.log4j.Logger;

import ua.name.anton.mail.Request;
import ua.name.anton.mail.Response;
import ua.name.anton.mail.ResponseUtil;
import ua.name.anton.mail.Storage;
import ua.name.avetrov.report.Report;

public class DoResponse implements Runnable {

	private Report report;
	private Request request;
	
	static Logger log = Logger.getLogger(DoResponse.class.getName());
		
	public DoResponse(Request request) {
		super();
		this.report = Storage.getReport(request.getReportType());
		this.request = request;
		log.info(request);
	}

	@Override
	public void run() {
		Response response = new Response();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();	
		baos = report.doReport(request.getArgs());		
		response.setAttache(baos.toByteArray());
		response.setAttMimeType(report.getAttMimeType());
		response.setAttName(report.getAttName());
		response.setRecipient(request.getFrom());
		response.setSubject(report.getSubject());
		ResponseUtil.doResponse(response);
	}

}
