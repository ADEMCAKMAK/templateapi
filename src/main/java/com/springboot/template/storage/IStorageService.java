package com.springboot.template.storage;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

public interface IStorageService {

    UUID store(MultipartFile file) throws IOException;

    StoredFile load(UUID uuid);

    MultipartFile get(UUID uuid);

    void delete(UUID uuid);

}
