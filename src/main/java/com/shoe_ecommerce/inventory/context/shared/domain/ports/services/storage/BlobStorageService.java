package com.shoe_ecommerce.inventory.context.shared.domain.ports.services.storage;

import com.shoe_ecommerce.inventory.shared.domain.MediaFile;

import java.util.concurrent.CompletableFuture;

public interface BlobStorageService {
    CompletableFuture<Blob> uploadShoeVariantAsset(MediaFile mediaFile);
}
