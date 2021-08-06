package com.github.jakobwilms.picoder.encode;

import com.github.jakobwilms.picoder.Utils;
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
            System.out.println(Utils.timestamp() + "--- START ENCODING ---");

            System.out.println(Utils.timestamp() + "--- Loading files ---");
            File input = new File(inputName);
            input = input.exists() ? input : new File(System.getProperty("user.dir") + inputName);
            File output = new File(outputName);
            System.out.println(Utils.timestamp() + "--- Files loaded ---");

            System.out.println(Utils.timestamp() + "--- Loading keys ---");
            String key = KeyUtils.load(keyName);
            String other = KeyUtils.load(otherKey);
            System.out.println(Utils.timestamp() + "--- Keys loaded ---");

            if (!input.exists() || key.equals("") || other.equals("")) {
                System.out.println(Utils.timestamp() + "--- INPUT FILE NOT FOUND (try absolut paths) OR KEY EMPTY ---");
                return;
            }
            if (output.exists())
                output.delete();
            output.createNewFile();

            _encode0(input, output, key, otherKey);

            System.out.println(Utils.timestamp() + "--- END ENCODING ---");
        } catch (Throwable ignored) {
        }
    }

    private static void _encode0(File input, File output, String key, String otherKey) {
        try {
            System.out.println(Utils.timestamp() + "--- Initializing encoder ---");
            Encoder encoder = new Encoder(input, output, key, otherKey);
            encoder.encode();
        } catch (Throwable ignored) {
        }
    }
}
