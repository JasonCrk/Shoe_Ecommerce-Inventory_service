package com.shoe_ecommerce.inventory.context.shared.infrastructure.adapters.services.storage;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobServiceClient;

import com.shoe_ecommerce.inventory.context.shared.domain.ports.services.storage.Blob;
import com.shoe_ecommerce.inventory.context.shared.domain.ports.services.storage.BlobStorageService;

import com.shoe_ecommerce.inventory.shared.domain.MediaFile;
import com.shoe_ecommerce.inventory.shared.domain.Service;
import com.shoe_ecommerce.inventory.shared.domain.UuidGenerator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@Service
public class BlobStorageServiceAdapter implements BlobStorageService {

    @Value("${spring.cloud.azure.storage.blob.shoe-variant-asset-container}")
    public String blobStorageShoeVariantAssetContainer;

    private final BlobServiceClient blobServiceClient;
    private final UuidGenerator uuidGenerator;

    public BlobStorageServiceAdapter(BlobServiceClient blobServiceClient, UuidGenerator uuidGenerator) {
        this.blobServiceClient = blobServiceClient;
        this.uuidGenerator = uuidGenerator;
    }

    @Async
    @Override
    public CompletableFuture<Blob> uploadShoeVariantAsset(MediaFile mediaFile) {
        try {
            return CompletableFuture.completedFuture(upload(mediaFile, blobStorageShoeVariantAssetContainer));
        } catch (IOException e) {
            return CompletableFuture.failedFuture(e);
        }
    }

    private Blob upload(MediaFile mediaFile, String blobStorageContainer) throws IOException {
        String[] filenameSplit = mediaFile.getOriginalFilename().split("\\.");
        String mediaFileExtension = filenameSplit[filenameSplit.length - 1];

        String blobFilename = uuidGenerator.generate() + "." + mediaFileExtension;

        BlobClient blobClient = blobServiceClient
                .getBlobContainerClient(blobStorageContainer)
                .getBlobClient(blobFilename);

        blobClient.upload(mediaFile.getInputStream(), mediaFile.getSize(), true);

        return new Blob(blobClient.getBlobUrl(), blobClient.getBlobName());
    }
}
