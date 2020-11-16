package com.obernalpo.ebook.model;

import com.feedo.domain.exceptions.NotFoundException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class EpubEbook extends Ebook {

    private static final String METADATA_FILE = "OEBPS/content.opf";
    private static final String METADATA_FILE_PATTERN = "OEBPS/.*.opf";
    private static final Charset METADATA_ENCODING = StandardCharsets.UTF_8;

    public EpubEbook(String fileName, String type, byte[] data) throws ParserConfigurationException, SAXException, IOException {
        super(fileName, type, data);
        System.out.println("Path: " + fileName);
        Document document = readDocument(fileName, data);

        this.identifier = document.getElementsByTagName("dc:identifier").item(0).getTextContent();
        System.out.println("identifier: " + identifier);
        this.title = document.getElementsByTagName("dc:title").item(0).getTextContent();
        this.creator = document.getElementsByTagName("dc:creator").item(0).getTextContent();
        this.language = document.getElementsByTagName("dc:language").item(0).getTextContent();
        this.rights = document.getElementsByTagName("dc:rights").item(0).getTextContent();
        this.publisher = document.getElementsByTagName("dc:publisher").item(0).getTextContent();
    }

    private Document readDocument(String fileName, byte[] data) throws IOException, ParserConfigurationException, SAXException {
        System.out.println("Creating temporal file: " + fileName + ".tmp");
        String filePath = fileName + ".tmp";
        File tmpFile = createFile(filePath, data);
        Document document = readDocument(tmpFile);
        Files.delete(tmpFile.toPath());
        System.out.println("Deleting temporal file");
        return document;
    }

    private File createFile(String filePath, byte[] bytes) throws IOException {
        InputStream inputStream = new ByteArrayInputStream(bytes);
        File file = new File(filePath);
        Files.copy(inputStream, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
        return file;
    }

    private Document readDocument(File file) throws IOException, ParserConfigurationException, SAXException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        try (
                ZipFile zipFile = new ZipFile(file);
                InputStream inputStream = zipFile.getInputStream(zipFile.getEntry(METADATA_FILE))
        ) {
            return documentBuilder.parse(inputStream);
        }
    }

    public void saveInput(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[inputStream.available()];
        inputStream.read(buffer);
        File targetFile = new File("whatiThiFocka.xml");
        OutputStream outStream = new FileOutputStream(targetFile);
        outStream.write(buffer);
    }
}
