package com.atuldwivedi.testcontainers.mssql.repo;

import com.atuldwivedi.testcontainers.mssql.entity.ItemEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.MSSQLServerContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

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

    /**
     * https://medium.com/@apusingh1967/how-to-use-mssql-server-as-test-container-for-integration-testing-using-junit-and-spring-boot-e5e299e1bd97
     */

/*    static DockerImageName myImage =
            DockerImageName.parse("mcr.microsoft.com/mssql/server:2019-latest")
                    .asCompatibleSubstituteFor("mcr.microsoft.com/mssql/server");*/

    static DockerImageName myImage =
            DockerImageName.parse("mcr.microsoft.com/azure-sql-edge:latest")
                    .asCompatibleSubstituteFor("mcr.microsoft.com/mssql/server");

    @Container
    public static MSSQLServerContainer msSql = new MSSQLServerContainer(myImage).acceptLicense();

    @DynamicPropertySource
    public static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", msSql::getJdbcUrl);
        registry.add("spring.datasource.username", msSql::getUsername);
        registry.add("spring.datasource.password", msSql::getPassword);
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
