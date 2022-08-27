package com.atuldwivedi.testcontainers.mysql.repo;

import com.atuldwivedi.testcontainers.mysql.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Atul Dwivedi
 */
@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
}
