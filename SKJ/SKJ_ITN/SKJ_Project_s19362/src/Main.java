import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        DatabaseNode databaseNode1 = new DatabaseNode(17,255, 9090);
        databaseNode1.listenSocket();

        Scanner scanner = new Scanner(System.in);
        String comand = "";
        List<String> list = new ArrayList<>();
        List<String> nodes = new ArrayList<>();
        while (true) {
            nodes = new ArrayList<>();
            System.out.println("hello");

            comand = scanner.nextLine();

            list = Arrays.asList(comand.split("\\s"));
            for (int i = 6; i < list.size(); i++) {
                nodes.add(list.get(i));
            }

            //System.out.println("." + list.get(2) + ".");
            if (comand.equals("-terminate all")) {
                System.exit(0);
            }

            if (list.get(1).equals("DatabaseNode") &&
                    list.get(2).equals("-tcpport") &&
                    list.get(4).equals("-record") &&
                    list.get(6).equals("-connect"))
            {
                //java DatabaseNode -tcpport 9991 -record 17:256 -connect localhost:9090
                DatabaseNode databaseNode = new DatabaseNode(
                        Integer.parseInt(list.get(5).split(":")[0]),
                        Integer.parseInt(list.get(5).split(":")[1]),
                        Integer.parseInt(list.get(3))
                );
                databaseNode.listenSocket();



                for (int i = 1; i < nodes.size(); i+=2) {
                    Socket socket = new Socket();
                    socket.connect(new InetSocketAddress("localhost",
                                    Integer.parseInt(nodes.get(i).split(":")[1])),
                                    500
                    );
                    System.out.println("NODE = " + list.get(3) + ", połączenie nawiązane z węzłem port - " + Integer.parseInt(nodes.get(i).split(":")[1]));
                    databaseNode.addNeighbour(Integer.parseInt(nodes.get(i).split(":")[1]), socket);
                }


            }

            if ((list.get(1).equals("DatabaseClient") &&
                    list.get(2).equals("-gateway")) &&
                    list.get(4).equals("-operation")&&
                    !list.get(5).equals("new-record"))
            {

            DatabaseClient databaseClient = new DatabaseClient(Integer.parseInt(list.get(3).split(":")[1]));
            databaseClient.runClient();

            }
            if(list.get(1).equals("DatabaseClient") &&
                    list.get(2).equals("-gateway") &&
                    list.get(4).equals("-operation") &&
                    list.get(5).equals("new-record")
            ) {
                System.out.println("tut");
                //java DatabaseNode -tcpport 9991 -record 17:256 -connect localhost:9090
                //java DatabaseClient -gateway localhost:9991 -operation new-record 20:255
                //java DatabaseClient -gateway localhost:9991 -operation get-value 17

                DatabaseClient databaseClient = new DatabaseClient(Integer.parseInt(list.get(3).split(":")[1]));
                databaseClient.runClient(Integer.parseInt(list.get(6).split(":")[0]), Integer.parseInt(list.get(6).split(":")[1]));
            }








        }

    }
}
