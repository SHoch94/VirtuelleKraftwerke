package de.mygroup.powercore.controller;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import de.mygroup.powercore.PowercoreApplication;
import de.mygroup.powercore.dao.UserDao;
import de.mygroup.powercore.model.User;
import de.mygroup.powercore.service.EncryptionService;

@Controller
public class MainController {

	@Autowired
	UserDao dao;

	@Autowired
	EncryptionService encryptionService;

	@RequestMapping("/main")
	public ModelAndView toMain(HttpSession session) {
		ModelAndView mv;
		String viewName = "main";
		if (!PowercoreApplication.isUserLoggedIn((User) session.getAttribute("user"))) {
			viewName = "login";
		}
		mv = new ModelAndView(viewName);
		return mv;
	}

	@RequestMapping("/login")
	public ModelAndView login(HttpSession session, @ModelAttribute("name") String name,
			@ModelAttribute("password") String password) throws NoSuchAlgorithmException {
		ModelAndView mv = new ModelAndView();
		String viewName = "login";
		String message = "Login fehlgeschlagen!";

		User user = dao.findByNameAndPassword(name, encryptionService.hash(password));
		if (null != user) {
			viewName = "main";
			message = "Willkommen, " + name;
			PowercoreApplication.logInUser(user);
			session.setAttribute("user", user);
		}
		mv.setViewName(viewName);
		mv.addObject("message", message);
		return mv;
	}
}
