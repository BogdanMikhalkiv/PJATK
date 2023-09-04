import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPClient {

    public static int gcd_3(int a, int b) {
        while (a != b) {
            if (a > b) {
                a = a - b;
            } else {
                b = b - a;
            }
        }
        return a;
    }

    public static void main(String[] args) {


            Socket socket = null;
            PrintWriter out = null;
            BufferedReader in = null;
            String address = "172.23.129.54";
            int port = 5000;

            try {
                socket = new Socket();
                socket.connect(new InetSocketAddress(address, port), 500);

                out = new PrintWriter(socket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                out.println(105425);
                out.println("172.23.129.36:5000");
            }
            catch (UnknownHostException e) {
                System.out.println("Unknown host");
                System.exit(-1);
            }
            catch  (IOException e) {
                System.out.println("No I/O");
                System.exit(-1);
            }






        try {
            socket.close();
        }
            catch (IOException e) {
            System.out.println("Cannot close the socket");
            System.exit(-1);
        }


    }

}

