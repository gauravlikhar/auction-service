/**
 * @author yukti.gupta
 * @date 27/04/23
 * @project_name auction
 **/

package com.assignment.auction.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateAuctionRequestDto {
    @NotNull
    private String name;

    @NotNull
    private String itemId;

    @Min(value = 0)
    private double minimumPrice;
}
