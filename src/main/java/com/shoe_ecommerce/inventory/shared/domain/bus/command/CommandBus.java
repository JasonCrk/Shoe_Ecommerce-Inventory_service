package com.shoe_ecommerce.inventory.shared.domain.bus.command;

public interface CommandBus {
    <R> R dispatch(Command command);
}
