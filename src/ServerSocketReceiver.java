import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketReceiver extends Thread {
    ServerSocket myServerSocket = null;
    byte[] Data;
    int port = 1234;
    QueueModule queueModule = new QueueModule();
    DataStore dataStore = new DataStore();

    public void run()
    {
        try {
            myServerSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Socket socket = null;
        int T=20;

        while (true)
        {

            try {
                socket = myServerSocket.accept();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            DataInputStream dIn = null;
            try {
                dIn = new DataInputStream(socket.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                String str = (String)dIn.readUTF();
                System.out.println(str);
                queueModule.addToQueue(str);
                if(queueModule.getQueue().size() >= 20){
                    dataStore.writeToFile(queueModule);
                }
//                System.out.println(str);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
