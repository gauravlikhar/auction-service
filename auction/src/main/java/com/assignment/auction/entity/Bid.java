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
@Table(name = "bid")
public class Bid{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long bidId;

    @Column(name = "auction_id", columnDefinition = "varchar(45)")
    private String auctionId;

    @Column(name = "dealer_id", columnDefinition = "varchar(45)")
    private String dealerId;

    @Column(name = "amount", columnDefinition = "double default 0")
    private double amount;
}

