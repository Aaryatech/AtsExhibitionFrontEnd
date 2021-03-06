package com.ats.exhibitionfrontend.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ats.exhibitionfrontend.common.Constants;
import com.ats.exhibitionfrontend.common.VpsImageUpload;
import com.ats.exhibitionfrontend.model.ErrorMessage;
import com.ats.exhibitionfrontend.model.ExhEmpWithExhName;
import com.ats.exhibitionfrontend.model.ExhEmployee;
import com.ats.exhibitionfrontend.model.LoginResponseExh;

@Controller
public class EmpController {

	RestTemplate rest = new RestTemplate();

	@RequestMapping(value = "/isMobileNoExist", method = RequestMethod.GET)
	public @ResponseBody Integer isMobileNoExist(HttpServletRequest request, HttpServletResponse response) {
		Integer res = null;
		// ModelAndView model = new ModelAndView("masters/empDetail");
		try {

			String mobileNo = request.getParameter("mobileNo");
			String callService = request.getParameter("callService");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			map.add("mobileNo", mobileNo);
			map.add("callService", callService);

			res = rest.postForObject(Constants.url + "isMobileNoExist", map, Integer.class);
			System.out.println(res);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}

	@RequestMapping(value = "/addEmployee", method = RequestMethod.GET)
	public ModelAndView addEmployee(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/addEmployee");
		try {

			HttpSession session = request.getSession();
			LoginResponseExh login = (LoginResponseExh) session.getAttribute("UserDetail");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("exhId", login.getExhibitor().getExhId());

			ExhEmpWithExhName[] res = rest.postForObject(Constants.url + "/getAllEmployeeIsUsed", map,
					ExhEmpWithExhName[].class);
			List<ExhEmpWithExhName> empList = new ArrayList<ExhEmpWithExhName>(Arrays.asList(res));

			int empCount = empList.size();

			model.addObject("empCount", empCount);

			model.addObject("empList", empList);
			model.addObject("empImgUrl", Constants.EMP_IMAGE);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/insertEmployee", method = RequestMethod.POST)
	public String insertEmployee(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("image") List<MultipartFile> image) {

		// ModelAndView model = new ModelAndView("masters/addEmployee");
		try {
			VpsImageUpload upload = new VpsImageUpload();

			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			System.out.println(sdf.format(cal.getTime()));

			String curTimeStamp = sdf.format(cal.getTime());
			String img1 = null;
			try {
				img1 = image.get(0).getOriginalFilename();

				upload.saveUploadedFiles(image, Constants.EMP_IMAGE_TYPE, image.get(0).getOriginalFilename());

				System.out.println("upload method called for image Upload " + image.toString());

			} catch (IOException e) {

				System.out.println("Exce in File Upload In GATE ENTRY  Insert " + e.getMessage());
				e.printStackTrace();
			}

			HttpSession session = request.getSession();
			LoginResponseExh login = (LoginResponseExh) session.getAttribute("UserDetail");
			System.out.println("exhiId " + login.getExhibitor().getExhId());

			String empId = request.getParameter("empId");
			String empName = request.getParameter("empName");
			String empDesg = request.getParameter("empDesg");
			String email = request.getParameter("email");
			String mob = request.getParameter("mob");
			String education = request.getParameter("education");
			String empPwd = request.getParameter("empPwd");
			String token = request.getParameter("token");

			System.out.println("empName" + empName);
			System.out.println("empDesg" + empDesg);
			System.out.println("email" + email);
			System.out.println("mob" + mob);
			System.out.println("education" + education);
			System.out.println("empPwd" + empPwd);

			System.out.println("token" + token);

			ExhEmployee exhEmployee = new ExhEmployee();

			exhEmployee.setEmpName(empName);
			exhEmployee.setEmpDesignation(empDesg);
			exhEmployee.setEmpEmail(email);
			exhEmployee.setEmpMobile(mob);
			exhEmployee.setEmpEducation(education);
			exhEmployee.setIsUsed(1);
			exhEmployee.setExhId(login.getExhibitor().getExhId());
			exhEmployee.setEmpPhoto(img1);
			exhEmployee.setPassword(empPwd);
			exhEmployee.setToken(token);

			System.out.println("exhEmployee" + exhEmployee);

			ExhEmployee res = rest.postForObject(Constants.url + "/saveExhEmployee", exhEmployee, ExhEmployee.class);

			System.out.println("res " + res);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/addEmployee";
	}

	@RequestMapping(value = "/empDetail/{empId}", method = RequestMethod.GET)
	public ModelAndView empDetail(@PathVariable int empId, HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/empDetail");
		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("empId", empId);

			ExhEmpWithExhName res = rest.postForObject(Constants.url + "/getAllEmployeeByEmpIdAndIsUsed", map,
					ExhEmpWithExhName.class);
			model.addObject("empDetail", res);
			model.addObject("empImgUrl", Constants.EMP_IMAGE);

			map = new LinkedMultiValueMap<String, Object>();

			HttpSession session = request.getSession();
			LoginResponseExh login = (LoginResponseExh) session.getAttribute("UserDetail");
			map.add("exhId", login.getExhibitor().getExhId());

			ExhEmpWithExhName[] empRes = rest.postForObject(Constants.url + "/getAllEmployeeIsUsed", map,
					ExhEmpWithExhName[].class);
			List<ExhEmpWithExhName> empList = new ArrayList<ExhEmpWithExhName>(Arrays.asList(empRes));

			int empCount = empList.size();

			model.addObject("empCount", empCount);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/deleteEmp/{empId}", method = RequestMethod.GET)
	public String deleteEmp(@PathVariable int empId, HttpServletRequest request, HttpServletResponse response) {

		// ModelAndView model = new ModelAndView("masters/empDetail");
		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("empId", empId);

			ErrorMessage res = rest.postForObject(Constants.url + "/deleteExhEmployee", map, ErrorMessage.class);
			System.out.println(res);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/addEmployee";
	}

	@RequestMapping(value = "/editEmployee", method = RequestMethod.POST)
	public String editEmployee(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("image") List<MultipartFile> image) {

		// ModelAndView model = new ModelAndView("masters/addEmployee");
		try {
			VpsImageUpload upload = new VpsImageUpload();

			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			System.out.println(sdf.format(cal.getTime()));

			String curTimeStamp = sdf.format(cal.getTime());
			String img1 = null;
			img1 = image.get(0).getOriginalFilename();

			// upload.saveUploadedFiles(image, Constants.EMP_IMAGE_TYPE,
			// image.get(0).getOriginalFilename());

			System.out.println("upload method called for image Upload " + image.toString());

			HttpSession session = request.getSession();
			LoginResponseExh login = (LoginResponseExh) session.getAttribute("UserDetail");
			System.out.println("exhiId " + login.getExhibitor().getExhId());

			String empId = request.getParameter("empId");
			String empName = request.getParameter("empName");
			String empDesg = request.getParameter("empDesg");
			String email = request.getParameter("email");
			String mob = request.getParameter("mob");
			String education = request.getParameter("education");
			String empPwd = request.getParameter("empPwd");
			String token = request.getParameter("token");

			System.out.println("empName" + empName);
			System.out.println("empDesg" + empDesg);
			System.out.println("email" + email);
			System.out.println("mob" + mob);
			System.out.println("education" + education);
			System.out.println("empPwd" + empPwd);

			System.out.println("token" + token);

			ExhEmployee exhEmployee = new ExhEmployee();

			exhEmployee.setEmpId(Integer.parseInt(empId));
			exhEmployee.setEmpName(empName);
			exhEmployee.setEmpDesignation(empDesg);
			exhEmployee.setEmpEmail(email);
			exhEmployee.setEmpMobile(mob);
			exhEmployee.setEmpEducation(education);
			exhEmployee.setIsUsed(1);
			exhEmployee.setExhId(login.getExhibitor().getExhId());
			exhEmployee.setEmpPhoto(img1);
			exhEmployee.setPassword(empPwd);
			exhEmployee.setToken(token);

			System.out.println("exhEmployee" + exhEmployee);

			ExhEmployee res = rest.postForObject(Constants.url + "/saveExhEmployee", exhEmployee, ExhEmployee.class);

			System.out.println("res " + res);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/addEmployee";
	}

}
