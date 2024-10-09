package com.shoe_ecommerce.inventory.context.shoe_variant_asset.presentation.requests;

import com.shoe_ecommerce.inventory.context.shared.presentation.validations.is_uuid.IsUuid;

import com.shoe_ecommerce.inventory.shared.presentation.validations.asset_content_type.AllowedAssetContentType;
import com.shoe_ecommerce.inventory.shared.presentation.validations.asset_content_type.AssetContentType;
import com.shoe_ecommerce.inventory.shared.presentation.validations.image_max_size.ImageMaxSize;
import com.shoe_ecommerce.inventory.shared.presentation.validations.video_max_size.VideoMaxSize;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record CreateBatchShoeVariantAssetRequest(
        @NotBlank(message = "IS required")
        @IsUuid(message = "Is invalid")
        String shoeVariantId,

        @NotEmpty(message = "Is required")
        @NotNull(message = "Is required")
        @VideoMaxSize(maxMegabytes = 10, message = "The video allows a maximum of 10 MB")
        @ImageMaxSize(maxMegabytes = 5, message = "The image allows a maximum of 5 MB")
        @AssetContentType(
                message = "Must be PNG or MP4 assets",
                contentTypes = {AllowedAssetContentType.PNG, AllowedAssetContentType.MP4}
        )
        List<MultipartFile> assets
) {
}
