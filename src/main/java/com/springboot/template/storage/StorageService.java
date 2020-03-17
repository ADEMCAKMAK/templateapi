package com.springboot.template.storage;

import com.springboot.template.core.repository.StoredFileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class StorageService implements IStorageService {

    private StoredFileRepository repository;

    public StorageService() {
    }

    public StorageService(StoredFileRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public UUID store(MultipartFile file) throws IOException {

        StoredFile storedFile = new StoredFile();
        storedFile.setName(file.getName());
        storedFile.setOriginalFileName(file.getOriginalFilename());
        storedFile.setFileType(file.getContentType());
        storedFile.setContent(file.getBytes());
        // TODO encrypt the content
        return this.repository.save(storedFile).getId();
    }

    @Override
    public StoredFile load(UUID uuid) {
        // TODO decrypt the content
        return this.repository.findById(uuid)
                .orElseThrow(() -> new FileNotFoundException("File Not Found!"));
    }

    @Override
    public MultipartFile get(UUID uuid) {
        return this.load(uuid).toMultipartFile();
    }

    @Override
    @Transactional
    public void delete(UUID uuid) {
        this.repository.deleteById(uuid);
    }

}
