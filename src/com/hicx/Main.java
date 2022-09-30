package com.hicx;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        String path = args[0];
        if (path.isEmpty()||path==null){
            System.out.println("Please provide a directory");
            return;
        }
        try {
            File file = new File(path);
            if (!file.exists()){
                System.out.println("Directory does not exists");
                return;
            }
        } catch(Exception e) {
            System.out.println("Directory does not exists");
            return;
        }
        Processor processor = new Processor();
        MonitorDirectory monitorService = new MonitorDirectory(path,processor);
        Thread monitoringThread = new Thread(monitorService);
        monitoringThread.start();
    }
}
