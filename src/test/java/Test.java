import java.util.Arrays;

public class Test {

    public static void main(String[] args) {
        byte[] bytes = "1".getBytes();
        System.out.println(Arrays.toString(bytes));
        for (byte b : bytes) {
            System.out.println((byte) (b * 5 + 1));
        }
    }
}
