package com.obernalpo.ebook.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Ebook {

    protected String identifier;
    protected String fileName;
    protected String type;
    protected byte[] data;
    protected String title;
    protected String creator;
    protected String language;
    protected String rights;
    protected String publisher;

    public Ebook(String fileName, String type, byte[] data) {
        this.fileName = fileName;
        this.type = type;
        this.data = data;
    }

    public void printFileMetadata() {
        System.out.println("Title: " + title);
        System.out.println("Creator: " + creator);
        System.out.println("Language: " + language);
        System.out.println("Rights: " + rights);
        System.out.println("Publisher: " + publisher);
        System.out.println("Identifier: " + identifier);
    }

}
