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
