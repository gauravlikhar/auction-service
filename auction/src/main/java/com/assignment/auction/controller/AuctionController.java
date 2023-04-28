/**
 * @author yukti.gupta
 * @date 27/04/23
 * @project_name auction
 **/

package com.assignment.auction.controller;

import com.assignment.auction.dto.ResponseDto;
import com.assignment.auction.dto.request.BidRequestDto;
import com.assignment.auction.dto.request.CreateAuctionRequestDto;
import com.assignment.auction.dto.response.HighestBidDetails;
import com.assignment.auction.entity.Auction;
import com.assignment.auction.entity.Bid;
import com.assignment.auction.service.AuctionService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/auction")
@Validated
public class AuctionController {

    @Autowired
    private AuctionService auctionService;

    @ApiOperation(value = "Create Auction")
    @PostMapping(value = "/create")
    public ResponseDto<String> createAuction(@RequestBody @Valid @NotNull(message = "Auction Creation payload is null") CreateAuctionRequestDto createAuctionRequestDto) {
        log.info("CreateAuctionRequestDto to create auction {}",createAuctionRequestDto);
        String auctionId = "";
        try {
            auctionId = auctionService.createAuction(createAuctionRequestDto);
            return ResponseDto.success("Auction created successfully", auctionId, "200");
        } catch (ValidationException ve){
            return ResponseDto.failure(ve.getMessage(), auctionId,"400");
        } catch (Exception e) {
            log.error("Error in creating auction, Error is {}", e.getMessage(), e);
            return ResponseDto.failure("Error in creating auction", auctionId, "500");
        }
    }

    @ApiOperation(value = "Runs Auction")
    @PatchMapping(value = "/run/{auction-id}")
    public ResponseDto<Long> runAuction(@NotNull @PathVariable("auction-id") Long auctionId) {
        log.info("Request received to run auction for id {}",auctionId);
        try {
            auctionService.runAuction(auctionId);
            return ResponseDto.success("Auction run successfully", auctionId, "200");
        } catch (ValidationException ve){
            return ResponseDto.failure(ve.getMessage(), auctionId,"400");
        } catch (Exception e) {
            log.error("Error in running auction, Error is {}", e.getMessage(), e);
            return ResponseDto.failure("Error in running auction", auctionId, "500");
        }
    }


    @ApiOperation(value = "Fetches highest Bid of auction")
    @GetMapping(value = "/highest-bid/{auction-id}")
    public ResponseDto<HighestBidDetails> getHighestBidDetails(@NotNull @PathVariable("auction-id") String auctionId) {
        HighestBidDetails highestBidDetails = new HighestBidDetails();
        log.info("Request received to fetch highest bid details");
        try {
            highestBidDetails = auctionService.getHighestBidDetails(auctionId);
            return ResponseDto.success("Highest Bid fetch successfully", highestBidDetails, "200");
        } catch (ValidationException ve) {
            return ResponseDto.failure(ve.getMessage(), highestBidDetails, "400");
        }catch(Exception e) {
            log.error("Error in fetching highest bid details, Error is {}", e.getMessage(), e);
            return ResponseDto.failure("Error in fetching highest bid details", highestBidDetails, "500");
        }
    }

    @ApiOperation(value = "Puts a Bid")
    @PostMapping(value = "/bid")
    public ResponseDto<String> putBid(@RequestBody @Valid @NotNull(message = "Bid Request payload is null") BidRequestDto bidRequestDto) {
        log.info("BidRequestDto to put bid on auction {}",bidRequestDto);
        try {
            auctionService.putBid(bidRequestDto);
            return ResponseDto.success("Bid putted successfully", bidRequestDto.getAuctionId() + " : " + bidRequestDto.getDealerId(), "200");
        }catch (ValidationException ve){
            return ResponseDto.failure(ve.getMessage(), "400");
        }  catch (Exception e) {
            log.error("Error in putting bid, Error is {}", e.getMessage(), e);
            return ResponseDto.failure("Error in putting bid", "500");
        }
    }


    @ApiOperation(value = "Gets all Auctions")
    @GetMapping(value = "/auctions")
    public ResponseDto<List<Auction>> getAllAuctions(@RequestParam(value = "auction-id", required = false) String auctionId) {
        List<Auction> auctionList = new ArrayList<>();
        log.info("Request received to fetch all auction details");
        try {
            auctionList = auctionService.getAllAuctions(auctionId);
            return ResponseDto.success("All auctions fetched successfully", auctionList, "200");
        } catch (Exception e) {
            log.error("Error in fetching all auction details, Error is {}", e.getMessage(), e);
            return ResponseDto.failure("Error in fetching all auction details", auctionList, "500");
        }
    }

    @ApiOperation(value = "Gets all Bids")
    @GetMapping(value = "/bids")
    public ResponseDto<List<Bid>> getAllBids(@RequestParam(value = "auction-id", required = false) String auctionId) {
        List<Bid> bidList = new ArrayList<>();
        log.info("Request received to fetch all bid details");
        try {
            bidList = auctionService.getAllBids(auctionId);
            return ResponseDto.success("All bids fetched successfully", bidList, "200");
        }  catch (Exception e) {
            log.error("Error in fetching all bid details, Error is {}", e.getMessage(), e);
            return ResponseDto.failure("Error in fetching all bid details", bidList, "500");
        }
    }

    @ApiOperation(value = "Gets all Bids for dealer")
    @GetMapping(value = "/bids/dealer/{dealer-id}")
    public ResponseDto<List<Bid>>  getAllBidsForDealer(@NotNull @PathVariable(value = "dealer-id") String dealerId) {
        List<Bid> bidList = new ArrayList<>();
        log.info("Request received to fetch all bids placed by dealer : {}", dealerId);
        try {
            bidList = auctionService.getAllBidsForDealer(dealerId);
            return ResponseDto.success("All bids fetched successfully placed by dealer", bidList, "200");
        } catch (ValidationException ve){
            return ResponseDto.failure(ve.getMessage(), bidList, "400");
        }catch (Exception e) {
            log.error("Error in fetching all bids placed by dealer, Error is {}", e.getMessage(), e);
            return ResponseDto.failure("Error in fetching all bids placed by dealer", bidList, "500");
        }
    }
}
