package com.obernalpo.ebook.model;

public class EpubEbook extends Ebook {

    public EpubEbook(String fileName, String type, byte[] data) {
        this.fileName = fileName;
        this.type = type;
        this.data = data;
        try {
            this.metadata = new EpubMetadata(fileName);
        } catch (Exception e) {
            this.metadata = new Metadata();
            e.printStackTrace();
        }
    }

}
