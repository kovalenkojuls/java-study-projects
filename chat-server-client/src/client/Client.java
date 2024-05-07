package client;

import components.Connection;
import components.ConsoleHelper;
import components.Message;
import components.MessageType;
import java.io.IOException;
import java.net.Socket;

public class Client {
    private volatile boolean clientConnected = false;
    protected Connection connection;

    protected String getServerAddress() {
        System.out.println("Enter server ip:");
        return ConsoleHelper.readString();
    }

    protected int getServerPort() {
        System.out.println("Enter server port:");
        return ConsoleHelper.readInt();
    }

    protected String getUserName() {
        System.out.println("Enter user name:");
        return ConsoleHelper.readString();
    }

    protected boolean shouldSendTextFromConsole() {
        return true;
    }

    protected SocketThread getSocketThread() {
        return new SocketThread();
    }

    protected void sendTextMessage(String text) {
        try {
            connection.send(new Message(MessageType.MESSAGE, text));
        } catch (IOException e) {
            System.out.println("Error send message.");
            clientConnected = false;
        }
    }

    public void run() {
        SocketThread socketThread = getSocketThread();
        socketThread.setDaemon(true);
        socketThread.start();

        try {
            synchronized (this) {
                wait();
            }
        } catch (InterruptedException e) {
            System.out.println("Error in client");
            return;
        }

        if (clientConnected) System.out.println("Сonnection is successful.");

        while (clientConnected) {
            String text = ConsoleHelper.readString();
            if (text.equals("exit")) break;
            if (shouldSendTextFromConsole()) sendTextMessage(text);
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.run();
    }

    public class SocketThread extends Thread {
        protected void processIncomingMessage(String message) {
            System.out.println(message);
        }

        protected void informAboutAddingNewUser(String userName) {
            System.out.printf("User with name %s joined.", userName);
        }

        protected void informAboutDeletingNewUser(String userName) {
            System.out.printf("User with name %s is gone.", userName);
        }

        protected  void notifyConnectionStatusChanged(boolean clientConnected) {
            Client.this.clientConnected = clientConnected;
            synchronized (Client.this) {
                Client.this.notify();
            }
        }

        protected void clientHandshake() throws IOException, ClassNotFoundException {
            while (true) {
                Message message = connection.receive();
                if (message.getType() == MessageType.REQUEST_USERNAME) {
                    String userName = getUserName();
                    connection.send(new Message(MessageType.SEND_USERNAME, userName));
                    continue;
                } else if (message.getType() == MessageType.ACCEPT_USERNAME) {
                    notifyConnectionStatusChanged(true);
                    return;
                } else {
                    throw new IOException("Unexpected MessageType.");
                }
            }
        }

        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            while (true) {
                Message message = connection.receive();

                if (message.getType() == MessageType.MESSAGE) {
                    processIncomingMessage(message.getData());
                } else if (message.getType() == MessageType.NEW_USER_JOIN) {
                    informAboutAddingNewUser(message.getData());
                } else if (message.getType() == MessageType.USER_HAS_LEFT) {
                    informAboutDeletingNewUser(message.getData());
                } else {
                    throw new IOException("Unexpected MessageType.");
                }
            }
        }

        public void run() {
            String ip = getServerAddress();
            int port = getServerPort();

            try (Socket socket = new Socket(ip, port);) {
                connection = new Connection(socket);
                clientHandshake();
                clientMainLoop();
            } catch (IOException | ClassNotFoundException e) {
                notifyConnectionStatusChanged(false);
            }
        }
    }
}
