package com.shop.cafe.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shop.cafe.dto.Member;
import com.shop.cafe.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
//@CrossOrigin("http://127.0.0.1:8080/")
public class MemberController {
	@Autowired
	MemberService memberService;
	
	@PostMapping("login")
	public Map<String, String> login(@RequestBody Member m, HttpServletRequest request){
		Map<String,String> responseData=new HashMap();
		try {
			m=memberService.login(m);
			if(m!=null) {
				HttpSession session = request.getSession();
				System.out.println(session.getId());
				session.setAttribute("member", m);
				responseData.put("msg", "로그인되었습니다");
				
			}else {
				responseData.put("msg", "로그인 실패했습니다");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			responseData.put("msg", "오류");
		}
		
		return responseData;
	}
	

	@PostMapping("insertMember")
	public Map<String, String> insertMember(@RequestBody Member m){
		Map<String,String> responseData=new HashMap();
		try {
			memberService.insertMember(m);
			responseData.put("msg", "ok");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			responseData.put("msg", e.getMessage());
		}
		
		return responseData;
	}
	
	
}

