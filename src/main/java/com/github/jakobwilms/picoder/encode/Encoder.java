package com.github.jakobwilms.picoder.encode;

import com.github.jakobwilms.picoder.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
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

            System.out.println(Utils.timestamp() + "--- Generating seed ---");
            String seed = generateSeed(bytes);
            System.out.println(Utils.timestamp() + "--- Seed generated ---");

            System.out.println(Utils.timestamp() + "--- Starting output stream");
            FileOutputStream outputStream = new FileOutputStream(getOutput());
            outputStream.write(EncryptUtils.encrypt(seed, bytes));

            outputStream.close();
            System.out.println(Utils.timestamp() + "--- Files written ---");
            System.out.println(Utils.timestamp() + "--- Output stream closed ---");

            System.out.println(Utils.timestamp() + "--- ENCODED FILE: " + getOutput().getAbsolutePath() + " ---");
        } catch (Throwable ignored) {
        }
    }

    private String generateSeed(byte[] bytes) {
        try {
            String key = addKey();
            String checksum = generateChecksum(bytes);

            return add(key, checksum);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("  33 ");
        return "";
    }

    private String add(String a, String b) {
        StringBuilder builder = new StringBuilder();

        for (int i = Math.max(a.length(), b.length()); i > 0; i--) {
            int a1 = i < a.length() ? fromChar(a.charAt(i)) : 0;
            int b1 = i < b.length() ? fromChar(b.charAt(i)) : 0;

            builder.insert(0, String.valueOf(a1 + b1).charAt(String.valueOf(a1 + b1).length() - 1));
        }

        return builder.toString();
    }

    private String generateChecksum(byte[] bytes) {
        List<Long> longs = new ArrayList<>();
        long l = 0L;

        for (byte b : bytes) {
            if (l < Long.MAX_VALUE - 200) {
                l += b;
                continue;
            }
            longs.add(l);
            l = 0L;
        }

        StringBuilder builder = new StringBuilder();

        for (long a : longs) {
            builder.append(a);
        }

        return builder.toString();
    }

    private String addKey() {
        StringBuilder builder = new StringBuilder();

        System.out.println("length: " + otherKey.length());
        for (int i = getKey().length() - 1; i >= 0; i--) {
            int a = fromChar(getKey().charAt(i));
            int b = fromChar(getOtherKey().charAt(i));

            builder.insert(0, String.valueOf(a + b).charAt(String.valueOf(a + b).length() - 1));
        }

        return builder.toString();
    }

    private int fromChar(char c) {
        return Utils.fromChar(c);
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
