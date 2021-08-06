public class Test {

    public static void main(String[] args) {
        int i = 351;
        int i2 = 38392;

        int i3 = i ^ i2;
        System.out.println(i3);

        i3 ^= i2;
        System.out.println(i3);

        i3 = i ^ i2;
        i3 ^= i;
        System.out.println(i3);

        System.out.println(5 % 2);
        System.out.println(2 % 5);
    }
}
