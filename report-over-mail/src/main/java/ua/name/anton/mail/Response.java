package ua.name.anton.mail;

public class Response {
	
	private String recipient;
	private String subject;
	private byte attache[];
	private String attName;
	private String attMimeType;
	
	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public byte[] getAttache() {
		return attache;
	}
	public void setAttache(byte attache[]) {
		this.attache = attache;
	}
	public String getAttName() {
		return attName;
	}
	public void setAttName(String attName) {
		this.attName = attName;
	}
	public String getAttMimeType() {
		return attMimeType;
	}
	public void setAttMimeType(String attMimeType) {
		this.attMimeType = attMimeType;
	}
	
	

}
