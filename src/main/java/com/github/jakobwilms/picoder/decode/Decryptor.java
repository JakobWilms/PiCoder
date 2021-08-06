package com.github.jakobwilms.picoder.decode;

import com.github.jakobwilms.picoder.Globals;
import com.github.jakobwilms.picoder.Utils;

import java.util.ArrayList;
import java.util.List;

public class Decryptor {

    private final String seed;
    private final byte[] bytes;

    private final int passes;

    public Decryptor(String seed, byte[] bytes) {
        this.seed = seed;
        this.bytes = bytes;
        this.passes = 5;
    }

    public Byte[] decrypt() {
        try {
            return _decrypt();
        } catch (Throwable ignored) {
        }

        return new Byte[0];
    }

    private Byte[] _decrypt() {
        System.out.println(Utils.timestamp() + "--- Generating final data ---");

        if (bytes[0] != Globals.VERSION_1 || bytes[1] != Globals.VERSION_2 || bytes[2] != Globals.VERSION_3)
            return new Byte[0];
        List<Byte> bytes = new ArrayList<>();
        for (int i = 3; i < this.bytes.length; i++) {
            bytes.add(this.bytes[i]);
        }

        System.out.println(Utils.timestamp() + "--- Filtering bytes ---");
        List<Integer> ints = new ArrayList<>();
        boolean q = false;
        for (Byte aByte : bytes) {
            if (aByte == "q".getBytes()[0]) {
                q = !q;
                continue;
            }
            if (q) {
                ints.add(Utils.fromChar(new String(new byte[]{aByte}).charAt(0)));
            }
        }

        Byte[] fBytes = new Byte[ints.size()];
        System.out.println(Utils.timestamp() + "--- Decrypting bytes ---");

        for (int i = 0; i < getPasses(); i++) {
            for (int j = 0; j < ints.size(); j++) {
                fBytes[i] = decrypt(ints.get(i), i);
            }
        }

        System.out.println(Utils.timestamp() + "--- Returning decrypted bytes ---");
        return fBytes;
    }

    private Byte decrypt(int number, int i) {
        i = i < seed.length() ? i : i % seed.length();
        int tmpSeed = Utils.fromChar(seed.charAt(i)) * 10 + Utils.fromChar(seed.charAt(i + 1)) + Utils.fromChar(seed.charAt(seed.length() - i - 1)) + Utils.fromChar(seed.charAt(seed.length() - i - 2)) * 10;
        byte x = (byte) (number ^ tmpSeed);
        return (byte) (x - 1);
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
