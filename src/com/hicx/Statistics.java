package com.hicx;

public class Statistics {

    private String content;
    private String fileName;

    public Statistics(String fileName, String content){
        this.fileName = fileName;
        this.content = content;
    }

    public String getFileName() {
        return fileName;
    }

    public int getNumberOfWords(){
        return this.content.split(" ").length;
    }

    public int getNumberOfSpecialChars(){
        if (content == null || content.trim().isEmpty()) {
            return 0;
        }
        int countSpecial = 0;

        for (int i = 0; i < content.length(); i++) {
            if (content.substring(i, i+1).matches("[^A-Za-z0-9]")) {
                countSpecial++;
            }

        }
        return countSpecial;
    }


}
