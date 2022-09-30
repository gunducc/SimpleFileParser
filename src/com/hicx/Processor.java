package com.hicx;

import com.hicx.parser.IParser;
import com.hicx.parser.ParserMultiton;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Processor{

    public synchronized void processFile(File file){
               IParser parser = ParserMultiton.getParser(MonitorDirectory.getFileExtension(file.getName()));
               String content = parser.parse(file);
               Statistics statistics = new Statistics(file.getName(),content);
               Printer.print(statistics);
               moveFile(file);
   }

   public void moveFile(File file){
       try {
           Path path = Paths.get(file.getParent().toString()+"\\processed\\");
           if (Files.notExists(path)) {
               System.out.println(path);
               try { Files.createDirectory(path); }
               catch (Exception e ) { e.printStackTrace(); }
           }

           Files.move(Paths.get(file.getPath()), path, StandardCopyOption.REPLACE_EXISTING);
       } catch (IOException e) {
           e.printStackTrace();
       }
   }

}
