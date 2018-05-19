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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.exhibitionfrontend.common.Constants;
import com.ats.exhibitionfrontend.model.ExhEmpWithExhName;
import com.ats.exhibitionfrontend.model.Exhibitor;
import com.ats.exhibitionfrontend.model.ExhibitorWithOrgName;
import com.ats.exhibitionfrontend.model.Location;
import com.ats.exhibitionfrontend.model.LoginResponseExh;
import com.ats.exhibitionfrontend.model.CompanyType;

@Controller
public class EditProfileController {

	RestTemplate rest = new RestTemplate();
	ExhibitorWithOrgName exhibitorWithNames = new ExhibitorWithOrgName();

	@RequestMapping(value = "/showEditProfile", method = RequestMethod.GET)
	public ModelAndView addEmployee(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/exbEditProfile");
		try {

			HttpSession session = request.getSession();
			LoginResponseExh login = (LoginResponseExh) session.getAttribute("UserDetail");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("exhId", login.getExhibitor().getExhId());

			exhibitorWithNames = rest.postForObject(Constants.url + "/getExhibitorByExhId", map,
					ExhibitorWithOrgName.class);

			System.err.println("Data received  " + exhibitorWithNames.toString());

			Location[] location = rest.getForObject(Constants.url + "/getAllLocationByIsUsed", Location[].class);
			List<Location> locationList = new ArrayList<Location>(Arrays.asList(location));

			CompanyType[] companyTypeList = rest.getForObject(Constants.url + "/getAllCompaniesByIsUsed",
					CompanyType[].class);
			List<CompanyType> companyList = new ArrayList<CompanyType>(Arrays.asList(companyTypeList));

			model.addObject("companyList", companyList);

			model.addObject("locationList", locationList);
			model.addObject("exhib", exhibitorWithNames);

		} catch (Exception e) {
			System.err.println("Exb ");
		}
		return model;
	}

	@RequestMapping(value = "/saveExbProfile", method = RequestMethod.POST)
	public ModelAndView saveEditProfile(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/exbEditProfile");
		try {

			HttpSession session = request.getSession();
			LoginResponseExh login = (LoginResponseExh) session.getAttribute("UserDetail");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			int exbId = login.getExhibitor().getExhId();

			// String exhId = request.getParameter("exhId");
			String exhibitorName = request.getParameter("exb_name");
			String compnyName = request.getParameter("comp_name");
			String aboutCompany = request.getParameter("abt_company");
			String pers1 = request.getParameter("con_per1");
			String pers2 = request.getParameter("con_per2");
			String mob1 = request.getParameter("con_per_mob1");
			String mob2 = request.getParameter("con_per_mob2");
			String email1 = request.getParameter("con_per_em1");
			String email2 = request.getParameter("con_per_em2");
			String latitude = request.getParameter("latitude");
			String longitude = request.getParameter("longitude");
			String address = request.getParameter("address");
			// int companyType = Integer.parseInt(request.getParameter("companyType"));
			String userMob = request.getParameter("mob_no");

			String locId = request.getParameter("loc_id");
			String comTypeId = request.getParameter("company_type_id");
			System.err.println("company type Id received " + comTypeId);

			Exhibitor exhibitor = new Exhibitor();

			exhibitor.setExhId(exbId);
			exhibitor.setAboutCompany(aboutCompany);
			exhibitor.setAddress(address);
			exhibitor.setCompanyType(Integer.parseInt(comTypeId));
			exhibitor.setCompLat(latitude);
			exhibitor.setCompLong(longitude);
			exhibitor.setContactPersonName1(pers1);
			exhibitor.setContactPersonName2(pers2);
			exhibitor.setExhCompany(compnyName);
			exhibitor.setExhName(exhibitorName);

			exhibitor.setPersonEmail1(email1);
			exhibitor.setPersonEmail2(email2);
			exhibitor.setPersonMob1(mob1);
			exhibitor.setPersonMob2(mob2);
			exhibitor.setUserMob(userMob);

			// exhibitorWithNames
			exhibitor.setCompanyTypeId(Integer.parseInt(comTypeId));

			exhibitor.setIsUsed(exhibitorWithNames.getIsUsed());
			exhibitor.setLocationId(Integer.parseInt(locId));
			exhibitor.setLogo(exhibitorWithNames.getLogo());
			exhibitor.setOrgId(exhibitorWithNames.getOrgId());
			exhibitor.setPassword(exhibitorWithNames.getPassword());

			Exhibitor res = rest.postForObject(Constants.url + "/saveExhibitor", exhibitor, Exhibitor.class);

			System.out.println("res " + res.toString());

		} catch (Exception e) {
			System.err.println("Exb " + e.getMessage());
			e.printStackTrace();
		}
		return model;
	}

}
