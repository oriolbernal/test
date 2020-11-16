package com.feedo.rest_web;

import com.obernalpo.ebook.model.Ebook;
import com.obernalpo.ebook.model.EpubEbook;
import com.obernalpo.ebook.services.FileStorageService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("http://localhost:8081")
public class FileDBResource {

    private final FileStorageService storageService;

    public FileDBResource(FileStorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            String fileType = file.getContentType();
            byte[] data = file.getBytes();
            Ebook ebook = new EpubEbook(fileName, fileType, data);
            storageService.store(ebook);
            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            System.out.println("Could not upload the file!");
            e.printStackTrace();
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @PostMapping("/uploads")
    public ResponseEntity<ResponseMessage> uploadFiles(@RequestParam("files") List<MultipartFile> files) {
        System.out.println(files.size());
        String message = "";
        try {
            for(MultipartFile file : files) {
                if(file.getOriginalFilename() != null && !file.getOriginalFilename().equals("")) {
                    System.out.println("File to be uploaded: " + file.getOriginalFilename());
                    uploadFile(file);
                }
            }
            message = "Uploaded the files successfully!";
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            System.out.println("Could not upload the files!");
            message = "Could not upload the files!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }


    @GetMapping("/files")
    public List<Ebook> getListFiles() {
        return storageService.getAllFiles().collect(Collectors.toList());
    }

    @GetMapping("/files/{id}")
    public Ebook getFile(@PathVariable String id) {
        return storageService.getFileById(id);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> download(@PathVariable String id) {
        Ebook ebook = storageService.getFileById(id);
        ebook.printFileMetadata();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + ebook.getFileName() + "\"")
                .body(ebook.getData());
    }

}
