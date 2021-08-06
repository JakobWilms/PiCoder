package com.github.jakobwilms.picoder.encode;

import com.github.jakobwilms.picoder.Utils;

public class EncryptUtils {

    public static byte[] encrypt(String seed, byte[] bytes) {
        try {
            if (seed == null || bytes == null || seed.equals("") || bytes.length == 0)
                return new byte[0];
            return _encrypt(seed, bytes);
        } catch (Throwable ignored) {
        }
        return new byte[0];
    }

    private static byte[] _encrypt(String seed, byte[] bytes) {
        try {
            System.out.println(Utils.timestamp() + "--- Encrypting data ---");
            System.out.println(Utils.timestamp() + "--- Initializing encryptor ---");
            Encryptor encryptor = new Encryptor(seed, bytes);
            Byte[] bytes1 = encryptor.encrypt();
            byte[] r = new byte[bytes1.length];
            System.out.println(Utils.timestamp() + "--- Transforming bytes ---");
            for (int i = 0; i < bytes1.length; i++) {
                r[i] = bytes1[i];
            }
            System.out.println(Utils.timestamp() + "--- Data encrypted");
            return r;
        } catch (Throwable ignored) {
        }
        return new byte[0];
    }
}
