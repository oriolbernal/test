package com.obernalpo.ebook.model;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class EpubEbook extends Ebook {

    public EpubEbook(String fileName, String type, byte[] data) {
        this.fileName = fileName;
        this.type = type;
        this.data = data;
        try {
            init("targetFile.tmp");
        } catch (Exception e) {
            this.metadata = new Metadata(null);
            e.printStackTrace();
        }
    }

    private void init(String filePath) throws ParserConfigurationException, SAXException, IOException {
        this.metadata = new EpubMetadata(filePath);

    }

}
