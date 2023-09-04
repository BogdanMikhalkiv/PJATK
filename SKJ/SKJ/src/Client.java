import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) throws IOException {
        log("Client starts");
        String serverName = " 172.21.48.197";
//        String serverName = "localhost";
        int serverPort = 20003;
        log("Server name resolving (DNS)");
        InetAddress serverAddress = InetAddress.getByName(serverName);
        log("Server name resolved: " + serverAddress.toString());
        log("TCP connection creating - socket opening");
        Socket client = new Socket(serverAddress, serverPort);
        log("TCP connection created - socket opened");
        log("TCP streams collecting");
        InputStream sis = client.getInputStream();
        OutputStream sos = client.getOutputStream();
        InputStreamReader sisr = new InputStreamReader(sis);
        OutputStreamWriter sosw = new OutputStreamWriter(sos);
        BufferedReader br = new BufferedReader(sisr);
        BufferedWriter bw = new BufferedWriter(sosw);
        log("sending a messege");
        String ms1 = "19362";
        String ms2 = "172.23.129.141:"+10000;
        bw.write(ms1);
        bw.newLine();
        bw.write(ms2);
        bw.newLine();
        bw.newLine();
        bw.flush();
        log("Messege sended:" + ms2);
//        String receid = br.readLine();
//        System.out.println("receisd ; " + receid);
        log("TCP connection clocing - socket closing");
        sis.close();
        sos.close();
        client.close();
        log("TCP connection closed - socket closed");
        log("Client ends");
    }

    public static void log(String message) {
        System.out.println("[C]: " + message);
    }

}
