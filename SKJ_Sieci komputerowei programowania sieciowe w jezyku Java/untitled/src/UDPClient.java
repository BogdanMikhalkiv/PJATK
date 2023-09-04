import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
//765553
public class UDPClient {
    public static void main(String[] args) throws IOException {
        Socket clientSocket = new Socket("172.21.48.127", 20004);

        PrintWriter outToServer = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        outToServer.println("21268");
        outToServer.println("172.23.129.147:9000");

        String line;
        while ((line = inFromServer.readLine()) != null){
            System.out.println(line);
        }
        clientSocket.close();

    }
}