package ua.name.anton.mail;

import java.util.Arrays;
import java.util.Date;

public class Request {
	
	private String from;
	private Integer reportType;
	private Date requestDate;
	private String args[];
	
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public Integer getReportType() {
		return reportType;
	}
	public void setReportType(Integer reportType) {
		this.reportType = reportType;
	}
	public Date getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}
	public String[] getArgs() {
		return args;
	}
	public void setArgs(String[] args) {
		this.args = args;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(args);
		result = prime * result + ((from == null) ? 0 : from.hashCode());
		result = prime * result
				+ ((reportType == null) ? 0 : reportType.hashCode());
		result = prime * result
				+ ((requestDate == null) ? 0 : requestDate.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Request other = (Request) obj;
		if (!Arrays.equals(args, other.args))
			return false;
		if (from == null) {
			if (other.from != null)
				return false;
		} else if (!from.equals(other.from))
			return false;
		if (reportType == null) {
			if (other.reportType != null)
				return false;
		} else if (!reportType.equals(other.reportType))
			return false;
		if (requestDate == null) {
			if (other.requestDate != null)
				return false;
		} else if (!requestDate.equals(other.requestDate))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Request [from=" + from + ", reportType=" + reportType
				+ ", requestDate=" + requestDate + ", args="
				+ Arrays.toString(args) + "]";
	}	
	
}
