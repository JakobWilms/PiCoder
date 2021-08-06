package com.github.jakobwilms.picoder.encode;

import com.github.jakobwilms.picoder.key.KeyUtils;

import java.io.File;

public class EncodeUtils {

    public static void encode(String inputName, String outputName, String keyName, String otherKey) {
        try {
            if (inputName == null || inputName.equals("") || outputName == null || outputName.equals("") || keyName == null || keyName.equals("") || otherKey == null || otherKey.equals(""))
                return;
            _encode(inputName, outputName, keyName, otherKey);
        } catch (Throwable ignored) {
        }
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private static void _encode(String inputName, String outputName, String keyName, String otherKey) {
        try {
            File input = new File(inputName);
            File output = new File(outputName);
            String key = KeyUtils.load(keyName);
            String other = KeyUtils.load(otherKey);

            if (!input.exists() || key.equals("") || other.equals(""))
                return;
            if (output.exists())
                output.delete();
            output.createNewFile();

            _encode0(input, output, key, otherKey);
        } catch (Throwable ignored) {
        }
    }

    private static void _encode0(File input, File output, String key, String otherKey) {
        try {
            Encoder encoder = new Encoder(input, output, key, otherKey);
            encoder.encode();
        } catch (Throwable ignored) {
        }
    }
}
