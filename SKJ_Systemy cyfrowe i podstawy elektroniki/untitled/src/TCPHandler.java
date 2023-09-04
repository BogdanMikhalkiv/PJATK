import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.SocketException;

public class TCPHandler extends Thread {

    DatagramSocket datagramSocket;
    public TCPHandler(DatagramSocket datagramSocket){
        this.datagramSocket = datagramSocket;
    }

    @Override
    public void run() {
        DatagramPacket datagramPacket = new DatagramPacket(new byte[2000],2000);
        System.out.println("Ok");
        try {
            datagramSocket.receive(datagramPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte b[] = "21268".getBytes();
        byte b1[] = "810656".getBytes();
        try {
            datagramSocket.send(new DatagramPacket(b,b.length,datagramPacket.getAddress(),datagramPacket.getPort()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            datagramSocket.send(new DatagramPacket(b1,b1.length,datagramPacket.getAddress(),datagramPacket.getPort()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        DatagramPacket datagramPacket1 = new DatagramPacket(new byte[2000],1600);
        DatagramPacket datagramPacket2 = new DatagramPacket(new byte[2000],1600);
        try {
            datagramSocket.receive(datagramPacket1);
            datagramSocket.receive(datagramPacket2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[]bytes = datagramPacket1.getData();
        byte[]bytes1= datagramPacket2.getData();
        String line = new String(bytes);
        String line2 = new String(bytes1);
        System.out.println(line+line2);

    }
}