package com.shoe_ecommerce.inventory.shared.domain.bus.command;

public interface CommandHandler<C extends Command, R> {
    R handle(C command);
}
