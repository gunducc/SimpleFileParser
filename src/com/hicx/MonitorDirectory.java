package com.hicx;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


public class MonitorDirectory implements Runnable{

    private String path;
    private Processor processor;

    public MonitorDirectory (String path, Processor processor){
        this.path = path;
        this.processor = processor;
    }

    public static String getFileExtension(String fileName) {
        int lastIndexOf = fileName.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return "";
        }
        return fileName.substring(lastIndexOf+1);
    }

    public boolean isValidFileType(String value) {
        return Arrays.stream(FileTypes.class.getEnumConstants()).anyMatch(e -> e.name().equalsIgnoreCase(getFileExtension(value)));
    }

    public void processExistingFiles(){
            for (final File file : Paths.get(this.path).toFile().listFiles()) {
               if (isValidFileType(file.getName())){
                   processor.processFile(file);
               }
            }
    }


    public void monitor() throws IOException {
        processExistingFiles();
        Path dir = Paths.get(this.path);
        WatchService watcher = FileSystems.getDefault().newWatchService();
        WatchKey key = dir.register(watcher, StandardWatchEventKinds.ENTRY_CREATE);
        boolean stopFlag = false;
        System.out.println("Monitoring service started for "+this.path);

        while(!stopFlag){
            try{
                key = watcher.take();
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("Running");

            List<WatchEvent<?>> keys = key.pollEvents();
            for (WatchEvent<?> watchEvent : keys){
                WatchEvent.Kind<?> watchEventKind = watchEvent.kind();
                if (watchEventKind == StandardWatchEventKinds.OVERFLOW){
                    continue;
                }
                if (watchEventKind == StandardWatchEventKinds.ENTRY_CREATE && isValidFileType(watchEvent.context().toString()) ){
                    String fileName = watchEvent.context().toString();
                    System.out.println(fileName);
                    processor.processFile(dir.resolve(fileName).toFile());
                }

            }


        }


    }

    @Override
    public void run() {
        try {
            monitor();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
