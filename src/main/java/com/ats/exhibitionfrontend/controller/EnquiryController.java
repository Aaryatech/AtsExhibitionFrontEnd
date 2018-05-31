package com.ats.exhibitionfrontend.controller;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.exhibitionfrontend.common.Constants;
import com.ats.exhibitionfrontend.model.EnquiryDetail;
import com.ats.exhibitionfrontend.model.EnquiryHeaderWithName;

@Controller
public class EnquiryController {
	RestTemplate rest = new RestTemplate();

	@RequestMapping(value = "/showEnquiryBetweenDates", method = RequestMethod.GET)
	public ModelAndView showEnquiryBetweenDates(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("enquiry/enquiryBetDates");
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/enqBetweenDates", method = RequestMethod.POST)
	public ModelAndView enqBetweenDates(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("enquiry/enquiryBetDates");
		try {

			String fromDate = request.getParameter("fromDate");
			String toDate = request.getParameter("toDate");

			Date fromDateDMY = new SimpleDateFormat("dd-MM-yyyy").parse(fromDate);
			Date toDateDMY = new SimpleDateFormat("dd-MM-yyyy").parse(toDate);

			String fromDateString = new SimpleDateFormat("yyyy-MM-dd").format(fromDateDMY);

			String toDateString = new SimpleDateFormat("yyyy-MM-dd").format(toDateDMY);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			map.add("fromDate", fromDateString);
			map.add("toDate", toDateString);

			List<EnquiryHeaderWithName> enquiryHeaderWithName = rest
					.postForObject(Constants.url + "/getAllEnquiryBetweenDates", map, List.class);

			model.addObject("enquiryHeaderWithName", enquiryHeaderWithName);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/enquiryDetail/{enqId}", method = RequestMethod.GET)
	public ModelAndView questionsDetail(HttpServletRequest request, HttpServletResponse response,
			@PathVariable int enqId) {

		ModelAndView model = new ModelAndView("enquiry/enqDetail");
		try {
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			map.add("enqId", enqId);

			EnquiryHeaderWithName enquiryHeaderWithName = rest.postForObject(Constants.url + "/getEnquiryByEnqId", map,
					EnquiryHeaderWithName.class);

			model.addObject("enquiryHeaderWithName", enquiryHeaderWithName);

			List<EnquiryDetail> enquiryDetailList = rest.postForObject(Constants.url + "/getAllEnquiryDetailByEnqId",
					map, List.class);

			model.addObject("enqList", enquiryDetailList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}
}
