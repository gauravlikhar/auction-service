/**
 * @author yukti.gupta
 * @date 27/04/23
 * @project_name auction
 **/

package com.assignment.auction.service.impl;

import com.assignment.auction.dto.request.BidRequestDto;
import com.assignment.auction.dto.request.CreateAuctionRequestDto;
import com.assignment.auction.dto.response.HighestBidDetails;
import com.assignment.auction.entity.Auction;
import com.assignment.auction.entity.Bid;
import com.assignment.auction.repository.AuctionRepository;
import com.assignment.auction.repository.BidRepository;
import com.assignment.auction.service.AuctionService;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.validation.ValidationException;
import java.util.*;
import java.util.stream.Collectors;


@Service
@Log4j2
public class AuctionServiceImpl implements AuctionService {
    @Autowired
    private AuctionRepository auctionRepository;

    @Autowired
    private BidRepository bidRepository;

//    @Autowired
//    private RedisOperationService redisOperationsService;


    @Override
    public String createAuction(CreateAuctionRequestDto createAuctionRequestDto) {
        log.info("AuctionServiceImpl :: Creating Auction for createAuctionRequestDto {}", createAuctionRequestDto);
        if(StringUtils.isBlank(createAuctionRequestDto.getItemId())){
            throw new ValidationException("Item id cannot be null");
        }
        Auction auction = Auction.builder()
                .name(createAuctionRequestDto.getName())
                .minimumPrice(createAuctionRequestDto.getMinimumPrice())
                .itemId(createAuctionRequestDto.getItemId()).build();
        Auction auctionResponse = auctionRepository.save(auction);
        return auctionResponse.getAuctionId().toString();
    }

    @Override
    public void runAuction(Long auctionId) throws ValidationException {
        Auction auctionEntity = auctionRepository.findByAuctionId(auctionId);
        if(Objects.nonNull(auctionEntity)) {
            auctionEntity.setStatus(true);
            auctionRepository.save(auctionEntity);
        }
        else{
             throw new ValidationException("No auction exists for this auction id : "+ auctionId);
        }
    }

    @Override
    public List<Auction> getAllAuctions(String auctionId) {
        if(StringUtils.isBlank(auctionId)) {
            return (List<Auction>) auctionRepository.findAll();
        }
        return Collections.singletonList(auctionRepository.findByAuctionId(Long.valueOf(auctionId)));
    }

    @Override
    public HighestBidDetails getHighestBidDetails(String auctionId) throws ValidationException {
        List<Bid> bidList = bidRepository.findByAuctionId(auctionId);
        if(CollectionUtils.isEmpty(bidList)){
            throw new ValidationException("No bid available");
        }
        Bid highestBidDetails = Optional.of(bidList.stream().max(Comparator.comparing(Bid :: getAmount))).get().orElseThrow(() -> new ValidationException("No bid available"));
        return HighestBidDetails.builder()
                .highestBidAmount(highestBidDetails.getAmount())
                .dealerId(highestBidDetails.getDealerId())
                .build();
    }

    @Override
    public List<Bid> getAllBids(String auctionId) {
        if(StringUtils.isBlank(auctionId)) {
            return (List<Bid>) bidRepository.findAll();
        }
        return bidRepository.findByAuctionId(auctionId);
    }

    @Override
    public synchronized void putBid(BidRequestDto bidRequestDto) throws Exception {

        if(StringUtils.isBlank(bidRequestDto.getDealerId())){
            throw new ValidationException("Dealer id cannot be null :"+ bidRequestDto.getAuctionId());
        }
        Auction auction = auctionRepository.findByAuctionId(bidRequestDto.getAuctionId());
        if(Objects.isNull(auction)){
            throw new ValidationException("No auction exists for this auction id :"+ bidRequestDto.getAuctionId());
        }
        if(!auction.isStatus()){
            throw new ValidationException("No auction is running for this auction id :"+ bidRequestDto.getAuctionId());
        }
        List<Bid> bidList = (List<Bid>) bidRepository.findAll();
//        RBucket<Object> rbBucket = redisOperationsService.acquire("Auction_"+ auction.getUuid() , TimeUnit.SECONDS, RedisConstants.REDIS_LOCK_TIMEOUT_COUNT);
//        if(Objects.nonNull(rbBucket)) {
            try {
                if(!CollectionUtils.isEmpty(bidList)) {
                    Set<Double> bidAmounts = bidList.stream().map(Bid::getAmount).collect(Collectors.toSet());
                    double maxBid = Collections.max(bidAmounts);
                    if (bidAmounts.contains(bidRequestDto.getAmount())) {
                        throw new ValidationException("No Two bids can be of the same value");
                    }
                    if (bidRequestDto.getAmount() <= maxBid) {
                        throw new ValidationException("Bid can be on the higher side only");
                    }
                }

                Bid bid = Bid.builder()
                        .amount(bidRequestDto.getAmount())
                        .auctionId(bidRequestDto.getAuctionId().toString())
                        .dealerId(bidRequestDto.getDealerId())
                        .build();
                bidRepository.save(bid);
            }  catch (ValidationException e) {
                throw new ValidationException(e);
            }
            catch (Exception e) {
                throw new Exception("Exception while putting bid on auction. Exception is {}" + e.getMessage(), e);
            }
//            finally {
//                redisOperationsService.release(rbBucket);
//            }
//        } else {
//            putBid(bidRequestDto);
//        }
    }

    @Override
    public List<Bid> getAllBidsForDealer(String dealerId) throws ValidationException{
        if(StringUtils.isBlank(dealerId)) {
            throw new ValidationException("Invalid dealerId : "+ dealerId);
        }
        return bidRepository.findByDealerId(dealerId);

    }

}
