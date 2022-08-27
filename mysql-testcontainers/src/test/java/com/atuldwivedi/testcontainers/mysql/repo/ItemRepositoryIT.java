package com.atuldwivedi.testcontainers.mysql.repo;

import com.atuldwivedi.testcontainers.mysql.entity.ItemEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Atul Dwivedi
 */
@Slf4j
@SpringBootTest
@Testcontainers
@DirtiesContext
public class ItemRepositoryIT {

    @Container
    public static MySQLContainer<?> mySqlDB = new MySQLContainer<>
            ("mysql:5.7.37")
            .withDatabaseName("scm")
            .withUsername("admin")
            .withPassword("admin");

    @DynamicPropertySource
    public static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mySqlDB::getJdbcUrl);
        registry.add("spring.datasource.username", mySqlDB::getUsername);
        registry.add("spring.datasource.password", mySqlDB::getPassword);

    }


    @Autowired
    private ItemRepository itemRepository;

    @Test
    @Sql(value = {"classpath:DDL.sql", "classpath:Data.sql"})
    public void shouldFindAllRecords() {
        List<ItemEntity> entities = itemRepository.findAll();
        assertEquals(1, entities.size());
    }

}
