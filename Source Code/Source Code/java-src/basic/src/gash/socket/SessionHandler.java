package gash.socket;

import java.io.BufferedInputStream;

import java.net.Socket;
// import java.net.SocketException;
import java.net.SocketTimeoutException;

import gash.payload.BasicBuilder;
import gash.payload.Message;


/**
 * 
 * @author gash
 * 
 */
class SessionHandler extends Thread {
	private Socket connection;
	// private String name;
	
	private boolean forever = true;

	private long starttime;

	private double requestcounter;
	

	public SessionHandler(Socket connection) {

		this.connection = connection;
		this.starttime = System.currentTimeMillis();
		this.requestcounter += 1;
		
		
		// allow server to exit if
		this.setDaemon(true);
	}

	/**
	 * stops session on next timeout cycle
	 */
	public void stopSession() {
		forever = false;
		if (connection != null) {
			try {
				connection.close();
				long endtime = System.currentTimeMillis();
		double elapsedtime = (endtime - this.starttime); 
		double requestrate = this.requestcounter / elapsedtime;
		System.out.println("Requests processed per second: " + requestrate/1000.0);
				System.out.println("Session" +" "+ this.getId() + " Ended Successfully");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		connection = null;
	}

	/**
	 * process incoming data
	 */
	@Override
	
	public void run() {
		System.out.println("Session " + this.getId() + " started");

		try {
			connection.setSoTimeout(12000);
			var in = new BufferedInputStream(connection.getInputStream());
			byte[] raw = new byte[2048];
			
			BasicBuilder builder = new BasicBuilder();
			while (forever) {
				try {
					int len = in.read(raw);
					if (len == 0)
						continue;
					else if (len == -1)
						break;
					long startProcessingTime = System.currentTimeMillis();
					Message msg = builder.decode(new String(raw, 0, len).getBytes());
					

		
		// Record the end time
		long endProcessingTime = System.currentTimeMillis();

		// Calculate the processing time in seconds
		double processingTimeSeconds = (endProcessingTime - startProcessingTime);

		// Print the processing time
		System.out.println("Processing time: " + processingTimeSeconds/1000.0 + " s");
					System.out.println(msg);
					
				}
				catch(SocketTimeoutException ste){System.out.println("Session Timed out after 12000ms");
				
				connection.close();
				
				
			}
			}
		} 
	
		catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				System.out.println("Session " + this.getId() + " Ending..");
				System.out.flush();
				stopSession();
				
			} catch (Exception re) {
				re.printStackTrace();
			}
		}
	}

} 
	