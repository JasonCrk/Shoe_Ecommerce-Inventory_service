package com.shoe_ecommerce.inventory.context.shoe_variant_asset.domain.exceptions;

import com.shoe_ecommerce.inventory.context.shoe_variant_asset.domain.value_objects.ShoeVariantAssetId;

import com.shoe_ecommerce.inventory.shared.domain.exceptions.DomainError;

public class ShoeVariantAssetNotExist extends DomainError {

    public ShoeVariantAssetNotExist(ShoeVariantAssetId id) {
        super("shoe_variant_asset_not_exist", String.format("The shoe variant asset <%s> doesn't exist", id.value()));
    }
}
