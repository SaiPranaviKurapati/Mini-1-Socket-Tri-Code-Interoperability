package gash.socket;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;


import gash.payload.BasicBuilder;
import gash.payload.Message;

public class BasicClient {
	private String name;
	private String ipaddr;
	private int port;
	private String group = "public";

	private Socket clt;

	public BasicClient(String name) {
		this(name, "127.0.0.1", 2000);
	}

	public BasicClient(String name, String ipaddr, int port) {
		this.name = name;
		this.ipaddr = ipaddr;
		this.port = port;
	}
	public Socket getclt(){
		return clt;

	}

	public void stop() {
    synchronized (this) {
        if (this.clt != null && !this.clt.isClosed()) {
            try {
                this.clt.close();
                System.out.println("Client is closed");
            }  catch (SocketException se) {
                // Handle SocketException
                System.err.println("SocketException occurred while closing the client socket:");
                se.printStackTrace();
            } 
			catch (IOException e) {
                // Handle IOException gracefully
                System.err.println("Error occurred while closing the client socket:");
                e.printStackTrace();
            } catch (Exception ex) {
                // Handle other unexpected exceptions
                System.err.println("Unexpected exception occurred while closing the client socket:");
                ex.printStackTrace();
            } 
			finally {
                this.clt = null;
            }
        }
    }
}

	public void join(String group) {
		this.group = group;
	}

	public void connect() {
		if (this.clt != null) {
			/* by dc:-  */
			System.out.println("The connection already exists");
			return;
		}

		try {
			/* by dc:-  */
			long starttime = System.currentTimeMillis();
			System.out.println("Connecting...");
			
			this.clt = new Socket(this.ipaddr, this.port);
			
			
			long endtime = System.currentTimeMillis();
        long connectiontime = endtime - starttime ;  
		System.out.println("Connected to " + clt.getInetAddress().getHostAddress()+"in "+connectiontime+" ms");  
		 } 
		// catch (Exception e) {
		// 	e.printStackTrace();
		
			
		// }
		/*by dc:- handle specific exeption */
				catch (UnknownHostException e) {
			// Handle UnknownHostException (e.g., invalid IP address or hostname)
			System.out.println("Invalid IP address or hostname or port");
			// e.printStackTrace();
		} catch (ConnectException e) {
			// Handle ConnectException (e.g., connection refused)
			System.out.println("Connection refused");
			// e.printStackTrace();
		} catch (SocketTimeoutException e) {
			// Handle SocketTimeoutException (e.g., connection timeout)
			System.out.println("Connection timeout");
			// e.printStackTrace();
		}  catch (Exception e) {
			// Handle any other unexpected exceptions
			System.out.println("Unexpected error Occurred");
			
		}
	}
	

	public void sendMessage(String message) {
		int retry =3;

		if (this.clt == null) {
			System.out.println("No connection, text not sent");
			return;
		}

		try {
			long starttime = System.currentTimeMillis();
			BasicBuilder builder = new BasicBuilder();
			
			byte[] msg = builder.encode(new Message(name, group, message)).getBytes();
			this.clt.getOutputStream().write(msg);
			long endtime = System.currentTimeMillis();
            long transmissiontime = endtime - starttime;
            System.out.println("Transmission time: "+transmissiontime/1000.0+" s");
			
		} 
		catch(SocketException se){
			System.out.println("Text not sent..Execption Caused due to connection abort");
			clt=null;
			stop();
			
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Text not sent, unexpected error caused");
			++retry;
			if(retry>3){
				clt=null;
				System.out.println("Maximum retries reached");
				stop();
				
			}
			
		}
	}


	
	


}
