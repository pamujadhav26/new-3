package com.aubank.loanoncc.controller;

import org.json.JSONObject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController

public class Controller {

	
	@GetMapping("/employees")
	public  String checkLoanEligibilty1() {
		
		char c;
		String s = null;
		for(c=2309;c<2362;c++) {
			System.out.println(" "+c);
			 
			s=String.valueOf(c);   
			 
		}
		 JSONObject json = new JSONObject();
		 json.put("wmpID", "1");
		 json.put("empvalue", s);
	System.out.println(json.toString());
		return json.toString(); 
	}    
	
	
}
