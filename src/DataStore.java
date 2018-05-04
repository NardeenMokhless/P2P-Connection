import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class DataStore {

    File file = new File("data.txt");
    GUI gui;

    DataStore(){

    }

    DataStore(GUI gui){
        this.gui = gui;
    }

    public void writeToFile(QueueModule queue){

        PrintWriter printWriter = null;
        try  {
            printWriter = new PrintWriter(new FileOutputStream(file, true));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 20; i++) {
            if(queue.isEmpty()){
                break;
            }

            printWriter.append(queue.pop() + "\n");
        }

        printWriter.close();
    }

    public void readFromFile(){
        Scanner scanner = null;

        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (scanner.hasNext()){
            //System.out.println(scanner.nextLine());
            this.gui.textArea.append(scanner.nextLine() + "\n");
        }
    }
}
