package com.shoe_ecommerce.inventory.shared.domain;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public interface MediaFile {
    String getName();
    String getOriginalFilename();
    String getContentType();
    long getSize();
    byte[] getBytes() throws IOException;
    InputStream getInputStream() throws IOException;
    void transferTo(File file) throws IOException;
}
