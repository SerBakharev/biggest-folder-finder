package org.example;

import java.io.File;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {

        /*MyThread thread = new MyThread(1);
        MyThread thread2 = new MyThread(2);

        thread.start();
        thread2.start();

         */

        String folderPath = "C:/Users/Admin/Desktop/джава";
        File file = new File(folderPath);
        long start = System.currentTimeMillis();

        FolderSizeCalculator calculator = new FolderSizeCalculator(file);

        ForkJoinPool pool = new ForkJoinPool();
        long size = pool.invoke(calculator);
        System.out.println(size);
        System.out.println(getHumanReadableSize(size));
        System.out.println(getSizeFromHumanReadable("234"));

        //System.out.println(getFolderSize(file));
        long duration = System.currentTimeMillis() - start;
        System.out.println(duration + " milliseconds");

       /* Set keys = System.getProperties().keySet();
        for(Object key : keys) {
            System.out.println(key);
        }

        */
    }

    //Todo: 24B, 234Kb, 234Mb, 234Tb
    public static String getHumanReadableSize(long size) {
        long newSize = size/1024;
        String readable;
        if(newSize < 1) {
            readable = String.valueOf(size).concat("B");
        } else if (newSize < 1024) {
            readable = String.valueOf(newSize).concat("Kb");
        } else if (newSize < (1024*1024)) {
            readable = String.valueOf(newSize/1024).concat("Mb");
        } else {
            readable = String.valueOf(newSize/(1024*1024)).concat("Tb");
        }
        return readable;
    }

    //Todo: 24B, 234Kb, 234Mb, 234Tb
    //Todo: 24B, 234K, 234M, 234T
    public static long getSizeFromHumanReadable(String folderString) {
        long size = Long.valueOf(folderString.replaceAll("[^0-9]", ""));
        if(folderString.contains("B")) {
            return size;
        } else if (folderString.contains("K")) {
            return size*1024;
        } else if (folderString.contains("M")) {
            return size*((long) Math.pow(1024, 2));
        } else if (folderString.contains("T")) {
            return size*((long) Math.pow(1024, 3));
        } else {
            return size;
        }
    }

    public static long getFolderSize(File folder) {
        if (folder.isFile()) {
            return folder.length();
        }
        long sum = 0;
        File[] files = folder.listFiles();
        for(File file : files) {
            sum += getFolderSize(file);
        }
        return sum;
    }
}