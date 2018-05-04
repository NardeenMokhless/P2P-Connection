import java.util.Random;

public class Generator {
    String[] strings = {"I like banana.","I eat chicken.", "I love shawerma."};


    public String generateString(){
        String randomString;
        Random random = new Random();
        randomString = strings[random.nextInt(strings.length)];
        return randomString;
    }
}
