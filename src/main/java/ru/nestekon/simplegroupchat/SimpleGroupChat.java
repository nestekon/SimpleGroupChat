package ru.nestekon.simplegroupchat;

public class SimpleGroupChat {
	public static void main (String[] args) {
		SimpleGroupChat chat = new SimpleGroupChat();
		chat.go();
	}

	public void go() {
		LoginUI mLoginForm = new LoginUI();
		mLoginForm.create();
	}
}
