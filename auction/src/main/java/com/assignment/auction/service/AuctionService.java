/**
 * @author yukti.gupta
 * @date 27/04/23
 * @project_name auction
 **/

package com.assignment.auction.service;

import com.assignment.auction.dto.request.BidRequestDto;
import com.assignment.auction.dto.request.CreateAuctionRequestDto;
import com.assignment.auction.dto.response.HighestBidDetails;
import com.assignment.auction.entity.Auction;
import com.assignment.auction.entity.Bid;

import java.util.List;

public interface AuctionService {

    String createAuction(CreateAuctionRequestDto createAuctionRequestDto);

    void runAuction(Long auctionId) throws Exception;

    HighestBidDetails getHighestBidDetails(String auctionId) throws Exception;
    void putBid(BidRequestDto bidRequestDto) throws Exception;

    List<Auction> getAllAuctions(String auctionId);

    List<Bid> getAllBids(String auctionId);

    List<Bid> getAllBidsForDealer(String dealerId) throws Exception;
}
