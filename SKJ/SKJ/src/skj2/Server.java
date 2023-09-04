package skj2;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        log("Server start");
        int port = 10000;
        log("Server socket creating");
        ServerSocket client = new ServerSocket(port);
        log("Server socket created");
        log("Server socket listening - accepting incomming connections");
        Socket socket = client.accept();
        log("Connection accepted");
       // File file = new File("/Users/bogda/Downloads/SKJ/Data/data.txt");

    }
    public static void log(String message) {
        System.out.println("[S]: " + message);
    }
}

