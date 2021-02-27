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
public class LowesTestQuizDto {
	
	private String category;
	private List<LowesTestResults> results;
	
}
