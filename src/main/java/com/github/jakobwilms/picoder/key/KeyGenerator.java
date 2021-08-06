package com.github.jakobwilms.picoder.key;

import com.github.jakobwilms.picoder.Pi;
import com.github.jakobwilms.picoder.Utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class KeyGenerator {

    private final String keyName;
    private final File file;

    public KeyGenerator(String keyName) {
        this.keyName = keyName;
        File file = new File("/home/" + System.getProperty("user.name") + "/.picoder/key/" + getKeyName());
        if (!file.getParentFile().exists())
            //noinspection ResultOfMethodCallIgnored
            file.getParentFile().mkdirs();
        this.file = file;
    }

    public void generate() {
        try {
            _generate();
        } catch (Throwable ignored) {
        }
    }

    private void _generate() throws NoSuchAlgorithmException, IOException{
        String key = getKey();
        write(key);
    }

    private void write(String key) throws IOException {
        if (!getFile().exists()) {
            //noinspection ResultOfMethodCallIgnored
            getFile().createNewFile();
        }
        FileOutputStream stream = new FileOutputStream(getFile());
        byte[] bytes = key.getBytes();
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (bytes[i] + 20);
        }
        stream.write(bytes);
        stream.close();
    }

    private String getKey() throws NoSuchAlgorithmException {
        StringBuilder builder = new StringBuilder();
        SecureRandom random = SecureRandom.getInstanceStrong();
        List<Integer> exclude = new ArrayList<>();
        for (int i = 0; i < 10_000; i++) {
            int j = nextInt(random, exclude);
            exclude.add(j);
            builder.append(j);
        }
        return builder.toString();
    }

    private int nextInt(SecureRandom random, List<Integer> exclude) {
        int i = random.nextInt(Pi.DIGITS);
        while (exclude.contains(i)) {
            i = random.nextInt(Pi.DIGITS);
        }
        return fromChar(Pi.PI.charAt(i));
    }

    private int fromChar(char c) {
        return Utils.fromChar(c);
    }

    public String getKeyName() {
        return keyName;
    }

    public File getFile() {
        return file;
    }
}
