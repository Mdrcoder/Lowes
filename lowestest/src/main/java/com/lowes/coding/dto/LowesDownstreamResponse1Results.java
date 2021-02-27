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
public class LowesDownstreamResponse1Results {

	private String category;
	private String type;
	private String difficulty;
	private String question;
	private String correct_answer;
	private List<String> incorrect_answers;

}
