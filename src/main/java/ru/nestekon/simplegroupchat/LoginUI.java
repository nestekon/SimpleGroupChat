package ru.nestekon.simplegroupchat;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.event.*;

public class LoginUI {
	
	JTextField	mJID;
	JTextField	mPassword;
	
	public void create() {
		JFrame frame = new JFrame("Simple Group Chat");
		JPanel mainPanel = new JPanel();
		mJID = new JTextField(20);
		mPassword = new JTextField(20);
		mainPanel.add(mJID);
		mainPanel.add(mPassword);
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new LoginActionListener());
		mainPanel.add(loginButton);
		frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
		frame.setSize(300, 150);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public class LoginActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			String email = mJID.getText().toString();
	        String password = mPassword.getText().toString();
			boolean cancel = false;
			
			// Проверка валидности пароля
	        if (password.trim().isEmpty() && !isPasswordValid(password)) {
	            // TODO:  сообщение об ошибке
	        	mPassword.requestFocus();
	        	cancel = true;
	        }
	        // Проверка валидности JID
	        if (email.trim().isEmpty() && !isEmailValid(email)) {
	        	// TODO:  сообщение об ошибке
	            mJID.requestFocus();;
	            cancel = true;
	        }
	        if (!cancel) {
	        	new Login().saveCredentialsAndLogin(email, password);
	        }
			
		}
		
		private boolean isEmailValid(String email) {
	        return email.contains("@");
	    }
	
	    private boolean isPasswordValid(String password) {
	        return password.length() > 4;
	    }
	}
}