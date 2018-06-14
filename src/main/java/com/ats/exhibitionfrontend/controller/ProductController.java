package com.ats.exhibitionfrontend.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ats.exhibitionfrontend.common.Constants;
import com.ats.exhibitionfrontend.common.VpsImageUpload;
import com.ats.exhibitionfrontend.model.ErrorMessage;
import com.ats.exhibitionfrontend.model.LoginResponseExh;
import com.ats.exhibitionfrontend.model.ProductWithExhName;
import com.ats.exhibitionfrontend.model.Products;

@Controller
public class ProductController {
	
	RestTemplate rest = new RestTemplate();
	
	@RequestMapping(value = "/addProduct", method = RequestMethod.GET)
	public ModelAndView addProduct(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("product/addProduct");
		try
		{ 
			HttpSession session = request.getSession(); 
			LoginResponseExh login = (LoginResponseExh) session.getAttribute("UserDetail");
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("exhId", login.getExhibitor().getExhId());
			List<ProductWithExhName> productList=rest.postForObject(Constants.url+"getAllProductsByExId",map, List.class);
			
			model.addObject("productList", productList);
			model.addObject("url", Constants.PRODUCT_IMAGE);
			model.addObject("isEdit", 0);
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/saveProduct", method = RequestMethod.POST)
	public String saveProduct(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("img1") List<MultipartFile> file1,@RequestParam("img2") List<MultipartFile> file2,@RequestParam("image3") List<MultipartFile> file3) {
		
		try {
			String productId = request.getParameter("productId");
			String productName = request.getParameter("productName");
			String prodDesc = request.getParameter("productDescription");
			String prodSpecification = request.getParameter("productSpecif");
			String prodExperty = request.getParameter("proExperty");
			int isEdit = Integer.parseInt(request.getParameter("isEdit"));
			VpsImageUpload upload = new VpsImageUpload();

			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			
			String curTimeStamp = sdf.format(cal.getTime());
			HttpSession session = request.getSession(); 
			LoginResponseExh login = (LoginResponseExh) session.getAttribute("UserDetail");
			System.out.println("exhiId " + login.getExhibitor().getExhId());
			try {
				
				upload.saveUploadedFiles(file1, Constants.PRODUCT_IMAGE_TYPE, curTimeStamp + "-" + file1.get(0).getOriginalFilename());
				System.out.println("upload method called " + file1.toString());
				
				
			} catch (IOException e) {
				
				System.out.println("Exce in File Upload In Product Insert " + e.getMessage());
				e.printStackTrace();
			}
	     try {
				
				upload.saveUploadedFiles(file2, Constants.PRODUCT_IMAGE_TYPE, curTimeStamp + "-" + file2.get(0).getOriginalFilename());
				System.out.println("upload method called " + file2.toString());
				
				
			} catch (IOException e) {
				
				System.out.println("Exce in File Upload In Product Insert " + e.getMessage());
				e.printStackTrace();
			}
	 	try {
			
			upload.saveUploadedFiles(file3, Constants.PRODUCT_IMAGE_TYPE, curTimeStamp + "-" + file3.get(0).getOriginalFilename());
			System.out.println("upload method called " + file3.toString());
			
			
		} catch (IOException e) {
			
			System.out.println("Exce in File Upload In Product Insert " + e.getMessage());
			e.printStackTrace();
		}
	 	String prodImage1="";String prodImage2="";String prodImage3="";
		if(isEdit==0)
		{
			 prodImage1 =file1.get(0).getOriginalFilename();
			 prodImage2 =file2.get(0).getOriginalFilename();
			 prodImage3 =file3.get(0).getOriginalFilename();
		}else
		{
			 prodImage1 = request.getParameter("image11");
			 prodImage2 = request.getParameter("image22");
			 prodImage3 = request.getParameter("image33");
		}
			
			Products product=new Products();
			if(productId!=null&&productId!="")
				product.setProdId(0);
			else
				product.setProdId(Integer.parseInt(productId));
			product.setProdName(productName);
			product.setProdSpecification(prodSpecification);
			product.setProdDesc(prodDesc);
			product.setProdExperty(prodExperty);
			product.setExhId(login.getExhibitor().getExhId());
			product.setProdImage1(prodImage1);
			product.setProdImage2(prodImage2);
			product.setProdImage3(prodImage3);
			product.setIsUsed(1);
			Products productRes = rest.postForObject("" + Constants.url + "saveProducts", product, Products.class);
			System.out.println(productRes.toString());
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/addProduct";
	}
	
	
	@RequestMapping(value = "/editProduct/{productId}",method=RequestMethod.GET)
	public ModelAndView editProduct(@PathVariable int productId, HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView model = new ModelAndView("product/addProduct");
		try {
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("prodId", productId);
		RestTemplate restTemplate = new RestTemplate();
		
		ProductWithExhName productRes=restTemplate.postForObject(Constants.url+"getProductByProdId",map,ProductWithExhName.class);
		System.out.println("Product="+productRes.toString());
		
		model.addObject("product",productRes);
		
		HttpSession session = request.getSession(); 
		LoginResponseExh login = (LoginResponseExh) session.getAttribute("UserDetail");
		System.out.println("exhiId " + login.getExhibitor().getExhId());
	    map = new LinkedMultiValueMap<String, Object>();
		map.add("exhId", login.getExhibitor().getExhId());
		List<ProductWithExhName> productList=rest.postForObject(Constants.url+"getAllProductsByExId",map, List.class);
		
		model.addObject("productList", productList);
		model.addObject("url", Constants.PRODUCT_IMAGE);
		model.addObject("isEdit", 1);

		}catch (Exception e) {
			e.printStackTrace();
		}
		
	return model;
		
	}
	
	@RequestMapping(value="/deleteProduct/{productId}",method=RequestMethod.GET)
	public String deleteProduct(@PathVariable int productId) {

		try {
	      MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
	      map.add("prodId",productId);
		
	      ErrorMessage errorMessage=rest.postForObject(Constants.url+"deleteProduct", map, ErrorMessage.class);
	      
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	      return "redirect:/addProduct";
	   
		   
	}
}
