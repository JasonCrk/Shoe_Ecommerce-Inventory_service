package com.shoe_ecommerce.inventory.shared.infrastructure.bus.query;

import com.shoe_ecommerce.inventory.shared.domain.Service;
import com.shoe_ecommerce.inventory.shared.domain.bus.query.Query;
import com.shoe_ecommerce.inventory.shared.domain.bus.query.QueryHandler;
import com.shoe_ecommerce.inventory.shared.domain.bus.query.QueryNotRegisteredError;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Service
public final class QueryHandlersInformation {

    HashMap<Class<? extends Query>, Class<? extends QueryHandler>> indexedQueryHandlers;

    public QueryHandlersInformation() throws IOException, URISyntaxException, ClassNotFoundException {
        indexedQueryHandlers = formatHandlers(findQueryHandlers("com.shoe_ecommerce.inventory"));
    }

    public Class<? extends QueryHandler> search(Class<? extends Query> queryClass) throws QueryNotRegisteredError {
        Class<? extends QueryHandler> queryHandlerClass = indexedQueryHandlers.get(queryClass);

        if (null == queryHandlerClass) {
            throw new QueryNotRegisteredError(queryClass);
        }

        return queryHandlerClass;
    }

    private HashMap<Class<? extends Query>, Class<? extends QueryHandler>> formatHandlers(
            Set<Class<? extends QueryHandler>> queryHandlers
    ) {
        HashMap<Class<? extends Query>, Class<? extends QueryHandler>> handlers = new HashMap<>();

        for (Class<? extends QueryHandler> handler : queryHandlers) {
            ParameterizedType paramType = (ParameterizedType) handler.getGenericInterfaces()[0];
            Class<? extends Query> queryClass = (Class<? extends Query>) paramType.getActualTypeArguments()[0];

            handlers.put(queryClass, handler);
        }

        return handlers;
    }

    private Set<Class<? extends QueryHandler>> findQueryHandlers(String packageName) throws URISyntaxException, ClassNotFoundException {
        Set<Class<? extends QueryHandler>> handlers = new HashSet<>();

        String path = packageName.replace('.', '/');
        File directory = new File(getClass().getClassLoader().getResource(path).toURI());

        if (directory.exists()) {
            for (File file : Objects.requireNonNull(directory.listFiles())) {
                String className = file.getName();
                if (className.endsWith(".class")) {
                    String fullClassName = packageName + '.' + className.substring(0, className.length() - 6);
                    Class<?> clazz = Class.forName(fullClassName);
                    if (QueryHandler.class.isAssignableFrom(clazz)) {
                        handlers.add((Class<? extends QueryHandler>) clazz);
                    }
                }
            }
        }

        return handlers;
    }
}
