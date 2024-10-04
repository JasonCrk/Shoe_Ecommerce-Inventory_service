package com.shoe_ecommerce.inventory.context.shoe_model.application.commands.create;

import com.shoe_ecommerce.inventory.context.shoe_variant.domain.value_objects.ShoeVariantId;
import com.shoe_ecommerce.inventory.context.shoe_variant.domain.value_objects.ShoeVariantName;
import com.shoe_ecommerce.inventory.context.shoe_variant.domain.value_objects.ShoeVariantPrice;
import com.shoe_ecommerce.inventory.shared.domain.bus.command.Command;

import java.math.BigDecimal;
import java.util.List;

public record CreateShoeModelCommand(
        String id,
        String name,
        String description,
        String categoryId,
        String assignedBrandId,
        List<ShoeVariant> variants
) implements Command {

    public record ShoeVariant(ShoeVariantId id, ShoeVariantName name, ShoeVariantPrice price) {

        public static ShoeVariant instance(String id, String name, BigDecimal price) {
            return new ShoeVariant(new ShoeVariantId(id), new ShoeVariantName(name), new ShoeVariantPrice(price));
        }
    }
}
