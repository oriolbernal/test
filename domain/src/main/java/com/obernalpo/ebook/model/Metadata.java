package com.obernalpo.ebook.model;

import lombok.Getter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;


@Getter
public class Metadata {

    protected BasicFileAttributes fileAttributes;
    protected String title;
    protected String creator;
    protected String language;
    protected String rights;
    protected String publisher;
    protected String identifier;

    public Metadata(Path path) {
        try {
            init(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void init(Path path) throws IOException {
        this.fileAttributes = Files.readAttributes(path, BasicFileAttributes.class);
    }
}