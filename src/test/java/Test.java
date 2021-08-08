import com.github.jakobwilms.picoder.Utils;

public class Test {

    public static void main(String[] args) {
        String seed = "01489597297498710942110834908110957195871904759806791";

        byte b = 25;
        int i = encrypt(b, 5, seed);
        System.out.println("Encrypt (25): " + i);

        byte b1 = 18;
        int i1 = encrypt(b1, 20, seed);
        System.out.println("Encrypt (18): " + i1);

        System.out.println("Decrypt (25):" + decrypt(i, 5, seed));
        System.out.println("Decrypt (18): " + decrypt(i1, 20, seed));
    }

    private static int encrypt(byte b, int i, String seed) {
        i = i < seed.length() ? i : i % seed.length();
        System.out.println("i: " + i);
        int tmpSeed = Utils.fromChar(seed.charAt(i)) * 10 + Utils.fromChar(seed.charAt(i + 1)) + Utils.fromChar(seed.charAt(seed.length() - i - 1)) + Utils.fromChar(seed.charAt(seed.length() - i - 2)) * 10;
        System.out.println("tmpSeed: " + tmpSeed);
        int x = b ^ tmpSeed;
        System.out.println("x: " + x);
        return x + 1;
    }

    private static byte decrypt(int b, int i, String seed) {
        i = i < seed.length() ? i : i % seed.length();
        System.out.println("i: " + i);
        int tmpSeed = Utils.fromChar(seed.charAt(i)) * 10 + Utils.fromChar(seed.charAt(i + 1)) + Utils.fromChar(seed.charAt(seed.length() - i - 1)) + Utils.fromChar(seed.charAt(seed.length() - i - 2)) * 10;
        System.out.println("tmpSeed: " + tmpSeed);
        int x = b - 1;
        return (byte) (x ^ tmpSeed);
    }
}
