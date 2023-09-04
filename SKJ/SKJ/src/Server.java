import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        log("Simple HTTP Server starts");
        int serverPort =  10000;
        log("Server socket creating");
        ServerSocket serverSocket = new ServerSocket(serverPort);
        log("Server socket created");
        log("Server socket listening - accepting incomming connections");
        Socket client = serverSocket.accept();
        log("Server: connections created: " + client.getInetAddress().toString() +":" + client.getPort());
        log("TCP streams collecting");
        InputStream sis = client.getInputStream();
        OutputStream sos = client.getOutputStream();
        InputStreamReader sisr = new InputStreamReader(sis);
        OutputStreamWriter sosw = new OutputStreamWriter(sos);
        BufferedReader br = new BufferedReader(sisr);
        BufferedWriter bw = new BufferedWriter(sosw);
        log("Messege sending");
        String ms1 = "19362";
        String ms2 = "13463";
        bw.write(ms1);
        bw.newLine();
        bw.write(ms2);
        bw.newLine();
        bw.newLine();
        log("Messege sended");
        log("Messege receiving");
        String n = br.readLine();
        int liczba = Integer.parseInt(n);
        System.out.println(liczba);
        String x = br.readLine();
        System.out.println(x);
        log("messege received :" + liczba + ", " + x);
        log("Messege sending");
        String ms3 = liczba + " " + x;
        bw.write(ms3);
        bw.newLine();
        bw.newLine();
        bw.flush();
        log("Messege sended:" + ms3);
        log("Client socket closing");
        client.close();
        log("Client socket closed");
        log("Server socket closing");
        serverSocket.close();
        log("Server socket closed");
        log("Simple HTTP Server ends");
    }
    public static void log(String message) {
        System.out.println("[S]: " + message);
    }
}
