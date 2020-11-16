package com.obernalpo.ebook.services;

import com.obernalpo.ebook.model.Ebook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

@Service
public interface FileStorageService {

    void store(Ebook ebook);

    Ebook getFileById(String id);

    Stream<Ebook> getAllFiles();
}
