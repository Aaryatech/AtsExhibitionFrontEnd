package com.ats.exhibitionfrontend.controller;

import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
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

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import org.springframework.web.servlet.ModelAndView;

import com.ats.exhibitionfrontend.common.Constants;
import com.ats.exhibitionfrontend.common.DateConvertor;

import com.ats.exhibitionfrontend.model.ErrorMessage;
import com.ats.exhibitionfrontend.model.ExhEmpWithExhName;

import com.ats.exhibitionfrontend.model.LoginResponseExh;
import com.ats.exhibitionfrontend.model.Task;
import com.ats.exhibitionfrontend.model.TaskWithName;

@Controller
public class TaskController {

	RestTemplate rest = new RestTemplate();

	@RequestMapping(value = "/assignTaskToEmp", method = RequestMethod.GET)
	public ModelAndView assignTaskToEmp(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("assignTask");
		try {

			HttpSession session = request.getSession();
			LoginResponseExh login = (LoginResponseExh) session.getAttribute("UserDetail");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("exhId", login.getExhibitor().getExhId());

			ExhEmpWithExhName[] res = rest.postForObject(Constants.url + "/getAllEmployeeIsUsed", map,
					ExhEmpWithExhName[].class);

			List<ExhEmpWithExhName> empList = new ArrayList<ExhEmpWithExhName>(Arrays.asList(res));
			model.addObject("empList", empList);

			TaskWithName[] res1 = rest.postForObject(Constants.url + "/getAllTaskListByExhId", map,
					TaskWithName[].class);

			List<TaskWithName> taskList = new ArrayList<TaskWithName>(Arrays.asList(res1));
			
			for (int i = 0; i < taskList.size(); i++) {
				taskList.get(i).setDate(DateConvertor.convertToDMY(taskList.get(i).getDate()));

			}
			model.addObject("taskList", taskList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/insertTask", method = RequestMethod.POST)
	public String insertTask(HttpServletRequest request, HttpServletResponse response) {

		try {

			HttpSession session = request.getSession();
			LoginResponseExh login = (LoginResponseExh) session.getAttribute("UserDetail");
			System.out.println("exhiId " + login.getExhibitor().getExhId());

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			int exhId = login.getExhibitor().getExhId();
			map.add("exhId", login.getExhibitor().getExhId());
             int taskId=0;
             try {
			  taskId = Integer.parseInt(request.getParameter("taskId"));
             }
             catch (Exception e) {
            	 taskId=0;
			}
			String taskDesc = request.getParameter("taskDesc");
			String remark = request.getParameter("remark");
			String date = request.getParameter("date");
			String empId = request.getParameter("empId");

			System.out.println("empName" + empId);
			System.out.println("taskDesc" + taskDesc);
			System.out.println("remark" + remark);
			System.out.println("date" + date);

			Date dateDMY = new SimpleDateFormat("dd-MM-yyyy").parse(date);
			String dateString = new SimpleDateFormat("yyyy-MM-dd").format(dateDMY);

			Task task = new Task();
			task.setTaskId(taskId);
			task.setDate(dateString);
			task.setEmpId(Integer.parseInt(empId));
			task.setExhId(exhId);
			task.setRemark(remark);
			task.setIsUsed(1);
			task.setStatus("1");
			task.setTaskDesc(taskDesc);
			task.setTaskCompletedDate(dateString);

			Task res = rest.postForObject(Constants.url + "/saveTask", task, Task.class);

			System.out.println("res " + res);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/assignTaskToEmp";
	}

	@RequestMapping(value = "/taskDetail/{taskId}", method = RequestMethod.GET)
	public ModelAndView empDetail(@PathVariable int taskId, HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("assignTask");
		try {

			HttpSession session = request.getSession();
			LoginResponseExh login = (LoginResponseExh) session.getAttribute("UserDetail");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			MultiValueMap<String, Object> map1 = new LinkedMultiValueMap<String, Object>();
			map1.add("exhId", login.getExhibitor().getExhId());
			map.add("taskId", taskId);

			TaskWithName res = rest.postForObject(Constants.url + "/getTaskByTaskIdAndIsUsed", map, TaskWithName.class);
			model.addObject("taskDetail", res);

			Date dateDMY = new SimpleDateFormat("yyyy-MM-dd").parse(res.getDate());
			String dateString = new SimpleDateFormat("dd-MM-yyyy").format(dateDMY);
			res.setDate(dateString);

			ExhEmpWithExhName[] res1 = rest.postForObject(Constants.url + "/getAllEmployeeIsUsed", map1,
					ExhEmpWithExhName[].class);

			List<ExhEmpWithExhName> empList = new ArrayList<ExhEmpWithExhName>(Arrays.asList(res1));
			model.addObject("empList", empList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/deleteTask/{taskId}", method = RequestMethod.GET)
	public String deleteEmp(@PathVariable int taskId, HttpServletRequest request, HttpServletResponse response) {

		// ModelAndView model = new ModelAndView("masters/empDetail");
		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("taskId", taskId);

			ErrorMessage res = rest.postForObject(Constants.url + "/deleteTask", map, ErrorMessage.class);
			System.out.println(res);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/assignTaskToEmp";
	}

	@RequestMapping(value = "/showTaskList", method = RequestMethod.GET)
	public ModelAndView showTaskList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/taskList");
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

	@RequestMapping(value = "/taskListByEmpId", method = RequestMethod.GET)
	@ResponseBody
	public List<TaskWithName> taskListByEmpId(HttpServletRequest request, HttpServletResponse response) {

		List<TaskWithName> taskWithNameList = null;
		try {

			int empId = Integer.parseInt(request.getParameter("empId"));

			HttpSession session = request.getSession();
			LoginResponseExh login = (LoginResponseExh) session.getAttribute("UserDetail");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("exhId", login.getExhibitor().getExhId());

			map.add("empId", empId);

			TaskWithName[] taskWithName = rest.postForObject(Constants.url + "/getTaskByEmpIdAndIsUsed", map,
					TaskWithName[].class);
			taskWithNameList = new ArrayList<TaskWithName>(Arrays.asList(taskWithName));
			for (int i = 0; i < taskWithNameList.size(); i++) {
				taskWithNameList.get(i).setDate(DateConvertor.convertToDMY(taskWithNameList.get(i).getDate()));

			}
			System.err.println("taskWithNameList" + taskWithNameList.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return taskWithNameList;
	}

}
