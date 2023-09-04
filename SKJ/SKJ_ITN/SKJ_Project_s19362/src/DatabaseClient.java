import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class DatabaseClient {

    int port;

    public DatabaseClient(int port) {
        this.port = port;
    }



    public void runClient() {

        Socket socket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        String address = "localhost";


        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress(address, port), 500);
            System.out.println("Connected client to port - " + port);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            Scanner scanner = new Scanner(System.in);
            String line = "";
            while (true) {
                System.out.println("while in client");
                line = scanner.nextLine();
                if (line.equals("-end")) {
                    break;
                }


                if (line.split("\\s")[0].equals("new-record")) {
                    System.out.println("wysylaje - " + line.split("\\s")[1].split(":")[0] + ":" + line.split("\\s")[1].split(":")[1]);
                    out.println("Klucz,wartosc=" + line.split("\\s")[1].split(":")[0]+":"+line.split("\\s")[1].split(":")[1]);
                    System.out.println(in.readLine());
                }
            }
//
//            out.println(105425);
//            out.println("172.23.129.36:5000");
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

    public void runClient(int klucz, int wartosc) {
        Socket socket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        String address = "localhost";


        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress(address, port), 500);
            System.out.println("Connected client to port - " + port);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//
            System.out.println("wysylaje - " + klucz+":"+wartosc);
            out.println("Klucz,wartosc=" + klucz+":"+wartosc);
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

