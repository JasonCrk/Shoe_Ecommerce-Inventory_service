package com.shoe_ecommerce.inventory.shared.presentation.validations.asset_content_type;

import lombok.Getter;

@Getter
public enum AllowedAssetContentType {
    PNG("image/png", "png"),
    MP4("video/mp4", "mp4");

    private final String mimeType;
    private final String extension;

    AllowedAssetContentType(String mimeType, String extension) {
        this.mimeType = mimeType;
        this.extension = extension;
    }
}
