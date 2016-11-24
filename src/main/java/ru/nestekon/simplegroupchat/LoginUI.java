package ru.nestekon.simplegroupchat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginUI {
	
	private JTextField	mJID;
	private JTextField	mPassword;
	private JFrame mFrame;
	
	public void create() {
		//TODO починить растягивания, сейчас пока это 
		//		исправляет frame.getContentPane().setLayout(new FlowLayout());
		
		// Список компонент
		JLabel jidLabel = new JLabel("JID");
		mJID = new JTextField(20);
		JLabel pswdLabel = new JLabel("Password");
		mPassword = new JTextField(20);
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	loginButton.setEnabled(false);
		    }
		});
		loginButton.addActionListener(new LoginActionListener());
		// Определение менеджера расположения
		mFrame = new JFrame("Simple Group Chat");
		Box mainPanel = new Box(BoxLayout.Y_AXIS);
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		Box jidPanel = new Box(BoxLayout.X_AXIS);
		jidPanel.add(jidLabel);
		jidPanel.add(Box.createHorizontalStrut(12));
		jidPanel.add(mJID);
		Box pswdPanel = new Box(BoxLayout.X_AXIS);
		pswdPanel.add(pswdLabel);
		pswdPanel.add(Box.createHorizontalStrut(12));
		pswdPanel.add(mPassword);
		mainPanel.add(jidPanel);
		mainPanel.add(pswdPanel);
		//GridBagLayout layout = new GridBagLayout(); 
		//mainPanel.setLayout(layout);
		//layout.setAutoCreateGaps(true); 
        //layout.setAutoCreateContainerGaps(true);
        
		mFrame.getContentPane().setLayout(new FlowLayout());
		mFrame.getContentPane().add(mainPanel);
		mFrame.getContentPane().add(loginButton);
		
		mFrame.setSize(400, 150);
		mFrame.setVisible(true);
		mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void hide() {
		mFrame.setVisible(false);
	}

	public class LoginActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			String email = mJID.getText().toString();
	        String password = mPassword.getText().toString();
			boolean cancel = false;
			
			// Проверка валидности пароля
	        if (password.trim().isEmpty() && !isPasswordValid(password)) {
	            // TODO:  сообщение об ошибке
	        	mPassword.selectAll();
	        	mPassword.requestFocus();
	        	cancel = true;
	        }
	        // Проверка валидности JID
	        if (email.trim().isEmpty() && !isEmailValid(email)) {
	        	// TODO:  сообщение об ошибке
	        	mJID.selectAll();
	            mJID.requestFocus();;
	            cancel = true;
	        }
	        if (!cancel) {
	        	new Login().saveCredentialsAndLogin(email, password);
	        	hide();
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