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
public class LowesTestResults {

	private String type;
	private String difficulty;
	private String question ;
	private List<String>all_answers ;
	private String correct_answer ;
    
}
