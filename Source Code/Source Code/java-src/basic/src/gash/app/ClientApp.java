package gash.app;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import gash.socket.BasicClient;

/**
 * client - basic chat construct. This varies from our Python and C++ versions
 * as it prompts the use for messages.
 * 
 * @author gash
 * 
 */
public class ClientApp {
	// private BasicClient myClient;

	public ClientApp() {
		/* by dc */
   // constructor
 }

	public static void main(String[] args) {
		var myClient = new BasicClient("app", "127.0.0.1", 2000);
		myClient.connect();
		myClient.join("pets/dogs");

		var br = new BufferedReader(new InputStreamReader(System.in));
		/* 
		while (true) {
			try {
		 		System.out.print("\nenter message ('exit' to quit): ");
		 		var m = br.readLine();
		 		if (m.length() == 0 || "exit".equalsIgnoreCase(m))
		 		    /*by dc 
		 			myClient.stop();
		 			// break;

		 		myClient.sendMessage(m);
		 	} catch (Exception ex) {
		 		break;
		 	}
		}
		*/
		/*by dc:- linter */
	 	
		boolean shouldContinue = true;
		 while (shouldContinue) {
			 try {
				 if(myClient.getclt()==null){
					 System.out.println("Connection aborted,Turning off the client");
					//  shouldContinue = false; // Set loop termination condition
					 myClient.stop();break;
				 }
				 System.out.print("\nEnter message ('exit' to quit): ");
				 var m = br.readLine();
				 if (m == null || m.isEmpty()) {
					 System.out.println("No data sent");
					 // shouldContinue = false; // Set loop termination condition
					 // myClient.stop();
				 }
				 else if(m.equalsIgnoreCase("exit")){
					 shouldContinue = false; // Set loop termination condition
					  myClient.stop();
					 
				 }
				 else {
					 
					 
					 myClient.sendMessage(m);
				 }
			 } catch (Exception ex) {
				 System.out.println("Invalid input");
				 ex.printStackTrace();
				 shouldContinue = false; // Terminate loop on IOException
			 }
		 }
	//System.out.print("\nEnter message ('exit' to quit): ");
	//var m = "hello world";
	//myClient.sendMessage(m);
		}
	}