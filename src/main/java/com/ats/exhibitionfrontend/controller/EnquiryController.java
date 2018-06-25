package com.ats.exhibitionfrontend.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.ats.exhibitionfrontend.model.LoginResponseExh;
import com.ats.exhibitionfrontend.model.eventhistory.GetAllEventForExhb;

@Controller
public class EnquiryController {
	RestTemplate rest = new RestTemplate();

	@RequestMapping(value = "/showEnquiryBetweenDates", method = RequestMethod.GET)
	public ModelAndView showEnquiryBetweenDates(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("enquiry/enquiryBetDates");
		try {

			SimpleDateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");

			Date curDate = new Date();
			String strDate = originalFormat.format(curDate);
			System.out.println("date " + curDate);
			System.out.println("date " + strDate);

			model.addObject("fromDate", strDate);
			model.addObject("toDate", strDate);

			HttpSession session = request.getSession();
			LoginResponseExh login = (LoginResponseExh) session.getAttribute("UserDetail");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("exhId", login.getExhibitor().getExhId());

			map.add("fromDate", strDate);
			map.add("toDate", strDate);

			EnquiryHeaderWithName[] enquiryHeaderWithName = rest
					.postForObject(Constants.url + "/getAllEnquiryBetweenDates", map, EnquiryHeaderWithName[].class);

			List<EnquiryHeaderWithName> eHWNList = new ArrayList<EnquiryHeaderWithName>(
					Arrays.asList(enquiryHeaderWithName));
			model.addObject("enquiryHeaderWithName", eHWNList);

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

			HttpSession session = request.getSession();
			LoginResponseExh login = (LoginResponseExh) session.getAttribute("UserDetail");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("exhId", login.getExhibitor().getExhId());

			map.add("fromDate", fromDateString);
			map.add("toDate", toDateString);

			EnquiryHeaderWithName[] enquiryHeaderWithName = rest
					.postForObject(Constants.url + "/getAllEnquiryBetweenDates", map, EnquiryHeaderWithName[].class);

			List<EnquiryHeaderWithName> eHWNList = new ArrayList<EnquiryHeaderWithName>(
					Arrays.asList(enquiryHeaderWithName));
			Date curDate = new Date();
			SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy");
			for (int i = 0; i < eHWNList.size(); i++) {
				EnquiryHeaderWithName header = eHWNList.get(i);
				System.err.println("Header no  " + i + "is  " + header.toString());
				String stringDate = header.getDate();
				String nextMeetDate = header.getDate();

				if (header.getStatus() == 1 || header.getStatus() == 2 || header.getStatus() == 3) {

					Date date = myFormat.parse(stringDate);
					Date nextMDate = myFormat.parse(nextMeetDate);
					long diff = curDate.getTime() - date.getTime();
					long timeUnit = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
					System.err.println("time unit " + timeUnit);

					header.setNoOfEnqDays(timeUnit);
					// cur Date -header.getDate()
				} else if (header.getStatus() == 4 || header.getStatus() == 5) {

					Date date = myFormat.parse(stringDate);
					Date nextMDate = myFormat.parse(nextMeetDate);
					long diff = nextMDate.getTime() - date.getTime();
					long timeUnit = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
					System.err.println("time unit else " + timeUnit);
					header.setNoOfEnqDays(timeUnit);

					// header.getNextMeetDate()-header.getDate()

				}
				eHWNList.set(i, header);
			}

			model.addObject("enquiryHeaderWithName", eHWNList);
			model.addObject("fromDate", fromDate);
			model.addObject("toDate", toDate);
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
	
	List<GetAllEventForExhb> eventList;

	@RequestMapping(value = "/showEnquiryByEvent", method = RequestMethod.GET)
	public ModelAndView showEnquiryByEvent(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("enquiry/enquiryByEvent");
		
		HttpSession session = request.getSession();
		LoginResponseExh login = (LoginResponseExh) session.getAttribute("UserDetail");

		int exhbId = login.getExhibitor().getExhId();

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

		map.add("exhbId", exhbId);

		GetAllEventForExhb[] eventListResp = rest.postForObject(Constants.url + "getEventsByExhbId", map,
				GetAllEventForExhb[].class);

		eventList = new ArrayList<GetAllEventForExhb>(Arrays.asList(eventListResp));

		model.addObject("eventList", eventList);

		return model;
		
	}
	
	
	
	
	
	@RequestMapping(value = "/getEnqByEvent", method = RequestMethod.POST)
	public ModelAndView getEnqByEvent(HttpServletRequest request, HttpServletResponse response) {
System.err.println("Inside Ger");
		ModelAndView model = new ModelAndView("enquiry/enquiryByEvent");
		try {

			String eventId = request.getParameter("evn_name");
			
			HttpSession session = request.getSession();
			LoginResponseExh login = (LoginResponseExh) session.getAttribute("UserDetail");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("exhId", login.getExhibitor().getExhId());

			map.add("eventId", eventId);

			EnquiryHeaderWithName[] enquiryHeaderWithName = rest
					.postForObject(Constants.url + "/getAllEnquiryByEvent", map, EnquiryHeaderWithName[].class);

			List<EnquiryHeaderWithName> eHWNList = new ArrayList<EnquiryHeaderWithName>(
					Arrays.asList(enquiryHeaderWithName));
			Date curDate = new Date();
			SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy");
			for (int i = 0; i < eHWNList.size(); i++) {
				EnquiryHeaderWithName header = eHWNList.get(i);
				System.err.println("Header no  " + i + "is  " + header.toString());
				String stringDate = header.getDate();
				String nextMeetDate = header.getDate();

				if (header.getStatus() == 1 || header.getStatus() == 2 || header.getStatus() == 3) {

					Date date = myFormat.parse(stringDate);
					Date nextMDate = myFormat.parse(nextMeetDate);
					long diff = curDate.getTime() - date.getTime();
					long timeUnit = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
					System.err.println("time unit " + timeUnit);

					header.setNoOfEnqDays(timeUnit);
					// cur Date -header.getDate()
				} else if (header.getStatus() == 4 || header.getStatus() == 5) {

					Date date = myFormat.parse(stringDate);
					Date nextMDate = myFormat.parse(nextMeetDate);
					long diff = nextMDate.getTime() - date.getTime();
					long timeUnit = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
					System.err.println("time unit else " + timeUnit);
					header.setNoOfEnqDays(timeUnit);

					// header.getNextMeetDate()-header.getDate()

				}
				eHWNList.set(i, header);
			}

			model.addObject("enquiryHeaderWithName", eHWNList);
			model.addObject("eventId", eventId);
			model.addObject("eventList", eventList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	
	
	
}
