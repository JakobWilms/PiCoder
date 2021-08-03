package com.github.jakobwilms.picoder.initialize;

import java.io.File;

public class Initializer {

    public Initializer() {
        File file = new File(File.pathSeparator + "usr" + File.pathSeparator + "bin", "picoder");
        delete(file);
    }

    private void delete(File dir) {
        File[] contents = dir.listFiles();
        if (contents != null) {
            for (File file : contents) {
                delete(file);
            }
        }
        //noinspection ResultOfMethodCallIgnored
        dir.delete();
    }
}
