package com.feedo.persistence_postgres.entities;

import com.obernalpo.ebook.model.Ebook;
import com.obernalpo.ebook.model.EpubEbook;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "FILES")
public class FileDBEntity {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id")
    private String id;

    @OneToOne(mappedBy = "file")
    private EbookEntity ebook;

    private String fileName;
    private String type;
    @Lob
    private byte[] data;

    public FileDBEntity() {
    }

    public FileDBEntity(String identifier, String fileName, String type, byte[] data) {
        this.id = identifier;
        this.fileName = fileName;
        this.type = type;
        this.data = data;
    }

}
