import java.io.*;
import java.net.*;

public class ChatClient {
    private BufferedReader in;
    private PrintWriter out;
    private String lastMessage = null;
    private String clientId = null;

    public ChatClient(String serverAddress, int serverPort) throws IOException {
        Socket socket = new Socket(serverAddress, serverPort);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        //The start of a new series to receive messages
        new Thread(new ReceivedMessagesHandler()).start();
    }

    public void sendMessage(String message) {
        if (message.equals(lastMessage)) {
            System.out.println("Sorry, messages cannot be repeated.");
            return;
        }

        out.println(message);
        lastMessage = message;
    }

    class ReceivedMessagesHandler implements Runnable {
        @Override
        public void run() {
            try {
                String message;

                // An episode to receive messages from the server
                while ((message = in.readLine()) != null) {
                    if (message.startsWith("Your user ID is: ")) {
                        clientId = message.substring("Your user ID is: ".length());
                    } else if (!message.startsWith("User " + clientId)) {
                        // View the message if you are not from the current user
                        System.out.println(message);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try {
            ChatClient client = new ChatClient("localhost", 12345);
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Welcome to the chat room! Greetings to your friends!:");

            String message;

            // An episode to send messages to the server
            while ((message = reader.readLine()) != null) {
                client.sendMessage(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}