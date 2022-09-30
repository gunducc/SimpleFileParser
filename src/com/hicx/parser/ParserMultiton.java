package com.hicx.parser;

import java.util.HashMap;
import java.util.Map;

public class ParserMultiton {

    private static final Map<String, IParser> instances = new HashMap<String, IParser>();

    private ParserMultiton() {
    }

    public static synchronized IParser getParser(String fileType) {

        IParser instance = instances.get(fileType);

        if (instance == null) {
            if (fileType.equalsIgnoreCase("TXT"))
            instance = new TxtParser();
            else if (fileType.equalsIgnoreCase("PDF"))
            instance = new PdfParser();
            else if (fileType.equalsIgnoreCase("DOCX"))
            instance = new DocxParser();
            else
            instance = null;

            instances.put(fileType, instance);
        }

        return instance;
    }

}
