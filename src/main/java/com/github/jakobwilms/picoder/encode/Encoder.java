package com.github.jakobwilms.picoder.encode;

import com.github.jakobwilms.picoder.SeedUtils;
import com.github.jakobwilms.picoder.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Encoder {

    private final File input;
    private final File output;
    private final String key;
    private final String otherKey;

    public Encoder(File input, File output, String key, String otherKey) {
        this.input = input;
        this.output = output;
        this.key = key;
        this.otherKey = otherKey;
    }

    public void encode() {
        try {
            _encode();
        } catch (Throwable ignored) {
        }
    }

    private void _encode() {
        try {
            System.out.println(Utils.timestamp() + "--- Reading files ---");

            FileInputStream stream = new FileInputStream(getInput());
            byte[] bytes = stream.readAllBytes();
            stream.close();

            System.out.println("In: " + new String(bytes));
            System.out.println("In: " + Arrays.toString(bytes));

            System.out.println(Utils.timestamp() + "--- Generating seed ---");
            String seed = SeedUtils.generateSeed(bytes, getKey(), getOtherKey());
            System.out.println("Seed: " + seed);
            System.out.println(Utils.timestamp() + "--- Seed generated ---");

            System.out.println(Utils.timestamp() + "--- Starting output stream ---");
            FileOutputStream outputStream = new FileOutputStream(getOutput());
            outputStream.write(EncryptUtils.encrypt(seed, bytes));

            outputStream.close();
            System.out.println(Utils.timestamp() + "--- Files written ---");
            System.out.println(Utils.timestamp() + "--- Output stream closed ---");

            System.out.println(Utils.timestamp() + "--- ENCODED FILE: " + getOutput().getAbsolutePath() + " ---");
        } catch (Throwable ignored) {
        }
    }

    public File getInput() {
        return input;
    }

    public File getOutput() {
        return output;
    }

    public String getKey() {
        return key;
    }

    public String getOtherKey() {
        return otherKey;
    }
}
