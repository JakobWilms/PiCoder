package com.github.jakobwilms.picoder.encode;

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
            Encryptor encryptor = new Encryptor(seed, bytes);
            Byte[] bytes1 = encryptor.encrypt();
            byte[] r = new byte[bytes1.length];
            for (int i = 0; i < bytes1.length; i++) {
                r[i] = bytes1[i];
            }
            return r;
        } catch (Throwable ignored) {
        }
        return new byte[0];
    }
}
