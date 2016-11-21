package ru.nestekon.simplegroupchat;

import java.io.IOException;

import org.jivesoftware.smack.ConnectionListener;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.chat.ChatMessageListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;

public class Connection  implements ConnectionListener,ChatMessageListener {
	
	private  final String mUsername;
    private  final String mPassword;
    private  final String mServiceName;
    private	XMPPTCPConnection mConnection;

	public static enum ConnectionState
    {
        CONNECTED ,AUTHENTICATED, CONNECTING ,DISCONNECTING ,DISCONNECTED;
    }
	
	public static enum LoggedInState
    {
        LOGGED_IN , LOGGED_OUT;
    }
	
	public Connection (Login aLogin) {
		if( aLogin.mJID != null)
        {
            mUsername = aLogin.mJID.split("@")[0];
            mServiceName = aLogin.mJID.split("@")[1];
        }else
        {
            mUsername ="";
            mServiceName="";
        }
		mPassword = aLogin.mPassword;
	}
	
	public void connect() throws IOException,SmackException,XMPPException
    { 
		//XMPPTCPConnectionConfiguration.XMPPTCPConnectionConfigurationBuilder builder=
		XMPPTCPConnectionConfiguration.Builder builder=
                XMPPTCPConnectionConfiguration.builder();
        builder.setServiceName(mServiceName);
        builder.setUsernameAndPassword(mUsername, mPassword);
        builder.setResource("SimpleGroupChat");
        
        //Настроить Поток получения сообщений от UI
        setupUiThreadBroadCastMessageReceiver();
        
        mConnection = new XMPPTCPConnection(builder.build());
        mConnection.addConnectionListener(this);
        mConnection.connect();
        mConnection.login();

        // TODO починить
        //ReconnectionManager reconnectionManager = ReconnectionManager.getInstanceFor(mConnection);
        //reconnectionManager.setEnabledPerDefault(true);
        //reconnectionManager.enableAutomaticReconnection();
    }
	
	private void setupUiThreadBroadCastMessageReceiver()
    {
		//TODO
    }
	
	@Override
    public void processMessage(Chat chat, Message message) {

        String from = message.getFrom();
        String contactJid = "";
        if ( from.contains("/"))
        {
            contactJid = from.split("/")[0];
        }else
        {
            contactJid=from;
        }

        //TODO здесь должен быть какой-то аналог Intent
    }
	
	public void disconnect()
    {
		
    }
	
	@Override
    public void connected(XMPPConnection connection) {
        ConnectionService.sConnectionState=ConnectionState.CONNECTED;
    }

    @Override
    public void authenticated(XMPPConnection connection, boolean resumed) {
    	ConnectionService.sConnectionState=ConnectionState.CONNECTED;
        // TODO
        //showContactListActivityWhenAuthenticated();

    }

    @Override
    public void connectionClosed() {
        ConnectionService.sConnectionState=ConnectionState.DISCONNECTED;
    }

    @Override
    public void connectionClosedOnError(Exception e) {
    	e.printStackTrace();
    	ConnectionService.sConnectionState=ConnectionState.DISCONNECTED;
    }

    @Override
    public void reconnectingIn(int seconds) {
        ConnectionService.sConnectionState = ConnectionState.CONNECTING;
    }

    @Override
    public void reconnectionSuccessful() {
        ConnectionService.sConnectionState = ConnectionState.CONNECTED;
    }

    @Override
    public void reconnectionFailed(Exception e) {
        ConnectionService.sConnectionState = ConnectionState.DISCONNECTED;
    }
    
}

