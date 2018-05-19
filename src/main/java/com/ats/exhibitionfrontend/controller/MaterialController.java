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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ats.exhibitionfrontend.common.Constants;
import com.ats.exhibitionfrontend.common.VpsImageUpload;
import com.ats.exhibitionfrontend.model.ErrorMessage;
import com.ats.exhibitionfrontend.model.ExhEmpWithExhName;
import com.ats.exhibitionfrontend.model.ExhMatWithExhName;
import com.ats.exhibitionfrontend.model.ExhMaterail;
import com.ats.exhibitionfrontend.model.LoginResponseExh;

@Controller
public class MaterialController {

	RestTemplate rest = new RestTemplate();

	@RequestMapping(value = "/addMaterial", method = RequestMethod.GET)
	public ModelAndView addProduct(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("material/addMaterial");
		try {

			HttpSession session = request.getSession();
			LoginResponseExh login = (LoginResponseExh) session.getAttribute("UserDetail");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("exhId", login.getExhibitor().getExhId());

			ExhMatWithExhName[] res = rest.postForObject(Constants.url + "/getAllMaterialByExhIdAndIsUsed", map,
					ExhMatWithExhName[].class);
			List<ExhMatWithExhName> matList = new ArrayList<ExhMatWithExhName>(Arrays.asList(res));
			model.addObject("matList", matList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/insertMaterial", method = RequestMethod.POST)
	public String insertEmployee(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("matLink") List<MultipartFile> file) {

		// ModelAndView model = new ModelAndView("masters/addEmployee");
		try {
			HttpSession session = request.getSession();
			LoginResponseExh login = (LoginResponseExh) session.getAttribute("UserDetail");
			System.out.println("exhiId " + login.getExhibitor().getExhId());

			String trId = request.getParameter("trId");
			String matName = request.getParameter("matName");
			String matLink = request.getParameter("matLink");

			System.out.println("trId" + trId);
			System.out.println("matName" + matName);
			System.out.println("matLink" + matLink);
			
			VpsImageUpload upload = new VpsImageUpload();

			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			System.out.println(sdf.format(cal.getTime()));
			
			String curTimeStamp = sdf.format(cal.getTime());
			String img1 = null;
			try {
				img1 =    login.getExhibitor().getExhId()+"_"+curTimeStamp+file.get(0).getOriginalFilename(); 

				upload.saveUploadedFiles(file, Constants.ITEM_IMAGE_TYPE,
						img1); 

				System.out.println("upload method called for image Upload " + file.toString());

			} catch (IOException e) {

				System.out.println("Exce in File Upload In GATE ENTRY  Insert " + e.getMessage());
				e.printStackTrace();
			}
			

			ExhMaterail exhMaterail = new ExhMaterail();
			if (trId == "" || trId == null)
				exhMaterail.setTrId(0);
			else
				exhMaterail.setTrId(Integer.parseInt(trId));
			exhMaterail.setMatName(matName);
			exhMaterail.setMatLink(img1);
			exhMaterail.setIsUsed(1);

			exhMaterail.setExhId(login.getExhibitor().getExhId());
			// exhEmployee.setEmpPhoto(img1);

			System.out.println("exhMaterail" + exhMaterail);

			ExhMaterail res = rest.postForObject(Constants.url + "/saveExhMaterial", exhMaterail, ExhMaterail.class);

			System.out.println("res " + res);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/addMaterial";
	}

	@RequestMapping(value = "/matDetail/{trId}", method = RequestMethod.GET)
	public ModelAndView empDetail(@PathVariable int trId, HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("material/materialDetail");
		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("trId", trId);

			ExhMatWithExhName res = rest.postForObject(Constants.url + "/getAllMatByTrIdAndIsUsed", map,
					ExhMatWithExhName.class);
			model.addObject("matDetail", res);
			model.addObject("fileUrl",Constants.ITEM_IMAGE_URL );

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/deleteMaterial/{trId}", method = RequestMethod.GET)
	public String deleteMaterial(@PathVariable int trId, HttpServletRequest request, HttpServletResponse response) {

		// ModelAndView model = new ModelAndView("material/insertMaterial");
		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("trId", trId);

			ErrorMessage errorMessage = rest.postForObject(Constants.url + "/deleteExhMaterial", map,
					ErrorMessage.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/addMaterial";
	}

}
