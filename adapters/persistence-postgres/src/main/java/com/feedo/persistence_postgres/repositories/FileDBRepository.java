package com.feedo.persistence_postgres.repositories;


import com.feedo.persistence_postgres.entities.FileDBEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileDBRepository extends JpaRepository<FileDBEntity, String> {
}
