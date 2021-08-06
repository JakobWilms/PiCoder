package com.github.jakobwilms.picoder;

public class Utils {

    public static String timestamp() {
        return "[" + System.currentTimeMillis() + "]  ";
    }

    public static String getTotalTime(long a, long b) {
        long dif = b - a;
        if (dif < 1_000) {
            return dif + " ms";
        } else if (dif < 3_600_000) {
            return dif / 1_000 + " s";
        } else {
            return dif / 3_600_000 + " h";
        }
    }

    public static int fromChar(char c) {
        if (c != '0' && c != '1' && c != '2' && c != '3' && c != '4' && c != '5' && c != '6' && c != '7' && c != '8' && c != '9') {
            System.exit(1);
            return 0;
        }
        return _fromChar(c);
    }

    private static int _fromChar(char c) {
        switch (c) {
            case '1':
                return 1;
            case '2':
                return 2;
            case '3':
                return 3;
            case '4':
                return 4;
            case '5':
                return 5;
            case '6':
                return 6;
            case '7':
                return 7;
            case '8':
                return 8;
            case '9':
                return 9;
            default:
                return 0;
        }
    }
}
