"""
BasicClient for interacting with a server.

Summary:
    This class provides functionality to interact with a server as a client.
    It allows connecting to a server, sending messages, and managing client state.

Raises:
    ValueError: If the IP address or port number is missing or empty.
    ValueError: If an operation cannot be performed due to invalid arguments.
    RuntimeError: If an operation cannot be performed due to the client's state.

Attributes:
    _clt (socket.socket): The client socket object.
    name (str): The name of the client.
    ipaddr (str): The IP address of the server.
    port (int): The port number of the server.
    group (str): The current group the client is in (default is "public").

Args:
    object (_type_): The base class of BasicClient.
"""

import socket
import time
import builder

class BasicClient:
    """
        BasicClient for interacting with a server.

        Summary:This class provides functionality to interact with a server as a client.
        It allows connecting to a server, sending messages, and managing client state.

        Raises:ValueError: If the IP address or port number is missing or empty.
        RuntimeError: If an operation cannot be performed due to the client's state.

        Args:object (_type_): _description_
    """

    def __init__(self, name, ipaddr="localhost", port=2000):
        self._clt = None
        self.name = name
        self.ipaddr = ipaddr
        self.port = port

        self.group = "public"

        if self.ipaddr is None:
            raise ValueError("IP address is missing or empty")
        elif self.port is None:
            raise ValueError("port number is missing")

        self.connect()

    def __del__(self):
        self.stop()

    def stop(self):
        """
            Stops the client by closing the connection to the server.
            If the client is not connected, does nothing.

            Args:None

            Returns:None
        """
        if self._clt is not None:
            self._clt.close()
        self._clt = None
    def connect(self):
        """
            Connects the client to the server.
            If the client is already connected, does nothing.

            Args:None

            Returns:None
        """
        if self._clt is not None:
            return

        addr = (self.ipaddr, self.port)
        print(addr)
        # calculating the time taken to connect to server
        start_time = time.time()
        self._clt = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        print(self._clt)

        try:
            self._clt.connect(addr)
            print("Connected successfully to:", addr)
        except OSError as e:
            print(f"Failed to connect to {addr}: {e}")
            self._clt = None
        end_time = time.time()
        connection_time = end_time - start_time     
        if self._clt:
            print(f"Connected to host in {connection_time}")
        else:
            print(f"Failed to Connect to host in {connection_time}")
    def join(self, group):
        """
            Joins a group on the server.
            
            Args:group (str): The name of the group to join.
        """
        self.group = group

    def send_msg(self, text):
        """
            Sends a message to the server.

            Args:text (str): The text content of the message.

            Raises:RuntimeError: If no connection to the server exists.
        """
        if self._clt is None:
            raise RuntimeError("No connection to server exists")
        if not text:
            print("Empty message received. Ignoring...")
        else:
            start_time = time.time()
            print(f"sending to group {self.group} from {self.name}: {text}")
            bldr = builder.BasicBuilder()
            m_str = bldr.encode(self.name,self.group,text)
            self._clt.send(bytes(m_str, "utf-8"))
            end_time =  time.time()
            # Calculating the time taken by client to transmit data to server
            transmission_time = end_time - start_time
            print(f"Transmission time: {transmission_time} seconds")

    def groups(self):
        """_summary_
        """
        print(self.group)
        # return list of groups
    def get_msgs(self):
        """_summary_        
        """
if __name__ == '__main__':
    clt = BasicClient("frida_kahlo","localhost",2000)
    while True:
        m = input("enter message: ")
        if m=='exit':
            break
        elif m=='':
            print("Empty message entered, enter message again")
        else:
            clt.send_msg(m)
