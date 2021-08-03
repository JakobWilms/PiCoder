package com.github.jakobwilms.picoder;

import com.github.jakobwilms.picoder.initialize.Initializer;

public class Main {

    public static void main(String[] args) {
        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            System.out.println("Windows not supported yet!");
            System.exit(0);
        }
        if (args.length == 0) {
            Initializer initializer = new Initializer();
        }
    }
}
