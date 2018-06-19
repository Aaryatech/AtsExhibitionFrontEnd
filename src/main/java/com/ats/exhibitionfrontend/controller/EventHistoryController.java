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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import com.ats.exhibitionfrontend.common.Constants;
import com.ats.exhibitionfrontend.model.ComMemWithOrgName;
import com.ats.exhibitionfrontend.model.ExhEventSubscription;
import com.ats.exhibitionfrontend.model.GetSchedule;
import com.ats.exhibitionfrontend.model.GetSponsor;
import com.ats.exhibitionfrontend.model.LoginResponseExh;
import com.ats.exhibitionfrontend.model.eventhistory.Events;
import com.ats.exhibitionfrontend.model.eventhistory.GetEventHistory;
import com.ats.exhibitionfrontend.model.eventhistory.GetEventVisitorName;

@Controller
public class EventHistoryController {

	RestTemplate rest = new RestTemplate();

	@RequestMapping(value = "/showEventHistory", method = RequestMethod.GET)
	public ModelAndView showEventHistory(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("history/eventhistory");
		try {

			HttpSession session = request.getSession();
			LoginResponseExh login = (LoginResponseExh) session.getAttribute("UserDetail");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("exhbId", login.getExhibitor().getExhId());

			GetEventHistory[] res = rest.postForObject(Constants.url + "/getEventHistory", map,
					GetEventHistory[].class);
			List<GetEventHistory> eventHisList = new ArrayList<GetEventHistory>(Arrays.asList(res));
			model.addObject("eventHistory", eventHisList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	// abtEvent

	@RequestMapping(value = "/abtEvent/{eventId}", method = RequestMethod.GET)
	public ModelAndView abtEvent(HttpServletRequest request, HttpServletResponse response, @PathVariable int eventId) {

		ModelAndView model = new ModelAndView("history/abtEvent");
		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("eventId", eventId);

			Events event = rest.postForObject(Constants.url + "/getEventByEId", map, Events.class);

			model.addObject("event", event);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/getEventDetail/{eventId}/{orgId}", method = RequestMethod.GET)
	public ModelAndView getEventDetail(HttpServletRequest request, HttpServletResponse response,
			@PathVariable int eventId, @PathVariable int orgId) {

		ModelAndView model = new ModelAndView("event/abtevent");
		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("eventId", eventId);

			Events event = rest.postForObject(Constants.url + "/getEventByEId", map, Events.class);

			model.addObject("event", event);

			map = new LinkedMultiValueMap<String, Object>();

			map.add("orgId", orgId);
			ComMemWithOrgName[] res = rest.postForObject(Constants.url + "/getAllCommitteeMembersByOrgIdAndIsUsed", map,
					ComMemWithOrgName[].class);

			List<ComMemWithOrgName> memberList = new ArrayList<ComMemWithOrgName>(Arrays.asList(res));

			model.addObject("memberList", memberList);
			map = new LinkedMultiValueMap<String, Object>();
			

			map.add("eventId", eventId);
			GetSponsor[] sponserRes = rest.postForObject(Constants.url + "/getSponsorByEvent", map, GetSponsor[].class);

			List<GetSponsor> sponserList = new ArrayList<GetSponsor>(Arrays.asList(sponserRes));
			
			System.err.println("Sponser"+sponserList.toString());

			model.addObject("sponserList", sponserList);

			
			map = new LinkedMultiValueMap<String, Object>();

			map.add("eventId", eventId);
			GetSchedule[] scheduleRes = rest.postForObject(Constants.url + "/getScheduleByEventId", map, GetSchedule[].class);
			System.err.println("scheduleRes"+scheduleRes.toString());

			List<GetSchedule> scheduleList = new ArrayList<GetSchedule>(Arrays.asList(scheduleRes));
			System.err.println("scheduleList"+scheduleList.toString());

			model.addObject("scheduleList", scheduleList);
			model.addObject("MemberImgUrl", Constants.MEMBER_IMG);
			model.addObject("SponsorImgUrl", Constants.SPONSOR_IMG);
			
			
		} catch (Exception e) {
			System.err.println("Exception in getEventDetail//@ Event History Controller " + e.getMessage());
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/getVisitorNames/{eventId}/{eventName}", method = RequestMethod.GET)
	public ModelAndView getVisitorNames(HttpServletRequest request, HttpServletResponse response,
			@PathVariable int eventId, @PathVariable String eventName) {

		ModelAndView model = new ModelAndView("history/eventvisitorName");
		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			HttpSession session = request.getSession();
			LoginResponseExh login = (LoginResponseExh) session.getAttribute("UserDetail");

			map.add("exhbId", login.getExhibitor().getExhId());
			map.add("eventId", eventId);

			GetEventVisitorName[] visNames = rest.postForObject(Constants.url + "/getEventVisitorName", map,
					GetEventVisitorName[].class);

			List<GetEventVisitorName> visNameList = new ArrayList<GetEventVisitorName>(Arrays.asList(visNames));

			model.addObject("visitorList", visNameList);

			model.addObject("eventName", eventName);

		} catch (Exception e) {
			System.err.println("Exception in /getVisitorNames @EvnHisController ");
			e.printStackTrace();
		}

		return model;
	}

	
	
	
	@RequestMapping(value = "/addEventSubsctiption/{orgId}/{eventId}", method = RequestMethod.GET)
	public String addEventSubsctiption(HttpServletRequest request, HttpServletResponse response,
			@PathVariable int orgId , @PathVariable int eventId) {

		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			HttpSession session = request.getSession();
			LoginResponseExh login = (LoginResponseExh) session.getAttribute("UserDetail");
			
			int exhbId= login.getExhibitor().getExhId();

			
			ExhEventSubscription exEvnSub=new ExhEventSubscription();
			
			exEvnSub.setEventId(eventId);
			exEvnSub.setExhId(exhbId);
			exEvnSub.setOrgId(orgId);
			exEvnSub.setExhEsubId(0);
			exEvnSub.setStallNo("0");
			
			ExhEventSubscription eveResp = rest.postForObject(Constants.url + "/saveExhEventSubscription", exEvnSub,
					ExhEventSubscription.class);

			System.err.println("/saveExhEventSubscription response " +eveResp.toString());
		

		} catch (Exception e) {
			System.err.println("Exception in /getVisitorNames @EvnHisController ");
			e.printStackTrace();
		}

		return "redirect:/home";
	}

	
	
}
