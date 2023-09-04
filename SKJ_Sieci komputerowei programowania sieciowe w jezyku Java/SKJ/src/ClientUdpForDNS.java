import java.io.IOException;
import java.net.*;

public class ClientUdpForDNS {
    public static void main(String[] args) throws IOException {
        System.out.println("client start");
        System.out.println();
        String s = "328036";
        String dnsName = "172.21.48.130";
//        String dnsName = "48.58.68.68";

        InetAddress dnsAdress = InetAddress.getByName(dnsName);
        int dnsPort = 10006;
        DatagramPacket packet = new DatagramPacket(s.getBytes(), s.getBytes().length, dnsAdress, dnsPort);
        System.out.println("UDP(datagram) socket opening");
        DatagramSocket datagramSocket = new DatagramSocket();
        System.out.println("UDP(datagram) socket opend");

        System.out.println("UDP(datagram) socket sending");
        datagramSocket.send(packet);
        System.out.println("reesponsed receiving");
        DatagramPacket datagramPacket = new DatagramPacket(new byte[2000],1500);
        datagramSocket.receive(datagramPacket);
        System.out.println("received from " + datagramPacket.getAddress() + ":" +datagramPacket.getPort() );
         byte []data = datagramPacket.getData();
        int len = datagramPacket.getLength();
        String text = "";
        String dataRec = new String(data, 0, len);

        for (int i = 0; i < len; i++) {
            text =  text + " " + data[i];
        }
        System.out.println("recieved " + dataRec);
        System.out.println("recieved bytes " + text);


        System.out.println("UDP(datagram) socket closing");
         datagramSocket.close();
        System.out.println("UDP(datagram) socket closed");

        System.out.println("client end");

    }
}
