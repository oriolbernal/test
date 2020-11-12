package com.feedo.persistence_postgres.adapters;

import com.feedo.domain.exceptions.NotFoundException;
import com.feedo.persistence_postgres.entities.FileDBEntity;
import com.feedo.persistence_postgres.repositories.FileDBRepository;
import com.obernalpo.ebook.ports.FilePersistence;
import com.obernalpo.ebook.model.Ebook;

import java.io.IOException;
import java.util.stream.Stream;

public class FilePersistencePostgres implements FilePersistence {

    private final FileDBRepository fileDBRepository;

    public FilePersistencePostgres(FileDBRepository fileDBRepository) {
        this.fileDBRepository = fileDBRepository;
    }

    @Override
    public Ebook store(Ebook ebook) throws IOException {
        FileDBEntity fileDBEntity = new FileDBEntity(ebook);
        return fileDBRepository.save(fileDBEntity).toEbook();
    }

    @Override
    public Ebook getFileById(String id) {
        return fileDBRepository.findById(id).map(FileDBEntity::toEbook).orElseThrow(() -> new NotFoundException("No s'ha trobat el recurs: " + id));
    }

    @Override
    public Stream<Ebook> getAllFiles() {
        return fileDBRepository.findAll().stream().map(FileDBEntity::toEbook);
    }
}