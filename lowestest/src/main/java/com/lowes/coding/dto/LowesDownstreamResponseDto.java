package com.lowes.coding.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * @author manan
 *
 */
@Getter
@Setter
public class LowesDownstreamResponseDto {
	
	private int response_code;
	private List<LowesDownstreamResponse1Results> results;

}
