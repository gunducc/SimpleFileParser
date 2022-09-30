package com.hicx;

public class Printer {

    public static void print(Statistics statistics){
        System.out.println("-----------------------");
        System.out.println("Filename:"+statistics.getFileName());
        System.out.println("Number of words:"+statistics.getNumberOfWords());
        System.out.println("Number of special characters:"+statistics.getNumberOfSpecialChars());
        System.out.println("-----------------------");
    }
}
