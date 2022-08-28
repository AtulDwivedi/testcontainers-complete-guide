package com.atuldwivedi.testcontainers.mssql.repo;

import com.atuldwivedi.testcontainers.mssql.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Atul Dwivedi
 */
@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
}
