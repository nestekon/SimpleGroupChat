package ru.nestekon.simplegroupchat;

public class Login {
	
	String mJID;
	String mPassword;
	
	public void saveCredentialsAndLogin(String aJID, String aPassword) {
		mJID = aJID;
		mPassword = aPassword;
		//TODO saveCredentials
		new ConnectionService().start(this);
		
	}
	
	
}
