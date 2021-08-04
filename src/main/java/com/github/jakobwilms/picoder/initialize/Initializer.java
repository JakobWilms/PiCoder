package com.github.jakobwilms.picoder.initialize;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;

import java.io.File;

public class Initializer {

    public Initializer() {
        File file = new File(File.pathSeparator + "usr" + File.pathSeparator + "bin", "PiCoder");
        if (file.exists())
            delete(file);
        try {
            Git git = Git.cloneRepository().setURI("https://github.com/JakobWilms/PiCoder.git").setDirectory(new File("/usr/bin/PiCoder")).call();
        } catch (GitAPIException e) {
            e.printStackTrace();
        }
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
