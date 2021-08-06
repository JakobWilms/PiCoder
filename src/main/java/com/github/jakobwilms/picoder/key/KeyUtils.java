package com.github.jakobwilms.picoder.key;

import com.github.jakobwilms.picoder.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class KeyUtils {

    public static void generate(String keyName) {
        try {
            if (keyName == null || keyName.equals("") || keyName.equals(" "))
                return;
            _generate(keyName);
        } catch (Throwable ignored) {
        }
    }

    public static String load(String keyName) {
        try {
            if (keyName == null || keyName.equals("") || keyName.equals(" "))
                return "";
            return _load(keyName);
        } catch (Throwable ignored) {
        }
        return "";
    }

    private static String _load(String keyName) throws IOException {
        System.out.println(Utils.timestamp() + "--- Reading key ---");

        File file = new File("/home/" + System.getProperty("user.name") + "/.picoder/key/" + keyName);
        if (!file.exists()) file = new File(keyName);

        FileInputStream stream = new FileInputStream(file);
        byte[] bytes = stream.readAllBytes();
        stream.close();

        System.out.println(Utils.timestamp() + "--- Decoding key ---");
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (bytes[i] - 20);
        }

        return new String(bytes);
    }

    private static void _generate(String keyName) {
        try {
            System.out.println(Utils.timestamp() + "--- GENERATING KEY ---");
            KeyGenerator keyGenerator = new KeyGenerator(keyName);
            keyGenerator.generate();
            System.out.println(Utils.timestamp() + "--- KEY GENERATED");
        } catch (Throwable ignored) {
        }
    }
}
