package com.shoe_ecommerce.inventory.shared.infrastructure.bus.command;

import com.shoe_ecommerce.inventory.shared.domain.Service;
import com.shoe_ecommerce.inventory.shared.domain.bus.command.Command;
import com.shoe_ecommerce.inventory.shared.domain.bus.command.CommandHandler;
import com.shoe_ecommerce.inventory.shared.domain.bus.command.CommandNotRegisteredError;

import java.io.File;
import java.lang.reflect.ParameterizedType;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Service
public final class CommandHandlersInformation {

    HashMap<Class<? extends Command>, Class<? extends CommandHandler>> indexedCommandHandlers;

    public CommandHandlersInformation() throws URISyntaxException, ClassNotFoundException {
        indexedCommandHandlers = formatHandlers(findHandlers("com.shoe_ecommerce.inventory"));
    }

    public Class<? extends CommandHandler> search(Class<? extends Command> commandClass) throws CommandNotRegisteredError {
        Class<? extends CommandHandler> commandHandlerClass = indexedCommandHandlers.get(commandClass);

        if (null == commandHandlerClass) {
            throw new CommandNotRegisteredError(commandClass);
        }

        return commandHandlerClass;
    }

    private HashMap<Class<? extends Command>, Class<? extends CommandHandler>> formatHandlers(
            Set<Class<? extends CommandHandler>> commandHandlers
    ) {
        HashMap<Class<? extends Command>, Class<? extends CommandHandler>> handlers = new HashMap<>();

        for (Class<? extends CommandHandler> handler : commandHandlers) {
            ParameterizedType paramType = (ParameterizedType) handler.getGenericInterfaces()[0];
            Class<? extends Command> commandClass = (Class<? extends Command>) paramType.getActualTypeArguments()[0];

            handlers.put(commandClass, handler);
        }

        return handlers;
    }

    private Set<Class<? extends CommandHandler>> findHandlers(String packageName) throws URISyntaxException, ClassNotFoundException {
        Set<Class<? extends CommandHandler>> handlers = new HashSet<>();

        String path = packageName.replace('.', '/');
        File directory = new File(getClass().getClassLoader().getResource(path).toURI());

        if (directory.exists()) {
            for (File file : Objects.requireNonNull(directory.listFiles())) {
                String className = file.getName();
                if (className.endsWith(".class")) {
                    String fullClassName = packageName + '.' + className.substring(0, className.length() - 6);
                    Class<?> clazz = Class.forName(fullClassName);
                    if (CommandHandler.class.isAssignableFrom(clazz)) {
                        handlers.add((Class<? extends CommandHandler>) clazz);
                    }
                }
            }
        }

        return handlers;
    }
}
