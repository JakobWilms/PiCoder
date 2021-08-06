package com.github.jakobwilms.picoder.decode;

import com.github.jakobwilms.picoder.Utils;
import com.github.jakobwilms.picoder.key.KeyUtils;

import java.io.File;

public class DecodeUtils {

    public static void decode(String inputName, String outputName, String keyName, String otherKey) {
        try {
            if (inputName == null || inputName.equals("") || outputName == null || outputName.equals("") || keyName == null || keyName.equals("") || otherKey == null || otherKey.equals(""))
                return;
            _decode(inputName, outputName, keyName, otherKey);
        } catch (Throwable ignored) {
        }
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private static void _decode(String inputName, String outputName, String keyName, String otherKey) {
        try {
            System.out.println(Utils.timestamp() + "--- START DECODING ---");

            System.out.println(Utils.timestamp() + "--- Loading files ---");
            File input = new File(inputName);
            input = input.exists() ? input : new File(System.getProperty("user.dir") + inputName);
            File output = new File(outputName);
            System.out.println(Utils.timestamp() + "--- Files loaded");

            System.out.println(Utils.timestamp() + "--- Loading keys ---");
            String key = KeyUtils.load(keyName);
            String other = KeyUtils.load(otherKey);
            System.out.println(Utils.timestamp() + "--- Key loaded ---");

            if (!input.exists() || key.equals("") || other.equals("")) {
                System.out.println(Utils.timestamp() + "--- INPUT FILE NOT FOUND (try absolut paths) OR KEY EMPTY ---");
                return;
            }
            if (output.exists())
                output.delete();
            output.createNewFile();

            _decode0(input, output, key, other);

            System.out.println(Utils.timestamp() + "--- END DECODING ---");
        } catch (Throwable ignored) {
        }
    }

    private static void _decode0(File input, File output, String key, String otherKey) {
        try {
            System.out.println(Utils.timestamp() + "--- Initializing decoder");
            Decoder decoder = new Decoder(input, output, key, otherKey);
            decoder.decode();
        } catch (Throwable ignored) {
        }
    }
}
