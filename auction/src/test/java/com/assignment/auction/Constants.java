/**
 * @author gaurav.likhar
 * @date 28/04/23
 * @project_name auction
 **/

package com.assignment.auction;

import com.assignment.auction.entity.Auction;
import com.assignment.auction.entity.Bid;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Constants {
    public static final List<Auction> auctionList = new ArrayList<Auction>(){{
            add(Auction.builder().auctionId(2L).itemId("1").minimumPrice(100).build());
            add(Auction.builder().auctionId(3L).itemId("2").minimumPrice(200).build());
    }};
    public static final List<Bid> bidList = new ArrayList<Bid>(){{
        add(Bid.builder().auctionId("2").dealerId("1").amount(400).build());
        add(Bid.builder().auctionId("2").dealerId("2").amount(500).build());
        add(Bid.builder().auctionId("3").dealerId("1").amount(700).build());
        add(Bid.builder().auctionId("3").dealerId("2").amount(800).build());

    }};


    public static List<Bid> getBidById(String id) {
        return bidList.stream().filter(x -> x.getAuctionId().equals(id)).collect(Collectors.toList());
    }

    public static Auction getAuctionById(Long id) {
       return auctionList.stream().filter(x -> x.getAuctionId().equals(id)).findFirst().get();
    }

    public static List<Bid> getBidByDealerId(String id) {
        return bidList.stream().filter(x -> x.getDealerId().equals(id)).collect(Collectors.toList());
    }
}
