package com.github.jakobwilms.picoder.key;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
        File file = new File("/home/" + System.getProperty("user.name") + "/.picoder/key/" + keyName);
        if (!file.exists()) file = new File(keyName);

        FileInputStream stream = new FileInputStream(file);
        byte[] bytes = stream.readAllBytes();
        stream.close();

        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append((byte) (b - 20));
        }

        System.out.println(builder);
        return builder.toString();
    }

    private static void _generate(String keyName) {
        try {
            KeyGenerator keyGenerator = new KeyGenerator(keyName);
            keyGenerator.generate();
        } catch (Throwable ignored) {
        }
    }
}