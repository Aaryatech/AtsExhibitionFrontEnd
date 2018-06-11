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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.exhibition.model.emptaskenq.EmpEnqDatewiseBean;
import com.ats.exhibition.model.emptaskenq.EmpTaskDatewiseBean;
import com.ats.exhibition.model.emptaskenq.GetEmpEnquiry;
import com.ats.exhibition.model.emptaskenq.GetEmpTask;
import com.ats.exhibitionfrontend.common.Constants;
import com.ats.exhibitionfrontend.common.DateConvertor;
import com.ats.exhibitionfrontend.model.ErrorMessage;
import com.ats.exhibitionfrontend.model.ExhEmpWithExhName;
import com.ats.exhibitionfrontend.model.LoginResponseExh;

@Controller
public class EnqAndTaskController {

	RestTemplate rest = new RestTemplate();
	List<ExhEmpWithExhName> empList;

	@RequestMapping(value = "/showEmpEnqTask", method = RequestMethod.GET)
	public ModelAndView showEmpEnqTask(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("enqtask/getenqandtask");
		try {

			HttpSession session = request.getSession();
			LoginResponseExh login = (LoginResponseExh) session.getAttribute("UserDetail");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("exhId", login.getExhibitor().getExhId());

			ExhEmpWithExhName[] res = rest.postForObject(Constants.url + "/getAllEmployeeIsUsed", map,
					ExhEmpWithExhName[].class);
			empList = new ArrayList<ExhEmpWithExhName>(Arrays.asList(res));
			model.addObject("empList", empList);
			model.addObject("empId", 0);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	// getEnqAndTaskByEmp

	@RequestMapping(value = "/getEnqAndTaskByEmp", method = RequestMethod.POST)
	public ModelAndView getEnqAndTaskByEmp(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("enqtask/getenqandtask");
		try {

			int empId = Integer.parseInt(request.getParameter("emp"));

			String fromDate = request.getParameter("fromDate");

			String toDate = request.getParameter("toDate");

			HttpSession session = request.getSession();
			LoginResponseExh login = (LoginResponseExh) session.getAttribute("UserDetail");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			int exhbId = login.getExhibitor().getExhId();
			if (empId == -1) {
				empId = 0;
			}

			map.add("empId", empId);
			map.add("exhbId", exhbId);
			map.add("fromDate", DateConvertor.convertToYMD(fromDate));
			map.add("toDate", DateConvertor.convertToYMD(toDate));

			GetEmpEnquiry[] Enqres = rest.postForObject(Constants.url + "/getEmpEnquiry", map, GetEmpEnquiry[].class);

			List<GetEmpEnquiry> enqList = new ArrayList<GetEmpEnquiry>(Arrays.asList(Enqres));

			model.addObject("enqList", enqList);

			GetEmpTask[] taskRes = rest.postForObject(Constants.url + "/getEmpTask", map, GetEmpTask[].class);

			List<GetEmpTask> taskList = new ArrayList<GetEmpTask>(Arrays.asList(taskRes));

			model.addObject("taskList", taskList);

			model.addObject("empList", empList);

			model.addObject("fromDate", fromDate);

			model.addObject("toDate", toDate);

			model.addObject("empId", empId);

			GetEmpEnquiry enqPrev = new GetEmpEnquiry();

			GetEmpEnquiry enqNext = new GetEmpEnquiry();

			List<GetEmpEnquiry> tempEnqList = null;
			List<EmpEnqDatewiseBean> enqDatewiseList = new ArrayList<EmpEnqDatewiseBean>();
			//EmpEnqDatewiseBean bean = null;
			
			
			for (int i = 0; i < enqList.size(); i++) {
				
				int flag=0;

				enqPrev = enqList.get(i);

				tempEnqList = new ArrayList<GetEmpEnquiry>();
				
				for (int j = i + 1; j < enqList.size(); j++) {
					enqNext = enqList.get(j);

					if (enqPrev.getNextMeetDate().equalsIgnoreCase(enqNext.getNextMeetDate())&& enqNext.getTempStatus()==0) {
						
						EmpEnqDatewiseBean bean = new EmpEnqDatewiseBean();

						tempEnqList.add(enqNext);
						tempEnqList.add(enqPrev);
						bean.setDate(enqPrev.getNextMeetDate());
						
						enqNext.setTempStatus(1);
						bean.setEmpEnq(tempEnqList);
						enqDatewiseList.add(bean);
						
						flag=1;
						
					}

				}
				
				if(flag==0 && enqPrev.getTempStatus()==0) {
					EmpEnqDatewiseBean bean = new EmpEnqDatewiseBean();
					tempEnqList.add(enqPrev);
					bean.setDate(enqPrev.getNextMeetDate());
					enqDatewiseList.add(bean);
					bean.setEmpEnq(tempEnqList);
				}
			}
			
			
			
			System.err.println("Enq Date Wise list enq " +enqDatewiseList.toString()); 
			model.addObject("enqDatewiseList", enqDatewiseList);
			
			//
			
			GetEmpTask taskPrev = new GetEmpTask();

			GetEmpTask taskNext = new GetEmpTask();

			List<GetEmpTask> tempTaskList = null;
			List<EmpTaskDatewiseBean> taskDatewiseList = new ArrayList<EmpTaskDatewiseBean>();
			//EmpEnqDatewiseBean bean = null;
			
			
			for (int i = 0; i < taskList.size(); i++) {
				
				int flag=0;

				taskPrev = taskList.get(i);

				tempTaskList = new ArrayList<GetEmpTask>();
				
				for (int j = i + 1; j < taskList.size(); j++) {
					taskNext = taskList.get(j);

					if (taskPrev.getDate().equalsIgnoreCase(taskNext.getDate())&& taskNext.getTempStatus()==0) {
						
						EmpTaskDatewiseBean bean = new EmpTaskDatewiseBean();

						tempTaskList.add(taskNext);
						tempTaskList.add(taskPrev);
						bean.setDate(taskPrev.getDate());
						
						taskNext.setTempStatus(1);
						bean.setEmpTask(tempTaskList);
						taskDatewiseList.add(bean);
						
						flag=1;
						
					}

				}
				
				if(flag==0 && taskPrev.getTempStatus()==0) {
					EmpTaskDatewiseBean bean = new EmpTaskDatewiseBean();
					tempTaskList.add(taskPrev);
					bean.setDate(taskPrev.getDate());
					taskDatewiseList.add(bean);
					bean.setEmpTask(tempTaskList);
				}
			}
			System.err.println("Task Date Wise list tasks " +taskDatewiseList.toString()); 
			model.addObject("taskDatewiseList", taskDatewiseList);

			//

		} catch (Exception e) {
			System.err.println("Exception  in /getEnqAndTaskByEmp FE  @EnqAndTaskController " + e.getMessage());
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/showEmpEnqEdit", method = RequestMethod.GET)
	public ModelAndView showEmpEnqEdit(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("enqtask/editempenq");
		try {

			HttpSession session = request.getSession();
			LoginResponseExh login = (LoginResponseExh) session.getAttribute("UserDetail");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("exhId", login.getExhibitor().getExhId());

			ExhEmpWithExhName[] res = rest.postForObject(Constants.url + "/getAllEmployeeIsUsed", map,
					ExhEmpWithExhName[].class);
			empList = new ArrayList<ExhEmpWithExhName>(Arrays.asList(res));
			model.addObject("empList", empList);
			model.addObject("empId", 0);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/getEnqAndTaskByEmpForEdit", method = RequestMethod.POST)
	public ModelAndView getEnqAndTaskByEmpForEdit(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("enqtask/editempenq");

		try {
			
			System.err.println("in side edit call ");
			

			int empId = Integer.parseInt(request.getParameter("emp"));

			String fromDate = request.getParameter("fromDate");

			String toDate = request.getParameter("toDate");

			HttpSession session = request.getSession();
			LoginResponseExh login = (LoginResponseExh) session.getAttribute("UserDetail");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			int exhbId = login.getExhibitor().getExhId();
			if (empId == -1) {
				empId = 0;
			}

			map.add("empId", empId);
			map.add("exhbId", exhbId);
			map.add("fromDate", DateConvertor.convertToYMD(fromDate));
			map.add("toDate", DateConvertor.convertToYMD(toDate));

			GetEmpEnquiry[] Enqres = rest.postForObject(Constants.url + "/getEmpEnquiry", map, GetEmpEnquiry[].class);

			List<GetEmpEnquiry> enqList = new ArrayList<GetEmpEnquiry>(Arrays.asList(Enqres));

			model.addObject("enqList", enqList);

			GetEmpTask[] taskRes = rest.postForObject(Constants.url + "/getEmpTask", map, GetEmpTask[].class);

			List<GetEmpTask> taskList = new ArrayList<GetEmpTask>(Arrays.asList(taskRes));

			model.addObject("taskList", taskList);

			model.addObject("empList", empList);

			model.addObject("fromDate", fromDate);

			model.addObject("toDate", toDate);

			model.addObject("empId", empId);
			model.addObject("enqSize", enqList.size());

		} catch (Exception e) {
			System.err.println("Exception  in /getEnqAndTaskByEmp FE  @EnqAndTaskController " + e.getMessage());
			e.printStackTrace();
		}

		return model;
	}

	// editEmpEnquiry

	@RequestMapping(value = "/editEmpEnquiry/{empId}/{enqId}/{meetDate}/{enqStatus}", method = RequestMethod.GET)
	public String editEmpEnquiry(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("empId") int empId, @PathVariable("enqId") int enqId,@PathVariable("meetDate") String meetDate,
			@PathVariable("enqStatus") int enqStatus) {

		System.err.println("Inside Edit enquiry call : emp Id  " + empId +"meetDate " +meetDate+ "enq status  " +enqStatus);
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("empId", empId);
		map.add("enqId", enqId);
		map.add("meetDate", meetDate);
		map.add("status", enqStatus);
		
		ErrorMessage edit = rest.postForObject(Constants.url + "editEnquiryHeader", map, ErrorMessage.class);

		return "redirect:/showEmpEnqTask";

	}

}
