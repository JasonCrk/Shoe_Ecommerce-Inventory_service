package com.shoe_ecommerce.inventory.shared.infrastructure;

import com.shoe_ecommerce.inventory.shared.domain.MediaFile;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class MediaFileAdapter implements MediaFile {
    private final MultipartFile multipartFile;

    public MediaFileAdapter(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

    @Override
    public String getName() {
        return this.multipartFile.getName();
    }

    @Override
    public String getOriginalFilename() {
        return this.multipartFile.getOriginalFilename();
    }

    @Override
    public String getContentType() {
        return this.multipartFile.getContentType();
    }

    @Override
    public long getSize() {
        return this.multipartFile.getSize();
    }

    @Override
    public byte[] getBytes() throws IOException {
        return this.multipartFile.getBytes();
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return this.multipartFile.getInputStream();
    }

    @Override
    public void transferTo(File file) throws IOException {
        this.multipartFile.transferTo(file);
    }
}
