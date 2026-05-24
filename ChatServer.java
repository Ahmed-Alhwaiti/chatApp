import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class ChatServer {
    //map for the storage of connected customers
    private static ConcurrentMap<String, PrintWriter> clients = new ConcurrentHashMap<>();
    private static int clientIdCounter = 1;

    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Welcome everyone in the chat application!");
            System.out.println("The server began on the port 460080\n Waiting for the rest of the customers successful!");

            //An episode to receive continuous connected clients
            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(new ClientHandler(clientSocket)).start();
            }
        }
    }

    static class ClientHandler implements Runnable {
        private Socket clientSocket;
        private BufferedReader in;
        private PrintWriter out;
        private String clientId;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                synchronized (ChatServer.class) {
                    //Create a unique identifier for each customer
                    clientId = "User" + clientIdCounter++;
                }

                clients.put(clientId, out);
                out.println("Your user ID is: " + clientId);
                System.out.println("Someone joined the chat now!: " + clientId);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
                String input;

                // An episode to receive messages from the customer
                while ((input = in.readLine()) != null) {
                    System.out.println("You received a message from the customer" + " " + clientId + ": " + input);
                    broadcastMessage(clientId, input);
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            } finally {
                //Remove the customer from the list when the connection is over
                clients.remove(clientId);
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        //Spread the message to all connected customers except for the sender
        private void broadcastMessage(String senderId, String message) {
            for (String clientId : clients.keySet()) {
                if (!clientId.equals(senderId)) {
                    clients.get(clientId).println(message);
                }
            }
        }
    }
}
