package gash.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * server to manage incoming clients
 * 
 * @author gash
 * 
 */
public class BasicServer {
	@SuppressWarnings("unused")
	private String host;
	private int port;
	private ServerSocket socket;
	private boolean forever = true;
	
	
	
	/*
	
		Thread Pooling: 
		Instead of creating a new thread for each 
		client connection, you can use a thread pool to limit the number of concurrent threads 
		and reuse them. This approach prevents resource exhaustion and improves the server's scalability.
		the ExecutorService will not call the start() method of SessionHandler. Instead, it will call the run() method of SessionHandler.

	When you submit a task (in this case, an instance of SessionHandler) to the ExecutorService 
	using the submit() method, the ExecutorService internally 
	manages the execution of the task. It does this by calling the run() 
	method of the Runnable or Callable object provided to it.

	In our case, SessionHandler is a subclass of Thread, and it overrides 
	the run() method. When the ExecutorService submits an instance
	of SessionHandler, it will internally call the run() method of SessionHandler, 
	not the start() method.

	Therefore, the start() method of SessionHandler won't be called by 
	the ExecutorService. Instead, the run() method of SessionHandler will 
	be invoked automatically when the task is executed by a thread from the thread pool.
		
	ref:- https://www.geeksforgeeks.org/thread-pools-java/
	*/
		private ExecutorService executor;
		// private long start_time;
		// private int request_counter;
		
	
	public BasicServer(String host, int port) {
		this.host = host;
		this.port = port;
		this.executor = Executors.newCachedThreadPool();
	}

	/**
	 * start monitoring socket for new connections
	 */
	public void start() {
		try{
			
			socket = new ServerSocket(port);

			System.out.println("Server Host: " + socket.getInetAddress().getHostAddress());
			// this.start_time =System.currentTimeMillis();
			while (forever) {
				// Socket s = socket.accept();
				// if (!forever) {
				// 	break;
				// }
				Socket clientSocket = socket.accept();
				System.out.println("--> Server got a client connection");
				executor.submit(new SessionHandler(clientSocket));
				

			}
		} catch (IOException e) {
		// Failed to create the socket
		System.out.println("--> Socket Creation Failed");
		e.printStackTrace();
	}  catch (Exception e) {
		
		e.printStackTrace();
	} finally {
		// Close the server socket if it was successfully created
		if (socket != null && !socket.isClosed()) {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
		
	}

	public void stop() {
		if (this.socket!= null) {
			try {
				this.socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			this.socket = null;
		}

		
	}
}
