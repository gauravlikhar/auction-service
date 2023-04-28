/**
 * @author yukti.gupta
 * @date 27/04/23
 * @project_name auction
 **/

package com.assignment.auction.entity;

import com.assignment.auction.enums.ItemType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long itemId;

    @Column(name = "name", columnDefinition = "varchar(45)")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "item_type", columnDefinition = "VARCHAR(40)", nullable = false)
    private ItemType itemType;
}
