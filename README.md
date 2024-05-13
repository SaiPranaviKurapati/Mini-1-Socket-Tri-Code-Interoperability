# Socket - Tri-Code Interoperability

Welcome to the "Socket - Tri-Code Interoperability" project, an educational exploration into the foundation of distributed computing using sockets, demonstrating process communication across three programming languages: Java, C++, and Python. This project allows students to explore network communication, cross-language interoperability, and the practical implementation of socket programming in various programming environments.

## Project Overview

The main goal of this project is to establish a communication matrix where clients and servers written in Java, Python, and C++ can interoperate. This matrix demonstrates the possibilities and challenges of cross-language communication in network programming.

### Interoperability Matrix

The matrix below outlines the current status of client-server communication pairs, denoting completed examples with 'Y'. The objective is to fill in the gaps and ensure robust communication between each pair.

| Clt/Svr  | Java | Python3 | C/C++ |
| -------- | :--: | :-----: | :---: |
| Java     | Y    |         |       |
| Python3  |      | Y       | Y     |
| C/C++    |      | Y       | Y     |

### Project Structure

This project contains three subdirectories, each corresponding to a different programming language implementation:

- `cpp-src`: Contains the C++ implementation.
- `java-src`: Contains the Java implementation.
- `python-src`: Contains the Python implementation.

Each subdirectory has its own `README.md` providing specific setup and running instructions.

## Getting Started

### Prerequisites

Ensure you have the following tools and languages installed on your system:

- C/C++ Compiler (gcc/g++, clang)
- Java JDK 11 or newer
- Python 3
- cmake
- Linters for C/C++ and Python
- Tools like Valgrind and static analyzers
- Text editor or IDE (VSCode, IntelliJ, Eclipse, etc.)

# Detailed Chat Application Documentation

This repository hosts a Python-based client-server chat application. The application uses TCP/IP sockets for network communication and supports basic chat functionalities including message sending, group management, and session handling.

## Components

### 1. Builder.py (Message Encoder/Decoder)

`BasicBuilder` class is responsible for encoding and decoding the messages sent between the client and the server.

- **Methods**:
  - `encode(name, group, msg)`: Takes the sender's name, group, and message text, and returns a formatted string that includes a message header with the payload length followed by the payload itself.
  - `decode(raw)`: Parses the raw message string to extract the sender's name, group, and message text. It validates the message format and raises a `ValueError` if the format is incorrect.

### 2. Client.py (Client Functionality)

The `BasicClient` class facilitates client operations such as connecting to the server, sending messages, and handling the client state.

- **Core Features**:
  - **Connection Management**: Manages the TCP connection to the server, handling both connection setup and teardown.
  - **Message Sending**: Encodes messages using `BasicBuilder` and sends them over the socket.
  - **Group Management**: Allows the client to join different groups within the chat system.

- **Methods**:
  - `connect()`: Establishes a connection to the server using the provided IP address and port.
  - `send_msg(text)`: Sends a text message to the server after encoding it.
  - `join(group)`: Joins a specified group on the server.

### 3. Server.py (Server Functionality)

`BasicServer` class manages incoming client connections and processes incoming messages in a multi-threaded environment.

- **Core Features**:
  - **Listening**: The server listens on a specified port for incoming client connections.
  - **Session Handling**: Each client connection is managed by a separate thread to handle messages independently.
  - **Message Processing**: Decodes and processes messages received from clients.

- **Methods**:
  - `run()`: Starts the server and listens for incoming connections.
  - `_run_server()`: Handles the actual incoming connections and initializes session handlers for each connection.
  - `stop()`: Shuts down the server and cleans up resources.

### Session Handling (SessionHandler Class)

Handles individual client sessions.

- **Methods**:
  - `process(raw)`: Processes messages from the connected client.
  - `run()`: Continuously receives messages from the client and processes them.

## Setup Instructions

1. **Clone the Repository**:
   ```bash
   git clone <repository-url>

**This project implements a basic client-server architecture using Python's socket programming, which allows for bidirectional communication between a client and a server over a TCP/IP network. Here's a detailed breakdown of how the project is structured and how interconnectivity is achieved:**

### Project Structure

### Builder.py:

**Purpose**: Handles the encoding and decoding of messages sent between the client and the server.
**Methods**:
encode(name, group, msg): Encodes a message with a header containing the message length followed by the group name, sender name, and the message text.
decode(raw): Decodes a received message into its constituent parts—group, name, and message text.

### Client.py:

**Purpose**: Implements the functionality of a client that can connect to a server, send messages, join groups, and handle incoming messages.
**Key Components**:
Connection management: Establishes and closes connections to the server.
Message handling: Sends encoded messages using the Builder class.
Group management: Allows the client to join different message groups.

### Server.py:

**Purpose**: Manages incoming client connections and processes incoming messages.
**Key Components:**
Connection listener: Listens for incoming client connections on a specified IP address and port.
Session management: For each client, a new session handler (SessionHandler) is spawned to handle messages from that client.
Message processing: Received messages are processed, decoded, and appropriate actions are taken based on the content.


### Interconnectivity and Data Flow

### Starting the Server:
The server is initialized with an IP address and a port number.
It starts listening for incoming connections. Each connection request is accepted, and a new thread (SessionHandler) is started for each client to handle messages independently.

### Client Operations:
The client initializes a connection to the server using the server's IP address and port number.
Once connected, the client can perform operations such as sending messages or joining groups. Messages are first encoded using the Builder class before being sent.
The client maintains its connection and can continuously send messages until it decides to disconnect.

### Message Transmission:
When a client sends a message, it is encoded into a string that includes the message length, sender name, group, and the actual message text.
The server receives this encoded string, decodes it, and processes the message according to its type (e.g., a chat message or a command to join a group).

### Handling Incoming Messages at the Server:
Each client connection is handled in a separate thread, allowing the server to manage multiple clients simultaneously without blocking.
Messages are received in a loop, decoded, and appropriate responses are generated based on the message content.

### Session Management:
Both the client and server maintain their state regarding the connection and the current group the client is associated with.
The server tracks all active sessions and can handle disconnections gracefully by cleaning up resources associated with a session.
Conclusion

This project demonstrates a straightforward implementation of a networked communication system using Python sockets. The architecture allows for scalable and flexible communication between multiple clients and a server, with the ability to handle different types of messages and commands effectively.

**Encoding**: Constructs a payload string by combining group, name, and msg separated by commas.
Prepares a header that includes the length of the payload, padded to four digits, followed by the payload itself. The length helps the receiver know how many characters to expect, which is crucial for correctly parsing messages that may vary in length.

**Decoding** : Splits the raw input string based on commas into parts. It expects four parts due to the structure defined by encode: [message length, group, name, msg].
Checks if the split results in exactly four parts. If not, it raises a ValueError indicating an issue with the message format, which is crucial for detecting transmission errors or tampering.

**session handler** : This implementation allows for individual sessions with clients to be handled in separate threads, enabling concurrent processing of client requests in a networked application. Each session can receive, process, and handle messages independently of others.




