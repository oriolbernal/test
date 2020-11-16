package com.obernalpo.ebook.model;

import com.feedo.domain.exceptions.NotFoundException;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.print.Doc;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.*;


public class MetadataTest {

    @Test
    void readDocument() throws IOException, ParserConfigurationException, SAXException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document2 = documentBuilder.parse(new File("C:\\Users\\obernalp\\Projectes\\IdeaProjects\\test\\whatiThiFocka.tmp"));
        System.out.println(document2.getDoctype());

        String path = "C:\\Users\\obernalp\\Projectes\\IdeaProjects\\test\\domain\\src\\test\\resources\\test.epub";
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        ZipEntry zipEntry = searchMetadataInZip(bytes);
    }

    @Test
    void main() throws ParserConfigurationException, SAXException, IOException {
        File file = new File("C:\\Users\\obernalp\\Projectes\\IdeaProjects\\test\\makinggames.epub");
        Document document = getDoc(file);
        System.out.println(document.getElementsByTagName("dc:identifier").item(0).getTextContent());
    }

    private Document getDoc(File file) throws IOException, ParserConfigurationException, SAXException {
        try (
                ZipFile zipFile = new ZipFile(file);
                InputStream inputStream = zipFile.getInputStream(zipFile.getEntry("OEBPS/content.opf"))
        ) {
            // create a new DocumentBuilderFactory
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            // use the factory to create a documentbuilder
            DocumentBuilder builder = factory.newDocumentBuilder();
            // create a new document from file
            Document document = builder.parse(inputStream);
            return document;
        }
    }

    private ZipEntry searchMetadataInZip(byte[] bytes) throws IOException {
        Pattern pattern = Pattern.compile("OEBPS/.*.opf", Pattern.CASE_INSENSITIVE);
        try (ZipInputStream zipInputStream = new ZipInputStream(new ByteArrayInputStream(bytes))) {
            ZipEntry zipEntry;
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                Matcher matcher = pattern.matcher(zipEntry.getName());
                if (matcher.find()) {
                    System.out.println("Eureka: " + zipEntry.getName());
                    return zipEntry;
                }
            }
        }
        throw new NotFoundException("No s'ha trobat el fitxer al zip");
    }

    Document readDocument(ZipEntry zipEntry) throws IOException, ParserConfigurationException, SAXException {
        ZipFile z = new ZipFile("name");
        InputStream inputStream = z.getInputStream(zipEntry);
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        return documentBuilder.parse(inputStream);
    }

    public ZipOutputStream getInputStream(ZipEntry zipEntry, byte[] bytes) throws IOException {
        InputStream fin = new ZipInputStream(new ByteArrayInputStream(bytes));
        try (ZipOutputStream zout = new ZipOutputStream(new ByteArrayOutputStream())) {
            zout.putNextEntry(zipEntry);
            int length;
            byte[] buffer = new byte[1024];
            while ((length = fin.read(buffer)) > 0) {
                zout.write(buffer, 0, length);
            }
            return zout;
        }
    }

    private static void extractFile(final ZipEntry entry, ZipInputStream is) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(entry.getName())) {
            final byte[] buf = new byte[1024];
            int length;
            while ((length = is.read(buf, 0, buf.length)) >= 0) {
                fos.write(buf, 0, length);
            }
        }
    }

}
