package com.springboot.template.core.repository;

import com.exercises.springboot.core.repository.base.IBaseRepository;
import com.exercises.springboot.storage.StoredFile;

import java.util.UUID;

public interface StoredFileRepository extends IBaseRepository<StoredFile, UUID> {
}
