package com.springboot.template.storage;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Path;


public class MultipartFileImpl implements MultipartFile {

    private byte[] content;
    private String name;
    private String originFileName;
    private String contentType;

    public MultipartFileImpl(byte[] content, String name, String originFileName, String contentType) {
        this.content = content;
        this.name = name;
        this.originFileName = originFileName;
        this.contentType = contentType;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getOriginalFilename() {
        return originFileName;
    }

    @Override
    public String getContentType() {
        return contentType;
    }

    @Override
    public boolean isEmpty() {
        return content == null || content.length == 0;
    }

    @Override
    public long getSize() {
        return content == null ? 0 : content.length;
    }

    @Override
    public byte[] getBytes() {
        return content;
    }

    @Override
    public InputStream getInputStream() {
        return new ByteArrayInputStream(content);
    }

    @Override
    public void transferTo(File dest) throws IOException, IllegalStateException {
        new FileOutputStream(dest).write(content);
    }

    @Override
    public Resource getResource() {
        return null;
    }

    @Override
    public void transferTo(Path dest) throws IOException, IllegalStateException {

    }
}
