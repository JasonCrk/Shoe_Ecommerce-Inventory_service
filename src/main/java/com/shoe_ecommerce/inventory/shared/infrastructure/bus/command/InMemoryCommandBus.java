package com.shoe_ecommerce.inventory.shared.infrastructure.bus.command;

import com.shoe_ecommerce.inventory.shared.domain.Service;
import com.shoe_ecommerce.inventory.shared.domain.bus.command.Command;
import com.shoe_ecommerce.inventory.shared.domain.bus.command.CommandBus;
import com.shoe_ecommerce.inventory.shared.domain.bus.command.CommandHandler;
import com.shoe_ecommerce.inventory.shared.domain.bus.command.CommandHandlerExecutionError;

import org.springframework.context.ApplicationContext;

@Service
public final class InMemoryCommandBus implements CommandBus {

    private final CommandHandlersInformation information;
    private final ApplicationContext context;

    public InMemoryCommandBus(CommandHandlersInformation information, ApplicationContext context) {
        this.information = information;
        this.context = context;
    }

    @Override
    public <R> R dispatch(Command command) {
        try {
            Class<? extends CommandHandler> commandHandlerClass = information.search(command.getClass());
            CommandHandler handler = context.getBean(commandHandlerClass);
            return (R) handler.handle(command);
        } catch (Throwable error) {
            throw new CommandHandlerExecutionError(error);
        }
    }
}
