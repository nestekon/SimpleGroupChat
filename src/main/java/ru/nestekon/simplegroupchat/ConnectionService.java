package ru.nestekon.simplegroupchat;

import java.io.IOException;

import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;

public class ConnectionService {
	
	private boolean mActive;
	private Thread mThread;
	private Connection mConnection;
	public static enum ConnectionState
    {
        CONNECTED ,AUTHENTICATED, CONNECTING ,DISCONNECTING ,DISCONNECTED;
    }
	
	public void start(Login aLogin)	{
		if(!mActive)
        {
			mActive = true;
			if( mThread ==null || !mThread.isAlive())
            {
                mThread = new Thread(new Runnable() {
                    //@Override
                    public void run() {
                        initConnection(aLogin);
                    }
                });
                mThread.start();
            }
        }
	}
	
    public void stop()
    {
        mActive = false;
        if( mConnection != null)
        {
            mConnection.disconnect();
        }
    }
	
	private void initConnection(Login aLogin) {
		if( mConnection == null)
        {
            mConnection = new Connection(aLogin);
        }
        try
        {
            mConnection.connect();

        }catch (IOException| SmackException | XMPPException e)
        {
            e.printStackTrace();
            stop();
        }
	}
}
