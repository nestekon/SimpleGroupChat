package ru.nestekon.simplegroupchat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JoinGroupChatUI {

	private JTextField mHost;
	private JTextField mRoom;
	private JTextField mNickname;
	private JFrame mFrame;
	
	public void create() {
		//TODO починить растягивания, сейчас пока это 
		//		исправляет frame.getContentPane().setLayout(new FlowLayout());
		
		// Список компонент
		JLabel hostLabel = new JLabel("Host");
		mHost = new JTextField(20);
		JLabel roomLabel = new JLabel("Room");
		mRoom = new JTextField(20);
		JLabel nicknameLabel = new JLabel("Nickname");
		mNickname = new JTextField(20);
		JButton joinButton = new JButton("Join");
		joinButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	joinButton.setEnabled(false);
		    }
		});
		joinButton.addActionListener(new JoinActionListener());
		// Определение менеджера расположения
		mFrame = new JFrame("Simple Group Chat");
		Box mainPanel = new Box(BoxLayout.Y_AXIS);
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		Box hostPanel = new Box(BoxLayout.X_AXIS);
		hostPanel.add(hostLabel);
		hostPanel.add(Box.createHorizontalStrut(12));
		hostPanel.add(mHost);
		Box roomPanel = new Box(BoxLayout.X_AXIS);
		roomPanel.add(roomLabel);
		roomPanel.add(Box.createHorizontalStrut(12));
		roomPanel.add(mRoom);
		Box nicknamePanel = new Box(BoxLayout.X_AXIS);
		nicknamePanel.add(nicknameLabel);
		nicknamePanel.add(Box.createHorizontalStrut(12));
		nicknamePanel.add(mNickname);
		mainPanel.add(hostPanel);
		mainPanel.add(roomPanel);
		mainPanel.add(nicknamePanel);
		
		mFrame.getContentPane().setLayout(new FlowLayout());
		mFrame.getContentPane().add(mainPanel);
		mFrame.getContentPane().add(joinButton);
		
		mFrame.setSize(400, 150);
		mFrame.setVisible(true);
		mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}	
	
	public void hide() {
		mFrame.setVisible(false);
	}
	
	public class JoinActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			String host = mHost.getText().toString();
	        String room = mRoom.getText().toString();
	        String nickname = mNickname.getText().toString();
			boolean cancel = false;
			
			// Проверка валидности
			if (host.trim().isEmpty() && !isHostValid(host)) {
		        // TODO:  сообщение об ошибке
				mHost.selectAll();
				mHost.requestFocus();
				cancel = true;
		    }
			if (room.trim().isEmpty()) {
				mRoom.requestFocus();
				cancel = true;
		    }
			if (nickname.trim().isEmpty()) {
				mRoom.requestFocus();
				cancel = true;
			}
			if (!cancel) {
	        	new JoinGroupChat().saveCredentialsAndJoin(
	        			host, room, nickname);
	        	hide();
	        }
		}
		
		private boolean isHostValid(String host) {
	        return host.contains(".");
	    }
	}
}
