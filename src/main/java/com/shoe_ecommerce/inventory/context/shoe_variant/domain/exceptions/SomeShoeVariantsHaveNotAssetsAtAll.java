package com.shoe_ecommerce.inventory.context.shoe_variant.domain.exceptions;

import com.shoe_ecommerce.inventory.context.shoe_model.domain.value_objects.ShoeModelId;
import com.shoe_ecommerce.inventory.shared.domain.exceptions.DomainError;

public class SomeShoeVariantsHaveNotAssetsAtAll extends DomainError {
    public SomeShoeVariantsHaveNotAssetsAtAll(ShoeModelId id) {
        super("some_shoe_variants_have_not_assets_at_all", String.format(
                "One of the variants of the <%s> shoe model doesn't have an asset",
                id.value()
        ));
    }
}
