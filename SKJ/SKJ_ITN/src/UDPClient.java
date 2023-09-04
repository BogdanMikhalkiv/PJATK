import java.io.IOException;
import java.net.*;

public class UDPClient {

    private static int port = 5555;
    private static String ip = "172.23.129.54";
    private DatagramSocket socket;
    private InetAddress address;

    public UDPClient(){
        try {
            socket = new DatagramSocket(5000);
            //System.out.println(socket.getLocalPort());
            address = InetAddress.getByName(ip);
        } catch (UnknownHostException e) {
            System.out.println("Unknown Host");
            System.exit(-1);
        } catch (SocketException e) {
            System.out.println("Socket Error");
            System.exit(-1);
        }
    }

    public void sendMsg(String msg){
        byte[] buf = msg.getBytes();
        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, port);

        try {
            socket.send(packet);
        }
        catch (IOException e){
            System.out.println("No IO");
            System.exit(-1);
        }

    }

    public String recieve() throws UnknownHostException {
        System.out.println("jestem tutaj w recieve i czekam");
        byte[] buf = new byte[1024];
        InetAddress myAdress = InetAddress.getByName("192.168.8.169");
        DatagramPacket packet = new DatagramPacket(buf, buf.length, myAdress, 5000);

        try {
            socket.receive(packet);
            System.out.println(packet.getPort());
        }
        catch (IOException e){
            System.out.println("No IO");
            System.exit(-1);
        }

        return new String(packet.getData(),0, packet.getLength());

    }

    public void close() {
        socket.close();
    }

    public static void main(String[] args) throws UnknownHostException {



        UDPClient client = new UDPClient();
//        client.sendMsg("s19362");
        String odp = "";
        try {
            odp = client.recieve();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        System.out.println(odp);

        int res = Integer.parseInt(odp);

        int result = res + res;
        System.out.println(result);

        client.sendMsg(result + "");
        String odp3 = client.recieve();
        System.out.println(odp3);

        client.close();

    }
}
