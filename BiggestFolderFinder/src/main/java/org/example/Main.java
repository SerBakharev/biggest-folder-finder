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
        Node root = new Node(file);

        long start = System.currentTimeMillis();

        FolderSizeCalculator calculator = new FolderSizeCalculator(root);

        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(calculator);
        System.out.println(root.toString());
        //System.out.println(size);
        //System.out.println(getHumanReadableSize(240640));
        //System.out.println(getSizeFromHumanReadable("234Tb"));

        //System.out.println(getFolderSize(file));
        long duration = System.currentTimeMillis() - start;
        System.out.println(duration + " milliseconds");

       /* Set keys = System.getProperties().keySet();
        for(Object key : keys) {
            System.out.println(key);
        }

        */
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