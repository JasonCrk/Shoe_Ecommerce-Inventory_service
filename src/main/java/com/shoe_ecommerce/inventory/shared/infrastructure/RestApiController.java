package com.shoe_ecommerce.inventory.shared.infrastructure;

import com.shoe_ecommerce.inventory.shared.domain.bus.command.Command;
import com.shoe_ecommerce.inventory.shared.domain.bus.command.CommandBus;
import com.shoe_ecommerce.inventory.shared.domain.bus.command.CommandHandlerExecutionError;
import com.shoe_ecommerce.inventory.shared.domain.bus.query.Query;
import com.shoe_ecommerce.inventory.shared.domain.bus.query.QueryBus;
import com.shoe_ecommerce.inventory.shared.domain.bus.query.QueryHandlerExecutionError;
import com.shoe_ecommerce.inventory.shared.domain.bus.query.Response;

public abstract class RestApiController {
    private final CommandBus commandBus;
    private final QueryBus queryBus;

    public RestApiController(CommandBus commandBus, QueryBus queryBus) {
        this.commandBus = commandBus;
        this.queryBus = queryBus;
    }

    public <R> R dispatch(Command command) throws CommandHandlerExecutionError {
        return this.commandBus.dispatch(command);
    }

    public Response ask(Query query) throws QueryHandlerExecutionError {
        return this.queryBus.ask(query);
    }
}
