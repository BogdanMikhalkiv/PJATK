import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseNode {

    private int klucz ;
    private int wartosc;
    private int port;

    HashMap<Integer,Socket> neighbours = new HashMap<>();


    public DatabaseNode(int klucz, int wartosc, int port) {
        this.klucz = klucz;
        this.wartosc = wartosc;
        this.port = port;

    }

    public void addNeighbour(Integer port, Socket socket) {
        if (!neighbours.containsKey(port)) {
            neighbours.put(port, socket);
            PrintWriter out = null;
            BufferedReader in = null;

            try {
                out = new PrintWriter(socket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                out.println("PORT=" +this.port);
                System.out.println("connected port " + this.port);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setKlucz(int klucz) {
        this.klucz = klucz;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setWartosc(int wartosc) {
        this.wartosc = wartosc;
    }

    public int getKlucz() {
        return klucz;
    }

    public int getPort() {
        return port;
    }

    public int getWartosc() {
        return wartosc;
    }



    public void newRekord(int klucz, int wartosc) {
        setKlucz(klucz);
        setWartosc(wartosc);
        System.out.println("OK");
    }



    public  class ServerThread extends Thread {
        private final Socket socket;




        public ServerThread(Socket socket) {
            super();
            this.socket = socket;
        }
        public void run() {
            try {
                PrintWriter out = null;
                BufferedReader in = null;

                out = new PrintWriter(socket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                while (true) {



                    String res = in.readLine();
                    if (!(res == null)) {

                        if (res.split("=")[0].equals("PORT")) {
                            int neighboursPort = Integer.parseInt(res.split("=")[1]);
                            if (!neighbours.containsKey(neighboursPort)) {

                                Socket socket = new Socket();
                                socket.connect(new InetSocketAddress("localhost", neighboursPort), 500);
                                System.out.println("Node =" +port + ", połączenie nawiązane z węzłem port - " + neighboursPort );

                                addNeighbour(neighboursPort, socket);
                            }

                            //System.out.println("recieve");

                        }
                        if (res.split("=")[0].equals("Klucz,wartosc")){

                            System.out.println("klucz i  wartosc do zmian - " + klucz+":"+wartosc);
                            klucz = Integer.parseInt(res.split("=")[1].split(":")[0]);
                            wartosc = Integer.parseInt(res.split("=")[1].split(":")[1]);
                            System.out.println("klucz i  wartosc po zmian - " + klucz+":"+wartosc);
                            out.println("OK");
                        }
                    }
                }

            } catch (Exception e){
                System.out.println(this + ": ERROR - " + e.getMessage());
                e.printStackTrace();
            }
            try {
                socket.close();
                System.out.println("Socket Closed " + socket.getPort());
            } catch (IOException e) {
                System.out.println('2');
            }
        }
        @Override
        public String toString() {
            return "Client " + socket.getPort();
        }

    }

    @Override
    public String toString() {
        return "DatabaseNode{" +
                "klucz=" + klucz +
                ", wartosc=" + wartosc +
                ", port=" + port +
                '}';
    }

    public class ListenThread extends Thread {

    @Override
    public void run() {
        ServerSocket server = null;
        Socket client = null;
        try {
            server = new ServerSocket(port);
        }
        catch (IOException e) {
            System.out.println("Could not listen");
            System.exit(-1);
        }

        System.out.println("Server listens on port: " + server.getLocalPort());
        while(true) {
            try {
                client = server.accept();
                System.out.println("-Connected client" + client.getPort());
            }
            catch (IOException e) {
                System.out.println("Accept failed");
                System.exit(-1);
            }

            (new ServerThread(client)).start();
        }
    }


}

    public void listenSocket() throws IOException {
        (new ListenThread()).start();
    }






}