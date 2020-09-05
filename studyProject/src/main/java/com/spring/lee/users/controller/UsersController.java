package com.spring.lee.users.controller;

import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.spring.lee.users.dto.UsersDto;
import com.spring.lee.users.service.UsersService;

@Controller
public class UsersController {
	
	@Autowired
	private UsersService service;
	
	@RequestMapping("/users/signup_form")
	public String signupForm() {
	
		return "users/signup_form";
	}
	
	@RequestMapping("/users/checkid")
	@ResponseBody
	public Map<String, Object> checkid(@RequestParam String inputId){

		return service.isExistId(inputId);
	}
	
	@RequestMapping("/users/signup")
	public ModelAndView signup(UsersDto dto, ModelAndView mView) {

		service.addUser(dto);
		mView.setViewName("users/signup");
		
		return mView;
	}
	@RequestMapping("/users/loginform")
	public String loginform(HttpServletRequest request) {

		String url=request.getParameter("url");
		
		if(url==null){
			String cPath=request.getContextPath();
			url=cPath+"/home.do";
		}
		request.setAttribute("url", url);
		
		return "users/loginform";
	}
	
	@RequestMapping("/users/login")
	public ModelAndView login(UsersDto dto, ModelAndView mView,
			HttpSession session, HttpServletRequest request) {

		String url=request.getParameter("url");
		String encodedUrl=URLEncoder.encode(url);

		mView.addObject("url", url);
		mView.addObject("encodedUrl", encodedUrl);

		service.loginProcess(dto, mView, session);
		mView.setViewName("users/login");
		
		return mView;
	}
	
	@RequestMapping("/users/logout")
	public String logout(HttpSession session) {
		
		session.invalidate();
		
		return "redirect:/home.do";
	}
	
	@RequestMapping("/users/private/info")
	public ModelAndView info(HttpServletRequest request,ModelAndView mView) {
		
		service.getInfo(request.getSession(), mView);
		mView.setViewName("users/private/info");
		
		return mView;
	}
	@RequestMapping("/users/private/delete")
	public ModelAndView delete(HttpServletRequest request,
			ModelAndView mView) {

		service.deleteUser(request.getSession());
		mView.setViewName("users/private/delete");
		
		return mView;
	}
	
	@RequestMapping("/users/private/updateform")
	public ModelAndView updateForm(HttpServletRequest request,
			ModelAndView mView) {
		service.getInfo(request.getSession(), mView);
		mView.setViewName("users/private/updateform");
		return mView;
	}
	
	@RequestMapping("/users/private/profile_upload")
	@ResponseBody
	public Map<String, Object> profile_upload
				(HttpServletRequest request,@RequestParam MultipartFile image){
		System.out.println(image);
		Map<String, Object> map=service.saveProfileImage(request, image);

		return map;
	}
	
	@RequestMapping("/users/private/update")
	public ModelAndView update(HttpServletRequest request, 
			UsersDto dto, ModelAndView mView) {

		service.updateUser(request.getSession(), dto);
		mView.setViewName("redirect:/users/private/info.do");
		
		return mView;
	}
	
	@RequestMapping("/users/private/pwd_updateform")
	public ModelAndView pwdUpdateform(ModelAndView mView) {
		
		mView.setViewName("users/private/pwd_updateform");
		return mView;
	}
	
	@RequestMapping("/users/private/pwd_update")
	public ModelAndView pwdUpdate(ModelAndView mView,
			UsersDto dto, HttpServletRequest request) {

		service.updateUserPwd(request.getSession(), dto, mView);
		mView.setViewName("users/private/pwd_update");
		
		return mView;
	}
}















