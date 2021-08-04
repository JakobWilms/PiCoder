package com.github.jakobwilms.picoder.key;

import com.github.jakobwilms.picoder.Pi;

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
        System.out.println(file.getAbsolutePath());
        if (!file.getParentFile().exists())
            //noinspection ResultOfMethodCallIgnored
            file.getParentFile().mkdirs();
        this.file = file;
    }

    public void generate() {
        try {
            _generate();
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
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
            bytes[i] = (byte) (bytes[i]* 2 + 1);
        }
        stream.write(bytes);
        stream.close();
    }

    private String getKey() throws NoSuchAlgorithmException {
        StringBuilder builder = new StringBuilder();
        SecureRandom random = SecureRandom.getInstanceStrong();
        List<Integer> exclude = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
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
        switch (c) {
            case '1':
                return 1;
            case '2':
                return 2;
            case '3':
                return 3;
            case '4':
                return 4;
            case '5':
                return 5;
            case '6':
                return 6;
            case '7':
                return 7;
            case '8':
                return 8;
            case '9':
                return 9;
            default:
                return 0;
        }
    }

    public String getKeyName() {
        return keyName;
    }

    public File getFile() {
        return file;
    }
}
