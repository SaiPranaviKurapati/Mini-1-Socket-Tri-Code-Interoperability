"""
BasicServer for handling client connections and messages.

Summary:This class represents a basic server for handling client connections and messages. 
It provides methods to start and stop the server, as well as handling 
incoming client connections.

Raises:
    ValueError: If there is an issue with the IP address or port number.
        This exception is raised in various methods of this module.
"""

import socket
import threading
import time
import builder
class BasicServer:
    """_summary_

    Attributes:
        ipaddr (str): IP address of the server.
        port (int): Port number the server is listening on.
        _svr (socket.socket): Server socket object.
        good (bool): Flag indicating if the server is running.
    """
    def __init__(self, ipaddr, port=2000):
        """
        Initializes a BasicServer object with the specified IP address and port number.

        Args:
            ipaddr (str): The IP address to bind the server to.
            port (int, optional): The port number to listen on. Defaults to 2000.

        Raises:
            ValueError: If the IP address is missing or empty,
                        if the port number is missing,
                        or if the port number is below or equal to 1024.
        """
        self.ipaddr = ipaddr
        self.port = port
        self._svr = None
        self.good = True

        if self.ipaddr is None:
            raise ValueError("IP address is missing or empty")
        elif self.port is None:
            raise ValueError("port number is missing")
        elif self.port <=1024:
            raise ValueError(f"port number ({port}) must be above 1024")
        self.request_counter = 0
        self.start_time = None

    def __del__(self):
        """Cleans up resources when the BasicServer object is deleted."""
        self.stop()

    def stop(self):
        """Stops the server and closes the server socket."""
        self.good = False
        if self._svr is not None:
            try:
                self._svr.shutdown(socket.SHUT_RDWR)  # Shut down the socket
            except OSError as e:
                print(f"An error occurred while shutting down the server socket: {e}")        
        self._svr.close()
        self._svr = None        
        end_time = time.time()  # Record the end time
        elapsed_time = end_time - self.start_time
        request_rate = self.request_counter / elapsed_time
        print(f"Requests processed per second: {request_rate}")

    def run(self):
        """
        Starts the server in a separate thread.

        Returns:
            threading.Thread: The thread running the server.
        """
        print("Starting server thread...")
        print(f"Server IP address: {self.ipaddr}, Port: {self.port}")

        server_threads = threading.Thread(target=self._run_server)
        server_threads.start()

        return server_threads

    def _run_server(self):
        """Starts the server and handles incoming client connections."""        
        try:
            addr = (self.ipaddr,self.port)
            self._svr = socket.create_server(addr)
            self._svr.listen(100000)
            print(f"Server ({self.ipaddr}) is listening on port {self.port}")
            self.start_time = time.time()
            while self.good:
                cltconn, caddr = self._svr.accept()
                print("Connection accepted from:", caddr)
                csession = SessionHandler(cltconn, caddr)
                csession.start()
                self.request_counter += 1
        except (OSError, socket.error) as e:
            print(f"An error occurred in the server thread: {e}")
class SessionHandler(threading.Thread):
    """_summary_

    Attributes:
        _cltconn (socket.socket): Client connection socket.
        _cltaddr (tuple): Client address (IP address, port number).
        good (bool): Flag indicating if the session is active.
    """
    def __init__(self,client_connection, client_addr):
        """
        Initializes a SessionHandler object with the specified client connection and address.

        Args:
            client_connection (socket.socket): The client connection socket.
            client_addr (tuple): The client address (IP address, port number).
        """
        threading.Thread.__init__(self)
        self.daemon = False
        self._cltconn = client_connection
        self._cltaddr = client_addr
        self.good = True

    def __del__(self):
        """Cleans up resources when the SessionHandler object is deleted."""
        self.close()

    def close(self):
        """Closes the client connection socket."""
        if self._cltconn is not None:
            self._cltconn.close()
            self._cltconn = None
            self.good = False

    def process(self, raw):
        """
        Processes a raw message received from the client.

        Args:
            raw (str): The raw message string received from the client.
        """
        MAX_MESSAGE_LENGTH = 2048       
        if len(raw) > MAX_MESSAGE_LENGTH:
            print("Message exceeds maximum allowed length. Ignoring...")
            return        
        while not raw.strip():  # Check if the message is empty or contains only whitespace
            print("Empty message received. Ignoring...")
            raw = input()
        try:
            start_processing_time = time.time()
            bldr = builder.BasicBuilder()
            name,group,text = bldr.decode(raw)
            print(f"from {name}, to group: {group}, text: {text}")
            end_processing_time = time.time()
            processing_time = end_processing_time - start_processing_time
            print(f"Processing time: {processing_time} seconds")
        except ValueError as e:
            print("ValueError:", e)
        except TypeError as e:
            print("TypeError:", e)
        except IndexError as e:
            print("IndexError:", e)
        except Exception as e:
            print(f"An unexpected error occurred in the process: {e}")

    def run(self):
        """Starts the session handler to receive and process messages from the client."""
        print("i am in server run")
        while self.good:
            try:
                buf = self._cltconn.recv(2048)
                if len(buf) <= 0:
                    self.good = False
                else:
                    self.process(buf.decode("utf-8"))
            except OSError as e:
                print(f"An OSError occurred: {e}")
                self.good = False
            except ValueError as e:
                print(f"A ValueError occurred: {e}")
                self.good = False
            except Exception as e:
                print(f"An unexpected error occurred: {e}")
                self.good = False

        print(f"Closing session {self._cltaddr}")

if __name__ == '__main__':
    try:
        svr = BasicServer("localhost", 2000)
        server_thread = svr.run()
        # checking if IP address is valid
        if server_thread is None:
            print("Server failed to start. Exiting...")
        else:
            try:
                while True:
                    time.sleep(1)
            except KeyboardInterrupt:
                print("Stopping server...")
                svr.stop()
                server_thread.join() # Join the server thread before exiting
    except OSError as e:
        print(f"An error occurred in the server thread: {e}")