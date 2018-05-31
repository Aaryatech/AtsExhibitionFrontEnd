package com.ats.exhibitionfrontend.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.exhibitionfrontend.common.Constants;
import com.ats.exhibitionfrontend.common.DateConvertor;
import com.ats.exhibitionfrontend.model.EnquiryHeaderWithName;
import com.ats.exhibitionfrontend.model.EventExhMappingWithExhName;
import com.ats.exhibitionfrontend.model.ExhEmpWithExhName;
import com.ats.exhibitionfrontend.model.ExhibitorDashboard;
import com.ats.exhibitionfrontend.model.FeedbackTxn;
import com.ats.exhibitionfrontend.model.LoginResponseExh;
import com.ats.exhibitionfrontend.model.VisitorByExhiId;
import com.ats.exhibitionfrontend.model.VisitorWithOrgEventName;
import com.ats.exhibitionfrontend.model.eventhistory.GetEventVisitorName;
 
@Controller
@Scope("session")
public class DashboardController {
	
	List<EnquiryHeaderWithName> pending = new ArrayList<EnquiryHeaderWithName>();
	List<EnquiryHeaderWithName> processing = new ArrayList<EnquiryHeaderWithName>();
	List<EnquiryHeaderWithName> completed = new ArrayList<EnquiryHeaderWithName>();
	List<EnquiryHeaderWithName> closed = new ArrayList<EnquiryHeaderWithName>();
	RestTemplate rest = new RestTemplate();
	
	@RequestMapping(value = "/exhibitorDashboard", method = RequestMethod.GET)
	public ModelAndView addEmployee(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("dashboard");
		try
		{ 
			
			 
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return model;
	}
	
	@RequestMapping(value = "/getTotalList", method = RequestMethod.POST) 
	public ModelAndView getTotalList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("dashboard");
		
		try
		{ 
			String fromDate = request.getParameter("fromDate");
			String toDate = request.getParameter("toDate");
			
			HttpSession session = request.getSession(); 
			LoginResponseExh login = (LoginResponseExh) session.getAttribute("UserDetail");
			
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("exhId", login.getExhibitor().getExhId());
			map.add("fromDate", DateConvertor.convertToYMD(fromDate));
			map.add("toDate", DateConvertor.convertToYMD(toDate));
			
			EventExhMappingWithExhName[] res = rest.postForObject(Constants.url + "/eventListByExhIdBetweenDate", map,
					EventExhMappingWithExhName[].class); 
			List<EventExhMappingWithExhName> eventList = new ArrayList<EventExhMappingWithExhName>(Arrays.asList(res));
			
			VisitorByExhiId[] visitorByExhiId = rest.postForObject(Constants.url + "/visitorListByExhibitorId", map,
					VisitorByExhiId[].class); 
			List<VisitorByExhiId> visitorList = new ArrayList<VisitorByExhiId>(Arrays.asList(visitorByExhiId));
			
			EnquiryHeaderWithName[] enquiryHeaderWithName = rest.postForObject(Constants.url + "/enquiryBetweenDateByExhibitorId", map,
					EnquiryHeaderWithName[].class); 
			List<EnquiryHeaderWithName> enquiryList = new ArrayList<EnquiryHeaderWithName>(Arrays.asList(enquiryHeaderWithName));
			
			FeedbackTxn[] feedbackTxn = rest.postForObject(Constants.url + "/feedbackbetweenDate", map,
					FeedbackTxn[].class); 
			List<FeedbackTxn> feedbackList = new ArrayList<FeedbackTxn>(Arrays.asList(feedbackTxn));
			
			
			pending = new ArrayList<EnquiryHeaderWithName>();
			processing = new ArrayList<EnquiryHeaderWithName>();
			completed = new ArrayList<EnquiryHeaderWithName>();
			closed = new ArrayList<EnquiryHeaderWithName>();
			
			
			for(int i=0;i<enquiryList.size();i++)
			{
				if(enquiryList.get(i).getStatus()==1)
				{
					pending.add(enquiryList.get(i));
				}
				else if(enquiryList.get(i).getStatus()==2)
				{
					processing.add(enquiryList.get(i));
				}
				else if(enquiryList.get(i).getStatus()==5)
				{
					completed.add(enquiryList.get(i));
				}
				else if(enquiryList.get(i).getStatus()==4)
				{
					closed.add(enquiryList.get(i));
				}
			}
			 
			 
			model.addObject("fromDate", fromDate);
			model.addObject("toDate", toDate);
			
			model.addObject("pending", pending);
			model.addObject("processing", processing);
			model.addObject("completed", completed);
			model.addObject("closed", closed);
			
			   
			model.addObject("eventList", eventList);
			model.addObject("visitorList", visitorList);
			model.addObject("enquiryList", enquiryList);
			model.addObject("feedbackList", feedbackList);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return model;
	}
	
	@RequestMapping(value = "/getChart", method = RequestMethod.GET)
	@ResponseBody
	public ExhibitorDashboard getChart(HttpServletRequest request, HttpServletResponse response) {

		 ExhibitorDashboard exhibitorDashboard = new ExhibitorDashboard();
		try
		{  
			
			 exhibitorDashboard.setTotalPending(pending.size());
			 exhibitorDashboard.setTotalProcessing(processing.size());
			 exhibitorDashboard.setTotalClosed(closed.size());
			 exhibitorDashboard.setTotalCompleted(completed.size());
			 
			 
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return exhibitorDashboard;
	}
	
	
	@RequestMapping(value = "/enquiryList/{status}", method = RequestMethod.GET)
	public ModelAndView enquiryList(@PathVariable int status, HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/enquiryList");
		try
		{ 
			if(status==1)
				 model.addObject("list", pending);
			else if (status==2)
				model.addObject("list", processing);
			else if (status==4)
				model.addObject("list", closed);
			else if (status==5)
				model.addObject("list", completed);
			
		 
			VisitorWithOrgEventName[] visNames = rest.getForObject(Constants.url + "/getAllVisitorsByIsUsed",  
					VisitorWithOrgEventName[].class);

			List<VisitorWithOrgEventName> visNameList = new ArrayList<VisitorWithOrgEventName>(Arrays.asList(visNames)); 
			model.addObject("visNameList", visNameList);
			
			System.out.println("visNameList" + visNameList);
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return model;
	}
	
	@RequestMapping(value = "/showVisitorLikesList", method = RequestMethod.GET)
	public ModelAndView showVisitorLikesList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/visitorLikes");
		try
		{ 
			
			 
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return model;
	}
	
	
	@RequestMapping(value = "/visitorLikesList", method = RequestMethod.GET)
	@ResponseBody
	public List<VisitorByExhiId> visitorLikesList(HttpServletRequest request, HttpServletResponse response) {

		List<VisitorByExhiId> exhibitorDashboard = new ArrayList<VisitorByExhiId>();
		try
		{ 
			String fromDate = request.getParameter("fromDate");
			String toDate = request.getParameter("toDate");
			
			HttpSession session = request.getSession(); 
			LoginResponseExh login = (LoginResponseExh) session.getAttribute("UserDetail");
			
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("exhId", login.getExhibitor().getExhId());
			map.add("fromDate", DateConvertor.convertToYMD(fromDate));
			map.add("toDate", DateConvertor.convertToYMD(toDate));
			
			VisitorByExhiId[] visitorByExhiId = rest.postForObject(Constants.url + "/visitorListByExhibitorId", map,
					VisitorByExhiId[].class); 
			exhibitorDashboard = new ArrayList<VisitorByExhiId>(Arrays.asList(visitorByExhiId));
			
			System.out.println(exhibitorDashboard);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return exhibitorDashboard;
	}

}
