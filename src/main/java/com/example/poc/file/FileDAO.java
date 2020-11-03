package com.example.poc.file;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FileDAO extends JpaRepository<FileBO, Integer> {
}
