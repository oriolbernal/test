package com.obernalpo.ebook.model;

import lombok.Getter;

@Getter
public abstract class Ebook {

    protected String fileName;
    protected String type;
    protected byte[] data;
    protected Metadata metadata;

    public void printFileMetadata() {
        System.out.println("creationTime: " + metadata.getFileAttributes().creationTime().toString());
        System.out.println("lastAccessTime: " + metadata.getFileAttributes().lastAccessTime().toString());
        System.out.println("lastModifiedTime: " + metadata.getFileAttributes().lastModifiedTime().toString());
        System.out.println("isDirectory: " + metadata.getFileAttributes().isDirectory());
        System.out.println("isOther: " + metadata.getFileAttributes().isOther());
        System.out.println("isRegularFile: " + metadata.getFileAttributes().isRegularFile());
        System.out.println("isSymbolicLink: " + metadata.getFileAttributes().isSymbolicLink());
        System.out.println("size: " + metadata.getFileAttributes().size());
        System.out.println("Title: " + metadata.getTitle());
        System.out.println("Creator: " + metadata.getCreator());
        System.out.println("Language: " + metadata.getLanguage());
        System.out.println("Rights: " + metadata.getRights());
        System.out.println("Publisher: " + metadata.getPublisher());
        System.out.println("Identifier: " + metadata.getIdentifier());
    }

}
