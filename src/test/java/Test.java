import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Test {

    public static void main(String[] args) {
        String key = "0224215483257289";
        File testFile = new File("/home/jakob/Desktop/testkey");
        try {
            //noinspection ResultOfMethodCallIgnored
            testFile.createNewFile();
            FileOutputStream stream = new FileOutputStream(testFile);

            byte[] bytes = key.getBytes();
            System.out.println("bytes: " + Arrays.toString(bytes));
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = (byte) (bytes[i] + 20);
            }
            System.out.println("bytes +20: " + Arrays.toString(bytes));
            stream.write(bytes);
            stream.close();


            FileInputStream inputStream = new FileInputStream(testFile);
            bytes = inputStream.readAllBytes();
            System.out.println("bytes read: " + Arrays.toString(bytes));
            stream.close();

            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = (byte) (bytes[i] - 20);
            }

            System.out.println("String: " + new String(bytes, StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
