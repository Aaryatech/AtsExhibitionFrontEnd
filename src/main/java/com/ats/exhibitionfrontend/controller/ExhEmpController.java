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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.exhibitionfrontend.common.Constants;
import com.ats.exhibitionfrontend.common.DateConvertor;
import com.ats.exhibitionfrontend.model.EnquiryHeaderWithName;
import com.ats.exhibitionfrontend.model.EventExhMappingWithExhName;
import com.ats.exhibitionfrontend.model.ExhEmpGraph;
import com.ats.exhibitionfrontend.model.ExhEmpWithExhName;
import com.ats.exhibitionfrontend.model.FeedbackTxn;
import com.ats.exhibitionfrontend.model.LoginResponseExh;
import com.ats.exhibitionfrontend.model.VisitorByExhiId;
import com.ats.exhibitionfrontend.model.VisitorWithOrgEventName;

@Controller
@Scope("session")
public class ExhEmpController {

	RestTemplate rest = new RestTemplate();

	String fromDate = new String();
	String toDate = new String();
	String empId = new String();

	List<EnquiryHeaderWithName> pending = new ArrayList<EnquiryHeaderWithName>();
	List<EnquiryHeaderWithName> processing = new ArrayList<EnquiryHeaderWithName>();
	List<EnquiryHeaderWithName> completed = new ArrayList<EnquiryHeaderWithName>();
	List<EnquiryHeaderWithName> closed = new ArrayList<EnquiryHeaderWithName>();
	List<EnquiryHeaderWithName> enquiryList = new ArrayList<EnquiryHeaderWithName>();
	List<ExhEmpWithExhName> empList = new ArrayList<ExhEmpWithExhName>();

	@RequestMapping(value = "/exhibitorEmplolyeeDashboard", method = RequestMethod.GET)
	public ModelAndView exhibitorEmplolyeeDashboard(HttpServletRequest request, HttpServletResponse response) {

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

		try {
			fromDate = request.getParameter("fromDate");
			toDate = request.getParameter("toDate");
			empId = request.getParameter("empId");

			HttpSession session = request.getSession();
			LoginResponseExh login = (LoginResponseExh) session.getAttribute("UserDetail");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("exhId", login.getExhibitor().getExhId());
			map.add("fromDate", DateConvertor.convertToYMD(fromDate));
			map.add("toDate", DateConvertor.convertToYMD(toDate));
			map.add("empId", empId);

			ExhEmpWithExhName[] res = rest.postForObject(Constants.url + "/getAllEmployeeIsUsed", map,
					ExhEmpWithExhName[].class);

			List<ExhEmpWithExhName> empList = new ArrayList<ExhEmpWithExhName>(Arrays.asList(res));
			model.addObject("empList", empList);

			EnquiryHeaderWithName[] enquiryHeaderWithName = rest.postForObject(
					Constants.url + "/getAllEnquiryBetDatesAndByEmpIdAndExhId", map, EnquiryHeaderWithName[].class);
			enquiryList = new ArrayList<EnquiryHeaderWithName>(Arrays.asList(enquiryHeaderWithName));

			map = new LinkedMultiValueMap<String, Object>();

			pending = new ArrayList<EnquiryHeaderWithName>();
			processing = new ArrayList<EnquiryHeaderWithName>();
			completed = new ArrayList<EnquiryHeaderWithName>();
			closed = new ArrayList<EnquiryHeaderWithName>();

			for (int i = 0; i < enquiryList.size(); i++) {
				if (enquiryList.get(i).getStatus() == 1) {
					pending.add(enquiryList.get(i));
				} else if (enquiryList.get(i).getStatus() == 2) {
					processing.add(enquiryList.get(i));
				} else if (enquiryList.get(i).getStatus() == 5) {
					completed.add(enquiryList.get(i));
				} else if (enquiryList.get(i).getStatus() == 4) {
					closed.add(enquiryList.get(i));
				}
			}

			model.addObject("fromDate", fromDate);
			model.addObject("toDate", toDate);

			model.addObject("pending", pending);
			model.addObject("processing", processing);
			model.addObject("completed", completed);
			model.addObject("closed", closed);
			model.addObject("enquiryList", enquiryList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/enquiryExhList/{status}", method = RequestMethod.GET)
	public ModelAndView enquiryList(@PathVariable int status, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView model = new ModelAndView("enqiryEmpExhList");
		try {
			HttpSession session = request.getSession();
			LoginResponseExh login = (LoginResponseExh) session.getAttribute("UserDetail");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("exhId", login.getExhibitor().getExhId());

			ExhEmpWithExhName[] res = rest.postForObject(Constants.url + "/getAllEmployeeIsUsed", map,
					ExhEmpWithExhName[].class);
			List<ExhEmpWithExhName> empList = new ArrayList<ExhEmpWithExhName>(Arrays.asList(res));
			model.addObject("empList", empList);

			if (status == 1)
				model.addObject("list", pending);
			else if (status == 2)
				model.addObject("list", processing);
			else if (status == 4)
				model.addObject("list", closed);
			else if (status == 5)
				model.addObject("list", completed);

			VisitorWithOrgEventName[] visNames = rest.getForObject(Constants.url + "/getAllVisitorsByIsUsed",
					VisitorWithOrgEventName[].class);

			List<VisitorWithOrgEventName> visNameList = new ArrayList<VisitorWithOrgEventName>(Arrays.asList(visNames));
			model.addObject("visNameList", visNameList);
			model.addObject("status", status);
			model.addObject("fromDate", fromDate);
			model.addObject("toDate", toDate);

			model.addObject("empId", empId);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/showEmployeeExhGraph", method = RequestMethod.GET)
	public ModelAndView showEmployeeExhGraph(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("employeeExhGraph");

		try {

			HttpSession session = request.getSession();
			LoginResponseExh login = (LoginResponseExh) session.getAttribute("UserDetail");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("exhId", login.getExhibitor().getExhId());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/infoExhEmpEnqCount", method = RequestMethod.POST)
	public ModelAndView infoExhEmpEnqCount(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("employeeExhGraph");

		try {
			fromDate = request.getParameter("fromDate");
			toDate = request.getParameter("toDate");

			HttpSession session = request.getSession();
			LoginResponseExh login = (LoginResponseExh) session.getAttribute("UserDetail");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("exhId", login.getExhibitor().getExhId());
			map.add("fromDate", DateConvertor.convertToYMD(fromDate));
			map.add("toDate", DateConvertor.convertToYMD(toDate));

			ExhEmpGraph[] res = rest.postForObject(Constants.url + "/getInfo", map, ExhEmpGraph[].class);

			List<ExhEmpGraph> enqInfo = new ArrayList<ExhEmpGraph>(Arrays.asList(res));
			model.addObject("enqInfo", enqInfo);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}
}