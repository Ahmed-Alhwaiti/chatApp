# Java Console Chat Application

## Student Project Description

This project is a simple console-based chat application developed in Java. It uses the client-server model, where one server receives connections from multiple clients. Each client can send messages to the server, and the server broadcasts those messages to the other connected clients.

The main purpose of this project is to demonstrate basic Java networking, socket programming, input/output streams, and multithreading.

## Project Files

The project contains two main Java files:

1. `ChatServer.java`
2. `ChatClient.java`

## 1. ChatServer.java

`ChatServer.java` represents the server side of the chat application.

The server performs the following tasks:

- Opens a server socket on port `12345`.
- Waits for clients to connect.
- Assigns a unique user ID to each connected client.
- Creates a separate thread for each client.
- Receives messages from clients.
- Sends each message to all other connected clients.
- Removes a client from the active users list when the connection ends.

The server uses `ServerSocket`, `Socket`, `BufferedReader`, `PrintWriter`, `Runnable`, and `ConcurrentHashMap`.

## 2. ChatClient.java

`ChatClient.java` represents the client side of the chat application.

The client performs the following tasks:

- Connects to the server using `localhost` and port `12345`.
- Sends messages typed by the user to the server.
- Receives messages from other connected clients.
- Uses a separate thread to receive messages while the user is still able to type.
- Prevents the same message from being sent twice in a row.

The client uses `Socket`, `BufferedReader`, `PrintWriter`, and `Runnable`.

## Requirements

To run this project, the following are required:

- Java Development Kit, JDK
- Command Prompt, Terminal, or a Java IDE
- At least three terminal windows for testing:
  - One terminal for the server
  - One terminal for the first client
  - One terminal for the second client

To check whether Java is installed, run:

```bash

java -version

javac -version

How to Compile the Program



Open the terminal inside the project folder and run:



javac ChatServer.java ChatClient.java



If the compilation is successful, Java will generate .class files automatically.



Examples of generated files:



ChatServer.class

ChatClient.class

ChatServer$ClientHandler.class

ChatClient$ReceivedMessagesHandler.class

How to Run the Program

Step 1: Start the Server



Open the first terminal window and run:



java ChatServer



The server must be started before running any client.



Step 2: Start the First Client



Open a second terminal window in the same project folder and run:



java ChatClient

Step 3: Start the Second Client



Open a third terminal window in the same project folder and run:



java ChatClient



Now, messages typed in one client window will appear in the other client window.


Example server  output:

Welcome everyone in the chat application!
The server began on the port 460080
 Waiting for the rest of the customers successful!
Someone joined the chat now!: User1
You received a message from the customer User1: hi
Someone joined the chat now!: User2
You received a message from the customer User2: hi 2
You received a message from the customer User1: how are u user 2
You received a message from the customer User2: iam fine, what about u


Example client 1 output:


Welcome to the chat room! Greetings to your friends!
hi 2
how are u user 2
iam fine, what about u

Example client 2 output:

Welcome to the chat room! Greetings to your friends!:
hi
hi 2
how are u user 2
iam fine, what about u






Main Concepts Demonstrated



This project demonstrates the following Java concepts:



Client-server architecture

Socket programming

ServerSocket and Socket classes

Input and output streams

BufferedReader

PrintWriter

Multithreading

Runnable interface

ConcurrentHashMap

Basic message broadcasting

How the Application Works



First, the server starts and waits for clients. When a client connects, the server gives that client a unique ID such as User1 or User2.



The server creates a new thread for each client. This allows multiple users to connect and chat at the same time.



When a client sends a message, the server receives it and sends it to all other connected clients. The sender does not receive their own message back from the server.



The client also has a separate thread for receiving messages. This allows the user to receive messages while typing new ones.



Common Errors and Solutions

1\\\\. javac is not recognized



This means the JDK is not installed or the Java path is not configured correctly.



Solution:



Install the JDK and make sure it is added to the system PATH.



2\\\\. Address already in use



This means port 12345 is already being used by another program.



Solution:



Change the port number in both ChatServer.java and ChatClient.java, then compile the files again.



Example:



12346

3\\\\. Client Cannot Connect to Server



This usually happens when the server is not running.



Solution:



Start the server first by running:



java ChatServer



Then run the client.



Limitations



This is a basic chat application. It does not include:



Graphical user interface

Login system

Password protection

Message encryption

Chat history storage

Private messages

File sharing

Possible Future Improvements



The project can be improved by adding:



A graphical interface using JavaFX or Swing

Custom usernames

Private messaging

Message timestamps

Chat history saved in a file

Better exception handling

Secure communication using encryption

Conclusion



This Java chat application shows how networking and multithreading can be used to build a simple real-time communication program. The server manages client connections and broadcasts messages, while each client can send and receive messages through the console. This project is useful for understanding the basics of Java socket programming and concurrent client handling.





This README matches the uploaded chat application code, which contains separate server


