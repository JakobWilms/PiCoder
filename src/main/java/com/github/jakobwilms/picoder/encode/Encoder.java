package com.github.jakobwilms.picoder.encode;

import java.io.File;

public class Encoder {

    private final File input;
    private final File output;

    public Encoder(String inputName, String outputName, String keyName, String otherKey) {
        this.input = new File(inputName);
        /*
        if (!input.exists()) {
            System.out.println("Input file does not exist!");
            System.exit(1);
        }
         */
        this.output = new File(outputName);
    }

    public File getInput() {
        return input;
    }

    public File getOutput() {
        return output;
    }
}
