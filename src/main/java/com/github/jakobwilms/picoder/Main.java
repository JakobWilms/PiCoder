package com.github.jakobwilms.picoder;

import com.github.jakobwilms.picoder.encode.Encoder;
import com.github.jakobwilms.picoder.key.KeyGenerator;

public class Main {

    public static void main(String[] args) {
        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            System.out.println("Windows not supported yet!");
            System.exit(0);
        }
        if (args.length == 5) {
            if (args[0].equals("encode")) {
                Encoder encoder = new Encoder(args[1], args[2], args[3], args[4]);
            }
        } else if (args.length == 2) {
            if (args[0].equals("generate-key")) {
                KeyGenerator keyGenerator = new KeyGenerator(args[1]);
                keyGenerator.generate();
            }
        }
    }
}
