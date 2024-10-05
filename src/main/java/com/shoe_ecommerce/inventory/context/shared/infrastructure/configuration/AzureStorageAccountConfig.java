package com.shoe_ecommerce.inventory.context.shared.infrastructure.configuration;

import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AzureStorageAccountConfig {

    @Value("${azure.blob-storage.connection}")
    public String azureBlobStorageConnection;

    @Bean
    public BlobServiceClient blobServiceClient() {
        return new BlobServiceClientBuilder()
                .connectionString(azureBlobStorageConnection)
                .buildClient();
    }
}
