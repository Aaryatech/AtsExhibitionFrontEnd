package com.ats.exhibitionfrontend.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.exhibitionfrontend.common.Constants;
import com.ats.exhibitionfrontend.common.DateConvertor;
import com.ats.exhibitionfrontend.model.CompanyType;
import com.ats.exhibitionfrontend.model.EnquiryHeader;
import com.ats.exhibitionfrontend.model.ExhEmpWithExhName;
import com.ats.exhibitionfrontend.model.Location;
import com.ats.exhibitionfrontend.model.LoginResponseExh;
import com.ats.exhibitionfrontend.model.Visitor;
import com.ats.exhibitionfrontend.model.eventhistory.GetEventHistory;
import com.ats.exhibitionfrontend.model.eventhistory.GetEventVisitorName;
import com.ats.exhibitionfrontend.model.feedback.EventExhMapping;

@Controller
@Scope("session")
public class VisitorController {

	RestTemplate rest = new RestTemplate();
	List<EventExhMapping> eventListByExhiId = new ArrayList<EventExhMapping>();

	@RequestMapping(value = "/addVisitorByExhibitor", method = RequestMethod.GET)
	public ModelAndView addVisitorByExhibitor(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/addVisitor");
		try {

			Location[] location = rest.getForObject(Constants.url + "/getAllLocationByIsUsed", Location[].class);
			List<Location> locationList = new ArrayList<Location>(Arrays.asList(location));

			CompanyType[] companyType = rest.getForObject(Constants.url + "/getAllCompaniesByIsUsed",
					CompanyType[].class);
			List<CompanyType> companyTypeList = new ArrayList<CompanyType>(Arrays.asList(companyType));

			HttpSession session = request.getSession();
			LoginResponseExh login = (LoginResponseExh) session.getAttribute("UserDetail");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("exhiId", login.getExhibitor().getExhId());

			EventExhMapping[] res = rest.postForObject(Constants.url + "/eventMappingListByExhibitorId", map,
					EventExhMapping[].class);
			eventListByExhiId = new ArrayList<EventExhMapping>(Arrays.asList(res));
			model.addObject("eventHistory", eventListByExhiId);

			model.addObject("companyTypeList", companyTypeList);
			model.addObject("locationList", locationList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/insertVisitor", method = RequestMethod.POST)
	public String insertVisitor(HttpServletRequest request, HttpServletResponse response) {

		try {
			String visitorName = request.getParameter("visitorName");
			String email = request.getParameter("email");
			String mob = request.getParameter("mob");
			String compName = request.getParameter("compName");
			int locationId = Integer.parseInt(request.getParameter("locationId"));
			int companyTypeId = Integer.parseInt(request.getParameter("companyTypeId"));
			int eventId = Integer.parseInt(request.getParameter("eventId"));

			Visitor insert = new Visitor();

			insert.setVisitorName(visitorName);
			insert.setVisitorEmail(email);
			insert.setVisitorMobile(mob);
			insert.setCompanyTypeId(companyTypeId);
			insert.setVisitorRepresent(compName);
			insert.setEventId(eventId);
			insert.setLocationId(locationId);
			insert.setIsActive(1);
			insert.setIsUsed(1);

			for (int i = 0; i < eventListByExhiId.size(); i++) {
				if (eventId == eventListByExhiId.get(i).getEventId()) {
					insert.setOrgId(eventListByExhiId.get(i).getIsUsed());
					break;
				}
			}

			Visitor res = rest.postForObject(Constants.url + "/saveVisitor", insert, Visitor.class);

			System.out.println("res " + res);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/addVisitorByExhibitor";
	}

	@RequestMapping(value = "/addEnquiryByExhibitor", method = RequestMethod.GET)
	public ModelAndView addEnquiryByExhibitor(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/addEnquiry");
		try {

			HttpSession session = request.getSession();
			LoginResponseExh login = (LoginResponseExh) session.getAttribute("UserDetail");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("exhiId", login.getExhibitor().getExhId());

			EventExhMapping[] res = rest.postForObject(Constants.url + "/eventMappingListByExhibitorId", map,
					EventExhMapping[].class);
			List<EventExhMapping> eventListByExhiId = new ArrayList<EventExhMapping>(Arrays.asList(res));
			model.addObject("eventHistory", eventListByExhiId);

			map = new LinkedMultiValueMap<String, Object>();
			map.add("exhId", login.getExhibitor().getExhId());

			ExhEmpWithExhName[] exhEmpWithExhName = rest.postForObject(Constants.url + "/getAllEmployeeIsUsed", map,
					ExhEmpWithExhName[].class);

			List<ExhEmpWithExhName> empList = new ArrayList<ExhEmpWithExhName>(Arrays.asList(exhEmpWithExhName));
			model.addObject("empList", empList);

			/*
			 * MultiValueMap<String, Object> map = new LinkedMultiValueMap<String,
			 * Object>(); map.add("eventId", eventId); map.add("exhbId", 0);
			 * GetEventVisitorName[] visNames = rest.postForObject(Constants.url +
			 * "/getEventVisitorName", map, GetEventVisitorName[].class);
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/getEmpByEventId", method = RequestMethod.GET)
	@ResponseBody
	public List<Visitor> getEmpByEventId(HttpServletRequest request, HttpServletResponse response) {

		List<Visitor> getEmpByEventId = new ArrayList<Visitor>();
		try {

			int eventId = Integer.parseInt(request.getParameter("eventId"));

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("eventId", eventId);
			map.add("exhbId", 0);
			Visitor[] visNames = rest.postForObject(Constants.url + "/getEmpByEventId", map, Visitor[].class);
			getEmpByEventId = new ArrayList<Visitor>(Arrays.asList(visNames));

			System.out.println("getEmpByEventId" + getEmpByEventId);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return getEmpByEventId;
	}

	@RequestMapping(value = "/insertEnquiry", method = RequestMethod.POST)
	public String insertEnquiry(HttpServletRequest request, HttpServletResponse response) {

		try {

			String meetDate = request.getParameter("meetDate");
			int eventId = Integer.parseInt(request.getParameter("eventId"));
			int visitorId = Integer.parseInt(request.getParameter("visitorId"));
			int empId = Integer.parseInt(request.getParameter("empId"));

			HttpSession session = request.getSession();
			LoginResponseExh login = (LoginResponseExh) session.getAttribute("UserDetail");

			EnquiryHeader insert = new EnquiryHeader();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			Date date = new Date();

			insert.setEventId(eventId);
			insert.setVisitorId(visitorId);
			insert.setEmpId(empId);
			insert.setDate(sf.format(date));
			insert.setApproximate_time(time.format(date));
			insert.setNextMeetDate(DateConvertor.convertToYMD(meetDate));
			insert.setStatus(1);
			insert.setIsUsed(1);
			insert.setExhId(login.getExhibitor().getExhId());

			System.out.println("insert" + insert);

			EnquiryHeader res = rest.postForObject(Constants.url + "/saveEnqHeader", insert, EnquiryHeader.class);

			System.out.println("res " + res);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/addEnquiryByExhibitor";
	}

}
