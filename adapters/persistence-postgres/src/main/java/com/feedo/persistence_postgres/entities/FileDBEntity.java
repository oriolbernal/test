package com.feedo.persistence_postgres.entities;

import com.obernalpo.ebook.model.Ebook;
import com.obernalpo.ebook.model.EpubEbook;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "EBOOKS")
public class FileDBEntity {
    @Id
    private String identifier;
    private String fileName;
    private String type;
    @Lob
    private byte[] data;

    public FileDBEntity() {
    }

    public FileDBEntity(Ebook ebook) {
        this.identifier = ebook.getMetadata().getIdentifier();
        this.fileName = ebook.getFileName();
        this.type = ebook.getType();
        this.data = ebook.getData();
    }

    public Ebook toEbook() {
        return new EpubEbook(fileName, type, data);
    }

}
