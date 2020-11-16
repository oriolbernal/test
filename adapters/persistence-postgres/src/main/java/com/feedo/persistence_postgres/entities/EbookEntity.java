package com.feedo.persistence_postgres.entities;

import com.obernalpo.ebook.model.Ebook;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "EBOOKS")
public class EbookEntity {
    @Id
    private String identifier;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "file_id", referencedColumnName = "id")
    private FileDBEntity file;
    private String title;
    private String creator;
    private String language;
    private String rights;
    private String publisher;

    public EbookEntity() {
    }

    public EbookEntity(Ebook ebook, FileDBEntity file) {
        this.identifier = ebook.getIdentifier();
        this.file = file;
        this.title = ebook.getTitle();
        this.creator = ebook.getCreator();
        this.language = ebook.getLanguage();
        this.rights = ebook.getRights();
        this.publisher = ebook.getPublisher();
    }

    public Ebook toEbook() {
        return Ebook.builder()
                .identifier(identifier)
                .fileName(file.getFileName())
                .type(file.getType())
                .data(file.getData())
                .title(title)
                .creator(creator)
                .language(language)
                .rights(rights)
                .publisher(publisher)
                .build();
    }

}
