package com.github.jakobwilms.picoder;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SeedUtils {

    public static String generateSeed(byte[] bytes, String key1, String key2) {
        String key = addKey(key1, key2);
        String checksum = generateChecksum(bytes);

        return add(key, checksum);
    }

    private static @NotNull String add(@NotNull String a, @NotNull String b) {
        StringBuilder builder = new StringBuilder();

        for (int i = Math.max(a.length(), b.length()); i > 0; i--) {
            int a1 = i < a.length() ? Utils.fromChar(a.charAt(i)) : 0;
            int b1 = i < b.length() ? Utils.fromChar(b.charAt(i)) : 0;

            builder.insert(0, String.valueOf(a1 + b1).charAt(String.valueOf(a1 + b1).length() - 1));
        }

        return builder.toString();
    }

    private static @NotNull String generateChecksum(byte[] bytes) {
        List<Long> longs = new ArrayList<>();
        long l = 0L;

        for (byte b : bytes) {
            if (l < Long.MAX_VALUE - 200) {
                l += b;
                continue;
            }
            longs.add(l);
            l = 0L;
        }

        StringBuilder builder = new StringBuilder();

        for (long a : longs) {
            builder.append(a);
        }

        return builder.toString();
    }

    private static @NotNull String addKey(String key1, String key2) {
        try {
            StringBuilder builder = new StringBuilder();

            for (int i = key1.length() - 1; i >= 0; i--) {
                int a = Utils.fromChar(key1.charAt(i));
                int b = Utils.fromChar(key2.charAt(i));

                builder.insert(0, String.valueOf(a + b).charAt(String.valueOf(a + b).length() - 1));
            }

            return builder.toString();
        } catch (Throwable ignored) {
            return "";
        }
    }
}
