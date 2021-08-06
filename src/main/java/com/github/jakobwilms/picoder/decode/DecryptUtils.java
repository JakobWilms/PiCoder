package com.github.jakobwilms.picoder.decode;

import com.github.jakobwilms.picoder.Utils;

public class DecryptUtils {

    public static byte[] decrypt(String seed, byte[] bytes) {
        try {
            if (seed == null || bytes == null || seed.equals("") || bytes.length == 0)
                return new byte[0];
            return _decrypt(seed, bytes);
        } catch (Throwable ignored) {
        }
        return new byte[0];
    }

    private static byte[] _decrypt(String seed, byte[] bytes) {
        try {
            System.out.println(Utils.timestamp() + "--- Decrypting data ---");
            System.out.println(Utils.timestamp() + "--- Initializing decryptor ---");
            Decryptor decryptor = new Decryptor(seed, bytes);
            Byte[] bytes1 = decryptor.decrypt();
            byte[] r = new byte[bytes1.length];
            System.out.println(Utils.timestamp() + "--- Transforming bytes ---");
            for (int i = 0; i < bytes1.length; i++) {
                r[i] = bytes1[i];
            }
            System.out.println(Utils.timestamp() + "--- Data decrypted ---");
            return r;
        } catch (Throwable ignored) {
        }
        return new byte[0];
    }
}
