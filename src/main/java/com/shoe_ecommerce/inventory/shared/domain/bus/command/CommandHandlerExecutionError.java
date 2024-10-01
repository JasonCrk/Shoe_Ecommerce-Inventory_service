package com.shoe_ecommerce.inventory.shared.domain.bus.command;

public class CommandHandlerExecutionError extends RuntimeException {
    public CommandHandlerExecutionError(Throwable cause) {
        super(cause);
    }
}
