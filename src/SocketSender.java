import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketSender extends Thread {
    ServerSocket myServerSocket = null;
    byte[] Data;
    int port = 1234;
    String host;
    QueueModule queueModule = new QueueModule();
    DataStore dataStore = new DataStore();

    public SocketSender(String host)
    {
        this.host = host;
    }


    // @SuppressWarnings("resource")
    public void run()
    {
        Generator generator = new Generator();
        Socket socket = null;



        while (true)
        {

            try {
                socket = new Socket(host, port);
            } catch (IOException e) {

            }
            DataOutputStream dOut = null;
            String string = generator.generateString();

            try {
                System.out.println(string);
                queueModule.addToQueue(string);
                if(queueModule.getQueue().size() >= 20){
                    dataStore.writeToFile(queueModule);
                }
                dOut = new DataOutputStream(socket.getOutputStream());
                dOut.writeUTF(string);
                dOut.flush();
                dOut.close();

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }
}
