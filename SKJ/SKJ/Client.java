import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) throws IOException {
        log("Client starts");
        int counter = 0;
        String serverName = "172.21.48.102";
//        String serverName = "localhost";

        for (int i = 33247; i <= 33768; i++) {
            int serverPort = i;
            String line = null;

            log("Server name resolving (DNS)");
            InetAddress serverAddress = InetAddress.getByName(serverName);
            log("Server name resolved: " + serverAddress.toString());
            log("TCP connection creating - socket opening");
            Socket client = null;
            try {
                client = new Socket(serverAddress, serverPort);
            }catch (IOException e){
                continue;
            }
            if ((client != null && client.isConnected())) {
                counter++;
                log("TCP connection created - socket opened");
                log("TCP streams collecting");
                InputStream sis = client.getInputStream();
                OutputStream sos = client.getOutputStream();
                InputStreamReader sisr = new InputStreamReader(sis);
                OutputStreamWriter sosw = new OutputStreamWriter(sos);
                BufferedReader br = new BufferedReader(sisr);
                BufferedWriter bw = new BufferedWriter(sosw);
                log("sending a messege");
                String ms1 = "697262";
                int X = Integer.parseInt(ms1);
                bw.write(ms1);
                bw.newLine();
                bw.flush();
                int N = Integer.parseInt(br.readLine());
                log("Messege sended:" + ms1);
                log(counter + "");
                log(N+"");
                log(N+X +"");



                log("TCP connection clocing - socket closing");
                sis.close();
                sos.close();
                client.close();
                log("TCP connection closed - socket closed");
                log("Client ends");
            }
        }
    }
    public static void log(String message) {
        System.out.println("[C]: " + message);
    }
}

