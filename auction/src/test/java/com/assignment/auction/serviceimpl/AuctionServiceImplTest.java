/**
 * @author yukti.gupta
 * @date 28/04/23
 * @project_name auction
 **/

package com.assignment.auction.serviceimpl;

import com.assignment.auction.Constants;
import com.assignment.auction.dto.request.BidRequestDto;
import com.assignment.auction.repository.AuctionRepository;
import com.assignment.auction.repository.BidRepository;
import com.assignment.auction.service.impl.AuctionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.validation.ValidationException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@DisplayName("Testcase for Auction Service")
public class AuctionServiceImplTest {
    @Mock
    BidRepository bidRepository;

    @Mock
    AuctionRepository auctionRepository;

    @InjectMocks
    AuctionServiceImpl auctionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    @DisplayName("Unit test case for running auction")
    void runAuction(){
        when(auctionRepository.findByAuctionId(anyLong())).thenReturn(null);
        assertThrows(ValidationException.class, () -> auctionService.runAuction(anyLong()));
    }


    @Test
    @DisplayName("Unit test case for fetching auctions")
    void getAllAuctions(){
        when(auctionRepository.findAll()).thenReturn(Constants.auctionList);
        assertEquals(2, auctionService.getAllAuctions(null).size());
    }

    @Test
    @DisplayName("Unit test case for fetching auction")
    void getAuction()  {
        when(auctionRepository.findByAuctionId(anyLong())).thenReturn(Constants.getAuctionById(2L));
        assertEquals(1, auctionService.getAllAuctions("2").size());
    }


    @Test
    @DisplayName("Unit test case for getting highest bid")
    void getHighestBidDetails(){
        when(bidRepository.findByAuctionId(anyString())).thenReturn(Constants.getBidById("2"));
        assertEquals(500, auctionService.getHighestBidDetails("2").getHighestBidAmount());

        when(bidRepository.findByAuctionId(anyString())).thenReturn(Constants.getBidById("3"));
        assertEquals(800, auctionService.getHighestBidDetails("3").getHighestBidAmount());
    }

    @Test
    @DisplayName("Unit test case for getting highest bid exception")
    void getHighestBidDetailsException(){
        when(bidRepository.findByAuctionId(anyString())).thenReturn(null);
        assertThrows(ValidationException.class, () -> auctionService.getHighestBidDetails(anyString()));
    }



    @Test
    @DisplayName("Unit test case for fetching bids")
    void getAllBids(){
        when(bidRepository.findAll()).thenReturn(Constants.bidList);
        assertEquals(4, auctionService.getAllBids(null).size());
    }

    @Test
    @DisplayName("Unit test case for fetching bid")
    void getBid()  {
        when(bidRepository.findByAuctionId(anyString())).thenReturn(Constants.getBidById("2"));
        assertEquals(2, auctionService.getAllBids("2").size());
    }

    @Test
    @DisplayName("Unit test case for fetching bids for dealer")
    void getAllBidsByDealerId(){
        when(bidRepository.findByDealerId(anyString())).thenReturn(Constants.getBidByDealerId("1"));
        assertEquals(2, auctionService.getAllBidsForDealer("2").size());
    }

    @Test
    @DisplayName("Unit test case for fetching bids for dealer exception")
    void getAllBidsByDealerIdException(){
        when(bidRepository.findByDealerId(anyString())).thenReturn(null);
        assertThrows(ValidationException.class, () -> auctionService.getHighestBidDetails(null));
    }

    @Test
    @DisplayName("Unit test case for putting bids")
    void putBid(){
        assertThrows(ValidationException.class, () -> auctionService.putBid(BidRequestDto.builder().auctionId(1L).dealerId(null).amount(5).build()));

        assertThrows(ValidationException.class, () -> auctionService.putBid(BidRequestDto.builder().auctionId(1L).dealerId("1").amount(-5).build()));

        assertThrows(ValidationException.class, () -> auctionService.putBid(BidRequestDto.builder().auctionId(null).dealerId("1").amount(5).build()));

        when(auctionRepository.findByAuctionId(anyLong())).thenReturn(null);
        assertThrows(ValidationException.class, () -> auctionService.putBid(BidRequestDto.builder().auctionId(1L).dealerId("1").amount(5).build()));

        when(bidRepository.findAll()).thenReturn(Constants.bidList);
        assertThrows(ValidationException.class, () -> auctionService.putBid(BidRequestDto.builder().auctionId(2L).dealerId("4").amount(500).build()));

        when(bidRepository.findAll()).thenReturn(Constants.bidList);
        assertThrows(ValidationException.class, () -> auctionService.putBid(BidRequestDto.builder().auctionId(2L).dealerId("4").amount(200).build()));
    }


}

