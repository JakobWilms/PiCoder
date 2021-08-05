package com.github.jakobwilms.picoder;

import com.github.jakobwilms.picoder.encode.EncodeUtils;
import com.github.jakobwilms.picoder.encode.Encoder;
import com.github.jakobwilms.picoder.key.KeyUtils;

public class Main {

    public static void main(String[] args) {
        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            System.out.println("Windows not supported yet!");
            System.exit(0);
        }
        if (args.length == 5) {
            if (args[0].equals("encode")) {
                EncodeUtils.encode(args[1], args[2], args[3], args[4]);
            }
        } else if (args.length == 2) {
            if (args[0].equals("generate-key")) {
                KeyUtils.generate(args[1]);
            }
        }
    }
}
