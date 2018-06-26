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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import com.ats.exhibitionfrontend.common.Constants;
import com.ats.exhibitionfrontend.model.LoginResponseExh;
import com.ats.exhibitionfrontend.model.eventhistory.GetAllEventForExhb;
import com.ats.exhibitionfrontend.model.eventhistory.GetEventHistory;
import com.ats.exhibitionfrontend.model.eventhistory.GetEventVisitorName;
import com.ats.exhibitionfrontend.model.eventhistory.ProductLikeByEvent;
import com.ats.exhibitionfrontend.model.feedback.EventExhMapping;

@Controller
public class ReportControlller {

	RestTemplate rest = new RestTemplate();
	List<GetAllEventForExhb> eventList;

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

			GetAllEventForExhb[] eventListResp = rest.postForObject(Constants.url + "getEventsByExhbId", map,
					GetAllEventForExhb[].class);

			eventList = new ArrayList<GetAllEventForExhb>(Arrays.asList(eventListResp));

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
				map.add("exhbId", exhbId);
			} else {

				map.add("eventId", 0);
				map.add("exhbId", exhbId);

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
	 
	//Eventwise Product Likes
	@RequestMapping(value = "/showProdVisName", method = RequestMethod.GET)
	public ModelAndView showProdVisName(HttpServletRequest request, HttpServletResponse response) {
System.err.println("inside showProdVisName");
		ModelAndView model = new ModelAndView("");
		try {
			model = new ModelAndView("report/eventWiseProdLike");

			HttpSession session = request.getSession();
			LoginResponseExh login = (LoginResponseExh) session.getAttribute("UserDetail");

			int exhbId = login.getExhibitor().getExhId();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			map.add("exhbId", exhbId);

			GetAllEventForExhb[] eventListResp = rest.postForObject(Constants.url + "getEventsByExhbId", map,
					GetAllEventForExhb[].class);

			eventList = new ArrayList<GetAllEventForExhb>(Arrays.asList(eventListResp));

			model.addObject("eventList", eventList);

		} catch (Exception e) {

			System.err.println("Exception in show /showProdVisName  @ ReportController" + e.getMessage());
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/getProdVisNameByEveId", method = RequestMethod.POST)
	public ModelAndView getProdVisNameByEveId(HttpServletRequest request, HttpServletResponse response) {
		System.err.println("inside getProdVisNameByEveId");

		ModelAndView model = null;
		try {
			model = new  ModelAndView("report/eventWiseProdLike");

			HttpSession session = request.getSession();
			LoginResponseExh login = (LoginResponseExh) session.getAttribute("UserDetail");

			int exhbId = login.getExhibitor().getExhId();

			int eventId = Integer.parseInt(request.getParameter("evn_name"));

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			
			System.err.println("Event Id Found  " +eventId);
			
		
				map.add("eventId", eventId);
				map.add("exhbId", exhbId);
		
				ProductLikeByEvent[] visNames = rest.postForObject(Constants.url + "/getProdVisitorName", map,
						ProductLikeByEvent[].class);

			List<ProductLikeByEvent> visNameList = new ArrayList<ProductLikeByEvent>(Arrays.asList(visNames));

			globalEvId = eventId;
			model.addObject("visitorList", visNameList);

			model.addObject("eventList", eventList);

			model.addObject("evnId", globalEvId);

		} catch (Exception e) {

			System.err.println("Exception in show /getProdVisNameByEveId  @ RepportController" + e.getMessage());
			e.printStackTrace();
		}

		return model;
	}
	
	@RequestMapping(value = "/showgetEventLikeCounts", method = RequestMethod.GET)
	public ModelAndView showgetEventLikeCounts(HttpServletRequest request, HttpServletResponse response) {

		 System.err.println("In showgetEventLikeCounts");
		ModelAndView model = new ModelAndView("report/eventslikereport");
		try {
			
			HttpSession session = request.getSession();
			LoginResponseExh login = (LoginResponseExh) session.getAttribute("UserDetail");

			int isSubscribed = login.getIsSubscribed();
			
			model = new ModelAndView("report/eventslikereport");
			
			model.addObject("subStatus",isSubscribed);
		}catch (Exception e) {
			System.err.println("Exce in showing eventslikereport " +e.getMessage());
			e.printStackTrace();
		}
		return model;
	}
	
	
	@RequestMapping(value = "/getEventLikeCounts", method = RequestMethod.GET)
	public @ResponseBody List<GetEventHistory>  getEventLikeCounts(HttpServletRequest request, HttpServletResponse response) {
		List<GetEventHistory> eventHisList = null;
		try {

			System.err.println("Inside AJax Call /getEventLikeCounts");
			HttpSession session = request.getSession();
			LoginResponseExh login = (LoginResponseExh) session.getAttribute("UserDetail");

			int exhbId = login.getExhibitor().getExhId();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		
				map.add("exhbId", exhbId);

				GetEventHistory[] res = rest.postForObject(Constants.url + "/getEventHistory", map,
						GetEventHistory[].class);
				 eventHisList = new ArrayList<GetEventHistory>(Arrays.asList(res));
System.err.println("Ev List  " +eventHisList.toString());
		} catch (Exception e) {

			System.err.println("Exception in show /getEventLikeCounts  @ RepportController" + e.getMessage());
			e.printStackTrace();
		}

		return eventHisList;
	}
	
	
}
