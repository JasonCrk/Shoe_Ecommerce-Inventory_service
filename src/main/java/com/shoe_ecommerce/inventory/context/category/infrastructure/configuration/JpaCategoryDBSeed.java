package com.shoe_ecommerce.inventory.context.category.infrastructure.configuration;

import com.shoe_ecommerce.inventory.context.category.domain.enums.Gender;
import com.shoe_ecommerce.inventory.context.category.infrastructure.persistence.jpa.models.JpaCategory;
import com.shoe_ecommerce.inventory.context.category.infrastructure.persistence.jpa.repositories.JpaCategoryRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.UUID;

@Configuration
public class JpaCategoryDBSeed {

    Logger logger = LoggerFactory.getLogger(JpaCategoryDBSeed.class);

    @Bean
    CommandLineRunner commandLineRunner(JpaCategoryRepository repository) {
        return args -> {
            List<JpaCategory> categories = List.of(
                    new JpaCategory(UUID.randomUUID(), "Deporte", Gender.MALE),
                    new JpaCategory(UUID.randomUUID(), "Deporte", Gender.FEMALE),
                    new JpaCategory(UUID.randomUUID(), "Casual", Gender.MALE),
                    new JpaCategory(UUID.randomUUID(), "Casual", Gender.FEMALE),
                    new JpaCategory(UUID.randomUUID(), "Casual", Gender.CHILD)
            );

            logger.info(categories.toString());
            repository.saveAll(categories);
        };
    }
}
