package com.ats.exhibitionfrontend.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.ErrorManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ats.exhibitionfrontend.common.Constants;
import com.ats.exhibitionfrontend.common.DateConvertor;
import com.ats.exhibitionfrontend.model.CompanyType;
import com.ats.exhibitionfrontend.model.EnquiryHeader;
import com.ats.exhibitionfrontend.model.ErrorMessage;
import com.ats.exhibitionfrontend.model.ExhEmpWithExhName;
import com.ats.exhibitionfrontend.model.Location;
import com.ats.exhibitionfrontend.model.LoginResponseExh;
import com.ats.exhibitionfrontend.model.Visitor;
import com.ats.exhibitionfrontend.model.VisitorExcelResponse;
import com.ats.exhibitionfrontend.model.VisitorExhibitorMapping;
import com.ats.exhibitionfrontend.model.eventhistory.Events;
import com.ats.exhibitionfrontend.model.eventhistory.GetEventHistory;
import com.ats.exhibitionfrontend.model.eventhistory.GetEventVisitorName;
import com.ats.exhibitionfrontend.model.feedback.EventExhMapping;

@Controller
@Scope("session")
public class VisitorController {

	RestTemplate rest = new RestTemplate();
	List<EventExhMapping> eventListByExhiId = new ArrayList<EventExhMapping>();

	VisitorExcelResponse uploadResList;

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
			String visitorId = request.getParameter("visitorId");
			int empId = Integer.parseInt(request.getParameter("empId"));

			System.out.println("visitorId" + visitorId);
			HttpSession session = request.getSession();
			LoginResponseExh login = (LoginResponseExh) session.getAttribute("UserDetail");

			EnquiryHeader insert = new EnquiryHeader();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			Date date = new Date();

			insert.setEventId(eventId);
			insert.setVisitorId(Integer.parseInt(visitorId));
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

	@RequestMapping(value = "/isVisitorExist", method = RequestMethod.GET)
	public @ResponseBody Visitor isVisitorExist(HttpServletRequest request, HttpServletResponse response) {
		Visitor visitor = null;
		// ModelAndView model = new ModelAndView("masters/empDetail");
		try {

			String mobileNo = request.getParameter("visitorMobile");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			map.add("visitorMobile", mobileNo);

			visitor = rest.postForObject(Constants.url + "isVisitorExist", map, Visitor.class);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return visitor;
	}

	int global = 0;

	@RequestMapping(value = "/uploadVis", method = RequestMethod.GET)
	public ModelAndView showUploadVis(HttpServletRequest request, HttpServletResponse response) {

		System.err.println("Inside Controller ");

		ModelAndView model = new ModelAndView("uploadVis");

		try {
			HttpSession session = request.getSession();
			LoginResponseExh login = (LoginResponseExh) session.getAttribute("UserDetail");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("exhiId", login.getExhibitor().getExhId());

			EventExhMapping[] res = rest.postForObject(Constants.url + "/eventMappingListByExhibitorId", map,
					EventExhMapping[].class);
			List<EventExhMapping> eventListByExhiId = new ArrayList<EventExhMapping>(Arrays.asList(res));

			Location[] location = rest.getForObject(Constants.url + "/getAllLocationByIsUsed", Location[].class);
			List<Location> locationList = new ArrayList<Location>(Arrays.asList(location));

			CompanyType[] companyType = rest.getForObject(Constants.url + "/getAllCompaniesByIsUsed",
					CompanyType[].class);
			List<CompanyType> companyTypeList = new ArrayList<CompanyType>(Arrays.asList(companyType));

			model.addObject("companyTypeList", companyTypeList);
			model.addObject("locationList", locationList);

			model.addObject("eventHistory", eventListByExhiId);

			List<Visitor> addedVisList = new ArrayList<>() ;

			List<Visitor> duplicateVisList = new ArrayList<Visitor>();

			if(uploadResList!=null) {
			if (uploadResList.getAddedVis().size() > 0) {
				model.addObject("addedVis", uploadResList.getAddedVis());
				addedVisList = uploadResList.getAddedVis();
			}
		//	addedVisList = new ArrayList<>();

			if (uploadResList.getDupMobNoVis().size() > 0) {

			duplicateVisList = uploadResList.getDupMobNoVis();
			
			model.addObject("dupMobNoVisList", duplicateVisList);

			}
			}
			if(global==1) {
			model.addObject("dupMobNoVisList", duplicateVisList);
			model.addObject("addedVis", addedVisList);

			}
			
			model.addObject("excelUrl",Constants.EXL_FILE);
global=0;			
		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("EXCE IN uploadVis " + e.getMessage());
		}
		return model;

	}

	@RequestMapping(value = "/uploadVisFileProcess", method = RequestMethod.POST)
	public String uploadVisFileProcess(Model model, @RequestParam("file") MultipartFile excelfile,
			HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mav = new ModelAndView("uploadVis");
		try {

			HttpSession session = request.getSession();
			LoginResponseExh login = (LoginResponseExh) session.getAttribute("UserDetail");

			int exhbId = login.getExhibitor().getExhId();

			int i = 7;

			System.out.println("Excel File name " + excelfile.getOriginalFilename());

			int eventId = Integer.parseInt(request.getParameter("eventId"));
			int locationId = Integer.parseInt(request.getParameter("locationId"));
			int companyTypeId = Integer.parseInt(request.getParameter("companyTypeId"));

			// Creates a workbook object from the uploaded excelfile
			XSSFWorkbook workbook = new XSSFWorkbook(excelfile.getInputStream());
			// Creates a worksheet object representing the first sheet
			XSSFSheet worksheet = workbook.getSheetAt(0);
			// Reads the data in excel file until last row is encountered

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("eventId", eventId);

			Events eventResponse = rest.postForObject(Constants.url + "/getEventByEId", map, Events.class);

			List<Visitor> visitorList = new ArrayList<Visitor>();

			System.out.println("Last Row Number is " + worksheet.getLastRowNum());

			while (i <= worksheet.getLastRowNum()) {
				// Creates an object for the UserInfo Model

				Visitor visitor = new Visitor();

				visitor.setOrgId(eventResponse.getOrgId());

				// Creates an object representing a single row in excel
				XSSFRow row = worksheet.getRow(i++);
				// Sets the Read data to the model class
				// user.setId((int) row.getCell(0).getNumericCellValue());

				// visitor.setEveId(eveId);

				visitor.setVisitorName((row.getCell(1).getStringCellValue()));
				visitor.setVisitorEmail(row.getCell(2).getStringCellValue());

				// double mob=row.getCell(3).getNumericCellValue();
				// int mob = (int) row.getCell(3).getNumericCellValue();
				String mob = getCellValueAsString(row.getCell(3));

				System.err.println("String value of mob " + mob);

				visitor.setVisitorMobile(String.valueOf(mob));

				visitor.setVisitorRepresent(row.getCell(4).getStringCellValue());

				visitor.setCompanyTypeId(companyTypeId);
				visitor.setEventId(eventId);
				visitor.setLocationId(locationId);
				visitor.setIsActive(1);
				visitor.setIsUsed(1);
				// persist data into database in here
				visitorList.add(visitor);
			}
			workbook.close();

			// userService.addListUser(lstUser);

			// model.addAttribute("bean", bean);

			System.out.println("Excel File Arraylist " + visitorList.toString());
			System.out.println("Excel File Arraylist Size: " + visitorList.size());

			RestTemplate rest = new RestTemplate();
			uploadResList = new VisitorExcelResponse();
			uploadResList = rest.postForObject(Constants.url + "/saveVisitorList", visitorList,
					VisitorExcelResponse.class);

			System.out.println("Response " + uploadResList.toString());

			List<Visitor> addedVisList = uploadResList.getAddedVis();
			mav.addObject("dupMobNoVisList", uploadResList.getDupMobNoVis());

			if (addedVisList.size() > 0) {
				mav.addObject("addedVis", addedVisList);

				for (int j = 0; j < addedVisList.size(); j++) {

					map = new LinkedMultiValueMap<String, Object>();

					map.add("eventId", addedVisList.get(j).getEventId());
					map.add("visitorId", addedVisList.get(j).getVisitorId());
					map.add("exhibitorId", exhbId);
					map.add("likeStatus", 1);

					VisitorExhibitorMapping exhLike = rest.postForObject(Constants.url + "/updateExhibitorLikeStatus",
							map, VisitorExhibitorMapping.class);

					System.err.println("exh Likes Response with vis excel file upload " + exhLike.toString());

				}

			}
			

		} catch (Exception e) {
			System.err.println("Exception in visitor Excel Upload Data");
			e.printStackTrace();
		}
		global = 1;
		return "redirect:/uploadVis";
	}

	public static String getCellValueAsString(Cell cell) {
		String strCellValue = null;
		if (cell != null) {
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_STRING:
				strCellValue = cell.toString();
				break;
			case Cell.CELL_TYPE_NUMERIC:
				if (DateUtil.isCellDateFormatted(cell)) {
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					strCellValue = dateFormat.format(cell.getDateCellValue());
				} else {
					Double value = cell.getNumericCellValue();
					Long longValue = value.longValue();
					strCellValue = new String(longValue.toString());
				}
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				strCellValue = new String(new Boolean(cell.getBooleanCellValue()).toString());
				break;
			case Cell.CELL_TYPE_BLANK:
				strCellValue = "";
				break;
			}
		}
		return strCellValue;
	}
}
