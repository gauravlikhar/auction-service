/**
 * @author yukti.gupta
 * @date 27/04/23
 * @project_name auction
 **/

package com.assignment.auction.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "auction")
public class Auction{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long auctionId;

    @Column(name = "name", columnDefinition = "varchar(45)")
    private String name;

    @Column(name = "item_id", columnDefinition = "varchar(45)", nullable = false)
    private String itemId;

    @Column(name = "status", columnDefinition = "boolean")
    private boolean status;

    @Column(name = "minimum_price", columnDefinition = "double default 0")
    private double minimumPrice;

}
