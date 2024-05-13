package gash.app;

import gash.socket.BasicServer;

/**
 * server application
 * 
 * @author gash
 * 
 */
class ServerApp {
	public ServerApp() {
   // constructor
 }

	public static void main(String[] args) {
		var host = "127.0.0.1";
		var port = 2000;
		var server = new BasicServer(host,port);
		server.start();
		// try {
		// 	while (true) {
		// 		Thread.sleep(1000); // Sleep for 1 second
		// 		// Process requests or perform server operations here
		// 	}
		// } catch (InterruptedException e) {
		// 	System.out.println("Stopping server...");
		// 	server.stop();
		// }
	}
}
