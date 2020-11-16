package com.obernalpo.ebook.services.services_impl;

import com.obernalpo.ebook.model.Ebook;
import com.obernalpo.ebook.ports.FilePersistence;
import com.obernalpo.ebook.services.FileStorageService;

import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

public class FileStorageServiceImpl implements FileStorageService {

    private final FilePersistence filePersistence;

    public FileStorageServiceImpl(FilePersistence filePersistence) {
        this.filePersistence = filePersistence;
    }

    @Override
    public void store(Ebook ebook) {
        filePersistence.store(ebook);
    }

    @Override
    public Ebook getFileById(String id) {
        return filePersistence.getFileById(id);
    }

    @Override
    public Stream<Ebook> getAllFiles() {
        return filePersistence.getAllFiles();
    }
}