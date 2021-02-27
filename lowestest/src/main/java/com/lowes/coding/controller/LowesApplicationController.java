package com.lowes.coding.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lowes.coding.dto.LowesDownstreamResponseDto;
import com.lowes.coding.dto.LowesDownstreamResponse1Results;
import com.lowes.coding.dto.LowesTestResponseDto;
import com.lowes.coding.service.LowesApplicationService;

/**
 * @author manan
 * controller to return lowes response
 */
@RestController
@RequestMapping("/exercise")
public class LowesApplicationController {
	
	@Autowired
	LowesApplicationService lowesApplicationService;
	
	/**
	 * @return LowesTestResponseDto
	 */
	@GetMapping("quiz")
	public ResponseEntity<LowesTestResponseDto> getCodingExerciseQuiz(){
		return ResponseEntity.ok(lowesApplicationService.getCodingExerciseFromDownstream());
				
	}

}
