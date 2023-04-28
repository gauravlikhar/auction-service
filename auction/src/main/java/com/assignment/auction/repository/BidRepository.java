/**
 * @author yukti.gupta
 * @date 27/04/23
 * @project_name auction
 **/

package com.assignment.auction.repository;

import com.assignment.auction.entity.Bid;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BidRepository extends CrudRepository<Bid, Long> {
    List<Bid> findByDealerId(String dealerId);

    List<Bid> findByAuctionId(String auctionId);
}
