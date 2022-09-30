package com.hicx.parser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class TxtParser implements IParser{
    @Override
    public String parse(File file) {
        try {
            return Files.readString(file.toPath()) ;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
