package com.shoe_ecommerce.inventory.context.shared.presentation.exceptions;

public record DomainErrorResponse(int status, String code, String message) {
}
