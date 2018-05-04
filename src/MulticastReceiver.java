import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.Vector;

public class MulticastReceiver extends  Thread {
    protected MulticastSocket socket = null;
    private InetAddress group;
    protected byte[] buf = new byte[256];
    protected  byte[]data;

    private int port = 1234;
    private Vector<String> IPs = new Vector();
    private String IP;
    public MulticastReceiver(String ip)
    {
        IP=ip;
    }

    public void run() {

        IPs.add(IP);

        try {
            group = InetAddress.getByName("239.255.255.250");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        try {
            socket = new MulticastSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            socket.joinGroup(group);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            sleep(1000);
        } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        while (true)
        {
            DatagramPacket packet;
            buf = new byte[256];
            packet = new DatagramPacket(buf, buf.length);
            try {
                System.out.println("Waiting ... ");
                socket.receive(packet);

            } catch (IOException e) {
                e.printStackTrace();
            }

            data=new byte[packet.getLength()];
            System.arraycopy(packet.getData(), packet.getOffset(), data, 0, packet.getLength());

            String received = new String(data);
            System.out.println("IP : " + received);


            System.out.println("--------------------------");
            for(int i=0;i<IPs.size();i++)
            {
                System.out.println(IPs.get(i));
                System.out.println(IPs.get(i).length());
            }
            System.out.println("--------------------------");

            if(!IPs.contains(received))
            {
                IPs.add(received);
                SocketSender socketSender = new SocketSender(received);
                socketSender.start();
            }
        }
    }
}