package com.github.jakobwilms.picoder.decode;

import com.github.jakobwilms.picoder.SeedUtils;
import com.github.jakobwilms.picoder.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Decoder {

    private final File input;
    private final File output;
    private final String key;
    private final String otherKey;

    public Decoder(File input, File output, String key, String otherKey) {
        this.input = input;
        this.output = output;
        this.key = key;
        this.otherKey = otherKey;
    }

    public void decode() {
        try {
            _decode();
        } catch (Throwable ignored) {
        }
    }

    private void _decode() {
        try {
            System.out.println(Utils.timestamp() + "--- Reading files ---");

            FileInputStream stream = new FileInputStream(getInput());
            byte[] bytes = stream.readAllBytes();
            stream.close();

            System.out.println(getKey() + "\n\n\n" + getOtherKey());

            System.out.println(Utils.timestamp() + "--- Finding seed ---");
            String seed = SeedUtils.generateSeed(bytes, getKey(), getOtherKey());
            System.out.println(Utils.timestamp() + "--- Seed found ---");
            System.out.println(seed);

            System.out.println(Utils.timestamp() + "--- Starting output stream ---");
            FileOutputStream outputStream = new FileOutputStream(getOutput());
            outputStream.write(DecryptUtils.decrypt(seed, bytes));

            outputStream.close();
            System.out.println(Utils.timestamp() + "--- Files written ---");
            System.out.println(Utils.timestamp() + "--- Output stream closed");

            System.out.println(Utils.timestamp() + "--- DECODED FILE: " + getOutput().getAbsolutePath() + " ---");
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
