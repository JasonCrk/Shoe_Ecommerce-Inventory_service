package com.shoe_ecommerce.inventory.context.shoe_variant_asset.domain.exceptions;

import com.shoe_ecommerce.inventory.context.shoe_variant.domain.value_objects.ShoeVariantId;

import com.shoe_ecommerce.inventory.shared.domain.exceptions.DomainError;

public class RemoveOnlyAssetOfShoeVariantAfterPublication extends DomainError {

    public RemoveOnlyAssetOfShoeVariantAfterPublication(ShoeVariantId shoeVariantId) {
        super(
                "remove_only_asset_of_shoe_variant_after_publication",
                String.format(
                        "Cannot delete the only asset of the shoe variant <%s> after publishing the shoe model",
                        shoeVariantId.value()
                )
        );
    }
}
