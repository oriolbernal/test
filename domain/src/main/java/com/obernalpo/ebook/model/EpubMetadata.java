package com.obernalpo.ebook.model;

import lombok.Getter;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.zip.ZipFile;

@Getter
public class EpubMetadata extends Metadata {

    private static final String METADATA_FILE = "OEBPS/content.opf";
    private static final Charset METADATA_ENCODING = StandardCharsets.UTF_8;

    public EpubMetadata(String filePath) throws IOException, SAXException, ParserConfigurationException {
        super(Paths.get(filePath));
        Document document = readDocument(filePath);
        this.title = document.getElementsByTagName("dc:title").item(0).getTextContent();
        this.creator = document.getElementsByTagName("dc:creator").item(0).getTextContent();
        this.language = document.getElementsByTagName("dc:language").item(0).getTextContent();
        this.rights = document.getElementsByTagName("dc:rights").item(0).getTextContent();
        this.publisher = document.getElementsByTagName("dc:publisher").item(0).getTextContent();
        this.identifier = document.getElementsByTagName("dc:identifier").item(0).getTextContent();
    }

    private Document readDocument(String filePath) throws IOException, ParserConfigurationException, SAXException {
        try (
                ZipFile zipFile = new ZipFile(filePath);
                InputStream inputStream = zipFile.getInputStream(zipFile.getEntry(METADATA_FILE))
        ) {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            return documentBuilder.parse(inputStream);
        }
    }

}
