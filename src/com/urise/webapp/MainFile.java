package com.urise.webapp;

import java.io.File;

public class MainFile {
    public static void main(String[] args) {

        String filePath = "C:\\Users\\matse\\JavaTest\\basejava";
        fallThrough(filePath);
    }

    public static void fallThrough(String path) {
        File file = new File(path);
        String[] list = file.list();
        if (list != null) {
            for (String str : list) {
                File sample = new File(path + File.separator + str);
                if (sample.isDirectory()) {
                    String[] arr = sample.list();
                    System.out.println(sample.getAbsolutePath() + " -> the CATALOG " + "\"" + sample.getName() + "\"" + " includes: ->");
                    if (arr != null) {
                        for (String s : arr) {
                            File f = new File(sample.getAbsolutePath() + File.separator + s);
                            if (f.isDirectory()) {
                                System.out.println("This is a DIRECTORY " + f.getName());
                            } else {
                                System.out.println("This is a FILE " + f.getName());
                            }
                        }
                    }
                    fallThrough(path + File.separator + str);
                }
            }
        }
    }
}
