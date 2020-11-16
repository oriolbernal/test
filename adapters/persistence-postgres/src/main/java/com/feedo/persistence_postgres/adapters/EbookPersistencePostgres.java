package com.feedo.persistence_postgres.adapters;

import com.feedo.domain.exceptions.NotFoundException;
import com.feedo.persistence_postgres.entities.EbookEntity;
import com.feedo.persistence_postgres.entities.FileDBEntity;
import com.feedo.persistence_postgres.repositories.EbookRepository;
import com.feedo.persistence_postgres.repositories.FileDBRepository;
import com.obernalpo.ebook.model.Ebook;
import com.obernalpo.ebook.ports.FilePersistence;

import java.util.stream.Stream;

public class EbookPersistencePostgres implements FilePersistence {

    private final EbookRepository ebookRepository;
    private final FileDBRepository fileDBRepository;

    public EbookPersistencePostgres(EbookRepository ebookRepository, FileDBRepository fileDBRepository) {
        this.ebookRepository = ebookRepository;
        this.fileDBRepository = fileDBRepository;
    }

    @Override
    public void store(Ebook ebook) {
        FileDBEntity file = new FileDBEntity(
                ebook.getIdentifier(),
                ebook.getFileName(),
                ebook.getType(),
                ebook.getData()
        );
        fileDBRepository.save(file);
        EbookEntity ebookEntity = new EbookEntity(ebook, file);
        ebookRepository.save(ebookEntity);
    }

    @Override
    public Ebook getFileById(String id) {
        return ebookRepository.findById(id).map(EbookEntity::toEbook).orElseThrow(() -> new NotFoundException("No s'ha trobat el recurs: " + id));
    }

    @Override
    public Stream<Ebook> getAllFiles() {
        return ebookRepository.findAll().stream().map(EbookEntity::toEbook);
    }
}