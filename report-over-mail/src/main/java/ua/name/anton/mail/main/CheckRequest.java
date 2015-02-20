package ua.name.anton.mail.main;

import ua.name.anton.mail.RequestUtil;

public class CheckRequest implements Runnable {

	
	
	@Override
	public void run() {
		Main.requests.addAll(RequestUtil.checkUnseenRequest());
	}

}
