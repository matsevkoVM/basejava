package com.urise.webapp;

import java.io.File;

public class MainFile {
    public static void main(String[] args) {

        File file = new File("C:\\Users\\matse\\JavaTest\\basejava");
        fallThrough(file);
    }

    public static void fallThrough(File file) {
        File[] list = file.listFiles();
        if (list != null) {
            for (File sample : list) {
                if (sample.isDirectory()) {
                    System.out.println("DIRECTORY " + sample.getAbsolutePath() + ". It includes ->");
                    File[] catalog = sample.listFiles();
                    if (catalog != null) {
                        for (File f : catalog) {
                            if (f.isFile()) {
                                System.out.println("     File \"" + f.getName() + "\"");
                            } else {
                                System.out.println("  Directory \"" + f.getName() + "\"");
                            }
                        }
                    }
                    fallThrough(sample);
                }
            }
        }
    }
}