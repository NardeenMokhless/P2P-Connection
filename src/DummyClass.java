public class DummyClass {


    public static void main(String[] args) {
        QueueModule queueModule = new QueueModule();
        DataStore dataStore = new DataStore();

        for (int i = 0; i < 100; i++) {
            queueModule.addToQueue("String");
        }

        while (!queueModule.isEmpty()){
            dataStore.writeToFile(queueModule);
        }

    }
}
