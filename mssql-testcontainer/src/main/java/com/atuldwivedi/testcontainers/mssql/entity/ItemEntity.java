package com.atuldwivedi.testcontainers.mssql.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;

/**
 * @author Atul Dwivedi
 */
@Data
@Entity
@Table(name = "item")
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_number")
    private BigInteger itemNumber;
}
