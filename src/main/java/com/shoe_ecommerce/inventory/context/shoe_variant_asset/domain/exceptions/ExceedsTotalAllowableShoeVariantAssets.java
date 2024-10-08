package com.shoe_ecommerce.inventory.context.shoe_variant_asset.domain.exceptions;

import com.shoe_ecommerce.inventory.context.shoe_variant.domain.value_objects.ShoeVariantId;
import com.shoe_ecommerce.inventory.context.shoe_variant_asset.domain.value_objects.ShoeVariantAssetPosition;

import com.shoe_ecommerce.inventory.shared.domain.exceptions.DomainError;

public class ExceedsTotalAllowableShoeVariantAssets extends DomainError {

    public ExceedsTotalAllowableShoeVariantAssets(ShoeVariantId id) {
        super(
                "exceeds_total_allowable_shoe_variant_assets",
                String.format(
                        "You cannot have more than %d assets for the shoe variant <%s>",
                        ShoeVariantAssetPosition.TOTAL_MAXIMUM,
                        id.value()
                )
        );
    }
}
