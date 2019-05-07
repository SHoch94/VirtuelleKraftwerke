package de.mygroup.powercore.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@RequestMapping("/")
	public String nothing() {
		return "login";
	}

	@RequestMapping("/logout")
	public String login(HttpSession session) {
		session.removeAttribute("user");
		return "login";
	}
}
