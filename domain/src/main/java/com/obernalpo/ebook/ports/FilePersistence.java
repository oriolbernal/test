package com.obernalpo.ebook.ports;

import com.obernalpo.ebook.model.Ebook;

import java.io.IOException;
import java.util.stream.Stream;

public interface FilePersistence {

    void store(Ebook file);

    Ebook getFileById(String id);

    Stream<Ebook> getAllFiles();

}
