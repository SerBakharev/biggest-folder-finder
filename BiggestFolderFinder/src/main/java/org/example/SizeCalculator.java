package org.example;

public class SizeCalculator {

    //Todo: 24B, 234Kb, 234Mb, 234Tb
    public static String getHumanReadableSize(long size) {
        double newSize = size/1024;
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
}
