package com.obernalpo.ebook.services;

import com.obernalpo.ebook.model.Ebook;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.stream.Stream;

@Service
public interface FileStorageService {

    Ebook store(Ebook file) throws IOException;

    Ebook getFileById(String id);

    Stream<Ebook> getAllFiles();
}
