package com.lowes.coding.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.lowes.coding.dto.LowesDownstreamResponse1Results;
import com.lowes.coding.dto.LowesDownstreamResponseDto;
import com.lowes.coding.dto.LowesTestQuizDto;
import com.lowes.coding.dto.LowesTestResponseDto;
import com.lowes.coding.dto.LowesTestResults;
import com.lowes.coding.service.LowesApplicationService;

import jdk.internal.org.jline.utils.Log;
import lombok.extern.slf4j.Slf4j;

/**
 * @author manan implement LowesApplicationServiceImpl
 */
@Slf4j
@Service
public class LowesApplicationServiceImpl implements LowesApplicationService {

	@Value("${first_downstream_service}")
	String firstDownStreamService;

	@Value("${second_downstream_service}")
	String secondDownStreamService;

	/**
	 * @return response object for endpoint
	 */
	@Transactional
	public LowesTestResponseDto getCodingExerciseFromDownstream() {
		log.debug("**starting service to get LowesTestResponse**");
		LowesDownstreamResponseDto firstDownStreamDto = getFromDownStreamService(firstDownStreamService);
		LowesDownstreamResponseDto secondDownStreamDto = getFromDownStreamService(secondDownStreamService);
		log.debug("**Ending service to get LowesTestResponse**");
		return getResponseDto(firstDownStreamDto, secondDownStreamDto);
	}

	/**
	 * @param firstDownStreamDto
	 * @param secondDownStreamService
	 * @return response object for endpoint
	 */
	public LowesTestResponseDto getResponseDto(LowesDownstreamResponseDto firstDownStreamDto,
			LowesDownstreamResponseDto secondDownStreamService) {
		LowesTestResponseDto response = new LowesTestResponseDto();
		ArrayList<LowesTestQuizDto> lowesResultsList = new ArrayList<LowesTestQuizDto>();
		log.debug("calling first downstream servince");
		ArrayList<String> category1 = getCategoryList(firstDownStreamDto);
		log.debug("first downstream servince success");
		log.debug("calling first downstream servince");
		ArrayList<String> category2 = getCategoryList(secondDownStreamService);
		log.debug("second downstream servince success");
		ArrayList<String> category = new ArrayList<String>();
		category.addAll(category1);
		category.addAll(category2);
		Set<String> setCategory = new HashSet<>(category);
		category.clear();
		category.addAll(setCategory);
		if (category != null) {
			category.forEach(element -> {
				ArrayList<LowesTestResults> resultsList = new ArrayList<LowesTestResults>();
				LowesTestQuizDto lowesResponse = new LowesTestQuizDto();
				lowesResponse.setCategory(element);
				resultsList.addAll(getResultList(element, firstDownStreamDto));
				resultsList.addAll(getResultList(element, secondDownStreamService));
				lowesResponse.setResults(resultsList);
				lowesResultsList.add(lowesResponse);

			});
			response.setQuiz(lowesResultsList);
		}
		return response;
	}

	/**
	 * @param element
	 * @param lowesDownstreamResponseDto
	 * @return result list for a category
	 */
	public ArrayList<LowesTestResults> getResultList(String element,
			LowesDownstreamResponseDto lowesDownstreamResponseDto) {
		ArrayList<LowesTestResults> resultsList = new ArrayList<LowesTestResults>();
		if (lowesDownstreamResponseDto != null) {
			lowesDownstreamResponseDto.getResults().stream().forEach(item -> {
				String categorytype = item.getCategory();
				if (element.equalsIgnoreCase(categorytype)) {
					LowesTestResults results = new LowesTestResults();
					List<String> incorrect_answers = item.getIncorrect_answers();
					results.setType(item.getType());
					results.setQuestion(item.getQuestion());
					results.setCorrect_answer(item.getCorrect_answer());
					results.setDifficulty(item.getDifficulty());
					if (incorrect_answers.contains(item.getCorrect_answer())) {
						results.setAll_answers(incorrect_answers);
					} else {
						incorrect_answers.add(item.getCorrect_answer());
						results.setAll_answers(incorrect_answers);
					}
					resultsList.add(results);
				}
			});
		}
		return resultsList;
	}

	/**
	 * method to call downstream services and return response dto
	 */
	public LowesDownstreamResponseDto getFromDownStreamService(String url) {
		RestTemplate restTemplate = new RestTemplate();
		LowesDownstreamResponseDto responseResults1 = new LowesDownstreamResponseDto();
		try {
			responseResults1 = restTemplate.getForEntity(url, LowesDownstreamResponseDto.class).getBody();
		} catch (Exception e) {
			log.error(e.toString());
		}
		return responseResults1;
	}

	/**
	 * @param firstDownStreamDto
	 * @return list of category from the downStreamDto
	 */
	public ArrayList<String> getCategoryList(LowesDownstreamResponseDto downStreamDto) {
		ArrayList<String> category = new ArrayList<String>();
		if (downStreamDto != null) {
			downStreamDto.getResults().stream().forEach(element -> {
				String catg = element.getCategory();
				category.add(catg);
			});
		}
		return category;
	}
}
