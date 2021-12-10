package com.ourapp.salonapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
	   
	   //for everyone
	   @GetMapping("/landing")
	   public String landing() {
		  return "landing";
	   } 
	   @GetMapping("/outservices")
		public String outservices() {
			return "outservices";
		}
	   
	   //stylist pages
	    @GetMapping("/stylist/stylistLogin")
	    public String getStylistLoginPage() {
	        return "stylistLogin";
	    }
	    
	   @GetMapping("/stylist/stylistprofile")
		public String stylistlanding() {
			return "stylistprofile";
		}
	   @GetMapping("/stylist/stylistassignedwork")
		public String stylistassignedwork() {
			return "stylistassignedwork";
		}

	 //admin pages
	    @GetMapping("/admin/adminLogin")
	    public String getAdminLoginPage() {
	        return "adminLogin";
	    }
	   @GetMapping("/admin/adminassignedwork")
		public String adminassignedwork() {
			return "adminassignedwork";
		}

	   //user pages
	   @GetMapping("/user/login")
		public String login() {
			return "login";
		}
	   @GetMapping("/user/services")
		public String services() {
			return "services";
		}
	  
	    @GetMapping("/user/contact")
		public String contact() {
			return "contact";
		}
		@GetMapping("/user/thankyou")
		public String thankyou() {
			return "thankyou";
		}
		@GetMapping("/user/error")
		public String error() {
			return "error";
		}
		@GetMapping("/user/bill")
		public String bill() {
			return "bill";
		}
		@GetMapping("/user/femaleServices")
		public String femaleServices() {
			return "femaleServices";
		}
		@GetMapping("/user/maleServices")
		public String maleServices() {
			return "maleServices";
		}
		@GetMapping("/user/appointment")
		public String appointment() {
			return "appointment";
		}
		
		 
}
