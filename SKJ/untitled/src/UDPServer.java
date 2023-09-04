import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UDPServer {
    public static void main(String[] args) throws SocketException {
        DatagramSocket datagramSocket= new DatagramSocket(9000);
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        while (true) {
            threadPool.submit(new TCPHandler(datagramSocket));
        }

    }
}