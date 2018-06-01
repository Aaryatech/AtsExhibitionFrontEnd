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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.exhibitionfrontend.common.Constants;
import com.ats.exhibitionfrontend.common.DateConvertor;
import com.ats.exhibitionfrontend.model.EnquiryHeaderWithName;
import com.ats.exhibitionfrontend.model.EventExhMappingWithExhName;
import com.ats.exhibitionfrontend.model.ExhEmpWithExhName;
import com.ats.exhibitionfrontend.model.FeedbackTxn;
import com.ats.exhibitionfrontend.model.LoginResponseExh;
import com.ats.exhibitionfrontend.model.VisitorByExhiId;

@Controller
@Scope("session")
public class ExhEmpController {

	RestTemplate rest = new RestTemplate();

	String fromDate = new String();
	String toDate = new String();
	
	List<EnquiryHeaderWithName> pending = new ArrayList<EnquiryHeaderWithName>();
	List<EnquiryHeaderWithName> processing = new ArrayList<EnquiryHeaderWithName>();
	List<EnquiryHeaderWithName> completed = new ArrayList<EnquiryHeaderWithName>();
	List<EnquiryHeaderWithName> closed = new ArrayList<EnquiryHeaderWithName>();
	List<EnquiryHeaderWithName> allEnquiryProcessing = new ArrayList<EnquiryHeaderWithName>();
	List<EnquiryHeaderWithName> allEnquiryPending = new ArrayList<EnquiryHeaderWithName>();
	List<EnquiryHeaderWithName> enquiryNotBetweenClosedList = new ArrayList<EnquiryHeaderWithName>();
	List<EnquiryHeaderWithName> enquiryNotBetweenCompletedList = new ArrayList<EnquiryHeaderWithName>();
	List<EventExhMappingWithExhName> eventList = new ArrayList<EventExhMappingWithExhName>();
	List<VisitorByExhiId> visitorList = new ArrayList<VisitorByExhiId>();
	List<EnquiryHeaderWithName> enquiryList = new ArrayList<EnquiryHeaderWithName>();
	List<FeedbackTxn> feedbackList = new ArrayList<FeedbackTxn>();
	
	@RequestMapping(value = "/exhibitorEmplolyeeDashboard", method = RequestMethod.GET)
	public ModelAndView addEmployee(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("exhEmpDashboard");

		
		try {

			HttpSession session = request.getSession();
			LoginResponseExh login = (LoginResponseExh) session.getAttribute("UserDetail");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("exhId", login.getExhibitor().getExhId());

			ExhEmpWithExhName[] res = rest.postForObject(Constants.url + "/getAllEmployeeIsUsed", map,
					ExhEmpWithExhName[].class);

			List<ExhEmpWithExhName> empList = new ArrayList<ExhEmpWithExhName>(Arrays.asList(res));
			model.addObject("empList", empList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}
	
	
	@RequestMapping(value = "/getExhEmpTotalList", method = RequestMethod.POST) 
	public ModelAndView getTotalList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("exhEmpDashboard");
		
		
		try
		{ 
			 fromDate = request.getParameter("fromDate");
			 toDate = request.getParameter("toDate");
			
			HttpSession session = request.getSession(); 
			LoginResponseExh login = (LoginResponseExh) session.getAttribute("UserDetail");
			
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("exhId", login.getExhibitor().getExhId());
			map.add("fromDate", DateConvertor.convertToYMD(fromDate));
			map.add("toDate", DateConvertor.convertToYMD(toDate));
			
		
			
			EnquiryHeaderWithName[] enquiryHeaderWithName = rest.postForObject(Constants.url + "/getAllEnquiryBetDatesAndByEmpId", map,
					EnquiryHeaderWithName[].class); 
			 enquiryList = new ArrayList<EnquiryHeaderWithName>(Arrays.asList(enquiryHeaderWithName));
			
			
			
			map = new LinkedMultiValueMap<String, Object>();
			map.add("exhId", login.getExhibitor().getExhId());
			map.add("fromDate", DateConvertor.convertToYMD(fromDate));
			map.add("toDate", DateConvertor.convertToYMD(toDate));
			map.add("status", 4);
			EnquiryHeaderWithName[] notBetweenClosed = rest.postForObject(Constants.url + "/enquiryNotBetweenDateWithStatus", map,
					EnquiryHeaderWithName[].class); 
			enquiryNotBetweenClosedList = new ArrayList<EnquiryHeaderWithName>(Arrays.asList(notBetweenClosed));
			
			map = new LinkedMultiValueMap<String, Object>();
			map.add("exhId", login.getExhibitor().getExhId());
			map.add("fromDate", DateConvertor.convertToYMD(fromDate));
			map.add("toDate", DateConvertor.convertToYMD(toDate));
			map.add("status", 5);
			EnquiryHeaderWithName[] notBetweenCompleted = rest.postForObject(Constants.url + "/getAllEnquiryBetDatesAndByEmpId", map,
					EnquiryHeaderWithName[].class); 
			enquiryNotBetweenCompletedList = new ArrayList<EnquiryHeaderWithName>(Arrays.asList(notBetweenCompleted));
			
			map = new LinkedMultiValueMap<String, Object>();
			map.add("exhId", login.getExhibitor().getExhId()); 
			map.add("status", 1);
			EnquiryHeaderWithName[] allPending = rest.postForObject(Constants.url + "/equiryListWithStatus", map,
					EnquiryHeaderWithName[].class); 
			 allEnquiryPending = new ArrayList<EnquiryHeaderWithName>(Arrays.asList(allPending));
			
			map = new LinkedMultiValueMap<String, Object>();
			map.add("exhId", login.getExhibitor().getExhId()); 
			map.add("status", 2);
			EnquiryHeaderWithName[] allProcessing = rest.postForObject(Constants.url + "/equiryListWithStatus", map,
					EnquiryHeaderWithName[].class); 
			allEnquiryProcessing = new ArrayList<EnquiryHeaderWithName>(Arrays.asList(allProcessing));
			
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
			model.addObject("enquiryNotBetweenClosedList", enquiryNotBetweenClosedList);
			model.addObject("enquiryNotBetweenCompletedList", enquiryNotBetweenCompletedList);
			model.addObject("allEnquiryProcessing", allEnquiryProcessing);
			model.addObject("allEnquiryPending", allEnquiryPending);
			   
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

}
