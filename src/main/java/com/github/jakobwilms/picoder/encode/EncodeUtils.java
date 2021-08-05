package com.github.jakobwilms.picoder.encode;

import com.github.jakobwilms.picoder.key.KeyUtils;

public class EncodeUtils {

    public static void encode(String inputName, String outputName, String keyName, String otherKey) {
        System.out.println(KeyUtils.load(keyName));
    }
}
