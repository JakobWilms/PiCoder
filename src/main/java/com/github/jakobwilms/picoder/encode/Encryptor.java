package com.github.jakobwilms.picoder.encode;

import com.github.jakobwilms.picoder.Globals;
import com.github.jakobwilms.picoder.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

class Encryptor {

    private final String seed;
    private final byte[] bytes;

    private final int passes;

    Encryptor(String seed, byte[] bytes) {
        this.seed = seed;
        this.bytes = bytes;
        this.passes = 5;
    }

    public Byte[] encrypt() {
        try {
            return _encrypt();
        } catch (Throwable ignored) {
        }

        return new Byte[0];
    }

    private Byte[] _encrypt() {
        int[] ints = new int[getBytes().length];
        for (int i = 0; i < getPasses(); i++) {
            for (int j = 0; j < getBytes().length; j++) {
                ints[i] = encrypt(bytes[i], i);
            }
        }

        return getOut(ints);
    }

    private Byte[] getOut(int[] ints) {
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        builder.append("q");

        for (int anInt : ints) {
            builder.append(anInt).append("q");
            if (String.valueOf(anInt).length() > 10) {
                System.out.println("> 10");
            }
            for (int j = String.valueOf(anInt).length(); j < 10; j++) {
                builder.append(random.nextInt(10));
            }
            builder.append("q");
        }

        List<Byte> bytes = new ArrayList<>();
        bytes.add(Globals.VERSION_1);
        bytes.add(Globals.VERSION_2);
        bytes.add(Globals.VERSION_3);

        byte[] bytes1 = builder.toString().getBytes();

        for (byte b : bytes1) {
            bytes.add(b);
        }

        return bytes.toArray(new Byte[0]);
    }

    private int encrypt(byte b, int i) {
        i = i < seed.length() ? i : i % seed.length();
        int tmpSeed = Utils.fromChar(seed.charAt(i)) * 10 + Utils.fromChar(seed.charAt(i + 1)) + Utils.fromChar(seed.charAt(seed.length() - i - 1)) + Utils.fromChar(seed.charAt(seed.length() - i - 2)) * 10;
        int x = b ^ tmpSeed;
        return x + 1;
    }

    public String getSeed() {
        return seed;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public int getPasses() {
        return passes;
    }
}
