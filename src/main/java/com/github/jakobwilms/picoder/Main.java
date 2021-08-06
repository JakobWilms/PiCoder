package com.github.jakobwilms.picoder;

import com.github.jakobwilms.picoder.decode.DecodeUtils;
import com.github.jakobwilms.picoder.encode.EncodeUtils;
import com.github.jakobwilms.picoder.key.KeyUtils;

public class Main {

    public static void main(String[] args) {
        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            System.out.println("Windows not supported yet!");
            System.exit(0);
        }

        long time = System.currentTimeMillis();

        System.out.println("-".repeat(60));
        System.out.println("--- START --- AT " + time + " ---");
        System.out.println("-".repeat(60));

        if (args.length == 5) {
            if (args[0].equals("encode")) {
                EncodeUtils.encode(args[1], args[2], args[3], args[4]);
            } else if (args[0].equals("decode")) {
                DecodeUtils.decode(args[1], args[2], args[3], args[4]);
            }
        } else if (args.length == 2) {
            if (args[0].equals("generate-key")) {
                KeyUtils.generate(args[1]);
            }
        }

        long time2 = System.currentTimeMillis();

        System.out.println("-".repeat(60));
        System.out.println("--- END --- AT " + time2 + " ---");
        System.out.println("--- TOTAL TIME:  " + Utils.getTotalTime(time, time2) + " ---");
        System.out.println("-".repeat(60));
    }
}
