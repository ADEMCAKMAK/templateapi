package com.springboot.template.core.repository;

import com.springboot.template.core.repository.base.IBaseRepository;
import com.springboot.template.storage.StoredFile;

import java.util.UUID;

public interface StoredFileRepository extends IBaseRepository<StoredFile, UUID> {
}
