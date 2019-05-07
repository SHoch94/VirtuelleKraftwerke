package de.mygroup.powercore.controller;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import de.mygroup.powercore.PowercoreApplication;
import de.mygroup.powercore.dao.UserDao;
import de.mygroup.powercore.model.User;
import de.mygroup.powercore.service.EncryptionService;

@Controller
public class UserController {

	@Autowired
	UserDao userDao;

	@Autowired
	EncryptionService encryptionService;

	@RequestMapping("/users")
	public ModelAndView toUsers(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		String viewName = "users";
		if (!PowercoreApplication.isUserLoggedIn((User) session.getAttribute("user"))) {
			viewName = "login";
		} else {
			mv.addObject("users", userDao.findAll());
		}
		mv.setViewName(viewName);
		return mv;
	}

	@RequestMapping("/users/add")
	public ModelAndView addUser(HttpSession session, String name, String password) {
		ModelAndView mv = new ModelAndView();
		String viewName = "/users";
		if (!PowercoreApplication.isUserLoggedIn((User) session.getAttribute("user"))) {
			viewName = "/login";
		} else {
			if (name == null || name.equals("")) {
				mv.addObject("message", "Name nicht befüllt!");
			} else if (password == null || password.equals("")) {
				mv.addObject("message", "Passwort nicht befüllt!");
			} else {
				User user = new User();
				user.setName(name);
				try {
					user.setPassword(encryptionService.hash(password));
					userDao.save(user);
				} catch (NoSuchAlgorithmException e) {
					mv.addObject("message", "Fehler bei Verschlüsselung");
					e.printStackTrace();
				}
			}
		}
		mv.addObject("users", userDao.findAll());
		mv.setViewName(viewName);
		return mv;
	}

	@RequestMapping("/users/save")
	public ModelAndView saveUser(HttpSession session, long id, String name, String password)
			throws NoSuchAlgorithmException {
		ModelAndView mv = new ModelAndView();
		String viewName = "/users";
		if (!PowercoreApplication.isUserLoggedIn((User) session.getAttribute("user"))) {
			viewName = "/login";
		} else {
			if (name == null || name.equals("")) {
				mv.addObject("message", "Name nicht befüllt!");
				viewName = "/editUser";
			} else if (password == null || password.equals("")) {
				mv.addObject("message", "Passwort nicht befüllt!");
				viewName = "/editUser";
			} else {
				User user = userDao.findById(id).orElse(new User());
				user.setName(name);
				user.setPassword(encryptionService.hash(password));
				userDao.save(user);
				mv.addObject("message", "Erfolgreich gespeichert!");
			}
		}
		mv.setViewName(viewName);
		mv.addObject("users", userDao.findAll());
		return mv;
	}

	@RequestMapping("/users/delete")
	public ModelAndView deleteUser(HttpSession session, long id) {
		ModelAndView mv = new ModelAndView();
		String viewName = "/users";
		if (!PowercoreApplication.isUserLoggedIn((User) session.getAttribute("user"))) {
			viewName = "/login";
		} else {
			String message = "Erfolgreich gespeichert!";
			User user = userDao.findById(id).orElse(null);
			if (user != null && user != session.getAttribute("user")) {
				userDao.delete(user);
			} else {
				message = "Benutzer kann nicht gelöscht werden!";
			}
			mv.addObject("message", message);
		}
		mv.addObject("users", userDao.findAll());
		mv.setViewName(viewName);
		return mv;
	}

	@RequestMapping("/users/edit")
	public ModelAndView editUser(HttpSession session, long id) throws NoSuchAlgorithmException {
		ModelAndView mv = new ModelAndView();
		String viewName = "/editUser";
		if (!PowercoreApplication.isUserLoggedIn((User) session.getAttribute("user"))) {
			viewName = "/login";
		} else {
			User user = userDao.findById(id).orElse(new User());
			if (user.getPassword() != null) {
				user.setPassword("hold");
			}
			mv.addObject("user", user);
		}
		mv.setViewName(viewName);
		return mv;
	}
}
