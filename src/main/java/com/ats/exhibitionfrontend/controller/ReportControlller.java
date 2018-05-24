package com.ats.exhibitionfrontend.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import com.ats.exhibitionfrontend.common.Constants;
import com.ats.exhibitionfrontend.model.LoginResponseExh;
import com.ats.exhibitionfrontend.model.eventhistory.GetEventVisitorName;
import com.ats.exhibitionfrontend.model.feedback.EventExhMapping;

@Controller
public class ReportControlller {

	RestTemplate rest = new RestTemplate();
	List<EventExhMapping> eventList;

	@RequestMapping(value = "/showEventVisName", method = RequestMethod.GET)
	public ModelAndView showFbQueTxn(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("");
		try {
			model = new ModelAndView("report/eveVisNameReport");

			HttpSession session = request.getSession();
			LoginResponseExh login = (LoginResponseExh) session.getAttribute("UserDetail");

			int exhbId = login.getExhibitor().getExhId();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			map.add("exhbId", exhbId);

			EventExhMapping[] eventListResp = rest.postForObject(Constants.url + "getEventsByExhbId", map,
					EventExhMapping[].class);

			eventList = new ArrayList<EventExhMapping>(Arrays.asList(eventListResp));

			model.addObject("eventList", eventList);

		} catch (Exception e) {

			System.err.println("Exception in show /showEventVisName  @ RepportController" + e.getMessage());
			e.printStackTrace();
		}

		return model;
	}

	int globalEvId = 0;

	@RequestMapping(value = "/getVisNameByEveId", method = RequestMethod.POST)
	public ModelAndView getVisNameByEveId(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("");
		try {
			model = new ModelAndView("report/eveVisNameReport");

			HttpSession session = request.getSession();
			LoginResponseExh login = (LoginResponseExh) session.getAttribute("UserDetail");

			int exhbId = login.getExhibitor().getExhId();

			int eventId = Integer.parseInt(request.getParameter("evn_name"));

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			
			System.err.println("Event Id Found  " +eventId);
			
			if (eventId != -1) {
				map.add("eventId", eventId);
			} else {

				map.add("eventId", 0);

			}

			GetEventVisitorName[] visNames = rest.postForObject(Constants.url + "/getEventVisitorName", map,
					GetEventVisitorName[].class);

			List<GetEventVisitorName> visNameList = new ArrayList<GetEventVisitorName>(Arrays.asList(visNames));

			globalEvId = eventId;
			model.addObject("visitorList", visNameList);

			model.addObject("eventList", eventList);

			model.addObject("evnId", globalEvId);

		} catch (Exception e) {

			System.err.println("Exception in show /showEventVisName  @ RepportController" + e.getMessage());
			e.printStackTrace();
		}

		return model;
	}
}
