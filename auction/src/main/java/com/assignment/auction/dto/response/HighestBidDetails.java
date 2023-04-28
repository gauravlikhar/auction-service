/**
 * @author yukti.gupta
 * @date 28/04/23
 * @project_name auction
 **/

package com.assignment.auction.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HighestBidDetails {

    private String dealerId;

    private double highestBidAmount;
}
