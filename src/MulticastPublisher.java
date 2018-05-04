import java.io.IOException;
import java.net.*;

public class MulticastPublisher extends  Thread {
    private MulticastSocket socket;
    {
        try {
            socket = new MulticastSocket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private InetAddress group;
    boolean moreQuotes = true;
    private byte[] buf;
    private int port = 1234;
    private String myIP;

    MulticastPublisher(String ip)
    {
        myIP = ip;
        port = 1234;
    }

    public void run() {
        try {
            group = InetAddress.getByName("239.255.255.250");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        try {
            socket.joinGroup(group);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            try {
                buf = new byte[256];
                buf = myIP.getBytes();


                DatagramPacket packet;
                packet = new DatagramPacket(buf, buf.length, group, port);
                socket.send(packet);
                System.out.println("Data is sent");

                try {
                    sleep(6500);
                } catch (InterruptedException e) {}

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
