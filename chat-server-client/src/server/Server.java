package server;

import components.Connection;
import components.ConsoleHelper;
import components.Message;
import components.MessageType;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<String, Connection>();

    public static void main(String[] args) throws IOException {
        System.out.println("Enter server port:");
        int port = ConsoleHelper.readInt();

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.printf("Server started on port %s", port);
            while (true) {
                Socket socket = serverSocket.accept();
                new Handler(socket).start();
            }
        } catch (Exception e) {
            System.out.println("Start server error.");
        }
    }

    private static class Handler extends Thread {
        private Socket socket;

        private Handler(Socket socket) {
            this.socket = socket;
        }

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {
            while (true) {
                connection.send(new Message(MessageType.REQUEST_USERNAME));

                Message message = connection.receive();
                if (message.getType() != MessageType.SEND_USERNAME) {
                    System.out.printf("Message received from %s. Error type message.", socket.getRemoteSocketAddress());
                    continue;
                }

                String userName = message.getData();

                if (userName.isEmpty()) {
                    System.out.printf("Try to connect to server with empty name %s.", socket.getRemoteSocketAddress());
                    continue;
                }

                if (connectionMap.containsKey(userName)) {
                    System.out.printf("This name already used %s.", socket.getRemoteSocketAddress());
                    continue;
                }
                connectionMap.put(userName, connection);

                connection.send(new Message(MessageType.ACCEPT_USERNAME));
                return userName;
            }
        }

        public static void sendBroadcastMessage(Message message) {
            for (Map.Entry<String, Connection> entry: connectionMap.entrySet()) {
                Connection connection = entry.getValue();
                try {
                    connection.send(message);
                } catch (IOException e) {
                    System.out.printf("Error send message %s", connection.getRemoteSocketAddress());
                }
            }
        }

        private void notifyUsers(Connection connection, String userName) throws IOException {
            for (String name: connectionMap.keySet()) {
                if (name.equals(userName))
                    continue;
                connection.send(new Message(MessageType.NEW_USER_JOIN, name));
            }
        }

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException {
            while (true) {
                Message message = connection.receive();
                if (message.getType() == MessageType.MESSAGE) {
                    String data = message.getData();
                    Message newMessage = new Message(MessageType.MESSAGE, userName + ": " + data);
                    sendBroadcastMessage(newMessage);
                } else {
                    System.out.printf("Message received %s. Error message type.", socket.getRemoteSocketAddress());
                }
            }
        }

        public void run() {
            System.out.printf("New connection with %s.", socket.getRemoteSocketAddress());
            String userName;

            try (Connection connection = new Connection(socket)) {
                userName = serverHandshake(connection);
                sendBroadcastMessage(new Message(MessageType.NEW_USER_JOIN, userName));
                notifyUsers(connection, userName);
                serverMainLoop(connection, userName);
                connectionMap.remove(userName);
                sendBroadcastMessage(new Message(MessageType.USER_HAS_LEFT, userName));
                System.out.println("Connection closed.");
            } catch (IOException | ClassNotFoundException e) {
                System.out.printf("Data exchange error with %s.", socket.getRemoteSocketAddress());
            }

        }
    }
}



