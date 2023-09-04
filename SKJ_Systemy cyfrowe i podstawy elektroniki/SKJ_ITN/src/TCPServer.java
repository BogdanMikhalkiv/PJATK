import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
public class TCPServer {

    public static class ServerThread extends Thread {
        private final Socket socket;
        static ArrayList<Integer> countClients = new ArrayList<>();
        static ArrayList<Integer> countNumbers = new ArrayList<>();

        static  ArrayList<Integer> sumaLiczb = new ArrayList<>();

        public ServerThread(Socket socket) {
            super();
            this.socket = socket;
        }
        public void run() {
            try {
                countClients.add(socket.getPort());
                sleep(1000);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                // pnk2
                int pierwszaLiczba = Integer.parseInt(in.readLine());
                out.println(pierwszaLiczba);


                System.out.println("liczba - " + this + " " + pierwszaLiczba);

                sumaLiczb.add(pierwszaLiczba);

                int wynik = 0;
                for (int i = 0; i < sumaLiczb.size(); i++) {
                    wynik += sumaLiczb.get(i);
                }

                System.out.println(sumaLiczb);


                out.println(wynik);
                System.out.println(this + ": Suma wyslana - " + wynik );


                // pnk3

                System.out.println( "NWD " + nwd(sumaLiczb));
                out.println(nwd(sumaLiczb));



                //pnk4

                int liczba1 = Integer.parseInt(in.readLine());
                int liczba2 = Integer.parseInt(in.readLine());
                int liczba3 = Integer.parseInt(in.readLine());
                System.out.println(liczba1);
                System.out.println(liczba2);
                System.out.println(liczba3);


                int sumaL = liczba1 + liczba2 + liczba3;

                out.println(678665);
                System.out.println("punkt4 - " + sumaL );

                //pnk5
                String napis = in.readLine();
                System.out.println(napis);
                String nowyNapis= "";

                for (int i = 0; i < napis.length()-1; i++) {
                    if (napis.charAt(i) != '1') {
                        nowyNapis += napis.charAt(i);
                    }
                }
                System.out.println(nowyNapis);
                out.println(nowyNapis);

                //punkt6
                double x = Double.parseDouble(in.readLine());

                double k = 0;

                for (double i = 0; i <= 9.0; i++) {
                    if (Math.pow(i,3.0) < x)  {
                        k = i;
                    }
                }
                out.println(k +"");

                String result = "";

                result = in.readLine();

                System.out.println("RESULT -" + Integer.parseInt(result));


            } catch (IOException e1) {
                System.out.println(this + ": ERROR - " + e1.getMessage());
                e1.printStackTrace();
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
    public void listenSocket(int port) throws IOException {
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
                //ServerThread.countClients++;
                System.out.println("-Connected client" + client.getPort());
            }
            catch (IOException e) {
                System.out.println("Accept failed");
                System.exit(-1);
            }

            (new ServerThread(client)).start();
        }

    }

    public static void main(String[] args) throws IOException {
        if(args.length < 1)
        {
            System.out.println("Too few parameters: got " + args.length + ", expected 1");
            return;
        }
        int port = Integer.parseInt(args[0]);
        TCPServer server = new TCPServer();
        server.listenSocket(port);
    }
    public static int NWD(int pierwsza, int druga)
    {
        while (pierwsza != druga)
        {
            if (pierwsza > druga)
            {
                pierwsza = pierwsza - druga;
            }
            else
            {
                druga = druga - pierwsza;
            }
        }
        return pierwsza;
    }



    public static int nwd(ArrayList<Integer> tab) {
        int nwdv = 0;
        for (int i = 0; i < tab.size()-1; i++) {
            nwdv = NWD(tab.get(i),tab.get(i+1));
        }
        return nwdv;
    }

}