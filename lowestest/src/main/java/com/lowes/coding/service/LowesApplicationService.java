package com.lowes.coding.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lowes.coding.dto.LowesDownstreamResponseDto;
import com.lowes.coding.dto.LowesDownstreamResponse1Results;
import com.lowes.coding.dto.LowesTestResponseDto;

/**
 * @author manan
 * service interface to perform busiess logic
 */
@Service
public interface LowesApplicationService {
	LowesTestResponseDto getCodingExerciseFromDownstream();
	LowesDownstreamResponseDto getFromDownStreamService(String url);
}
