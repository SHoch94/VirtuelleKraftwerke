package de.mygroup.powercore.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import de.mygroup.powercore.PowercoreApplication;
import de.mygroup.powercore.dao.PowerplantDao;
import de.mygroup.powercore.dao.TypeDao;
import de.mygroup.powercore.model.Powerplant;
import de.mygroup.powercore.model.Type;
import de.mygroup.powercore.model.User;

@Controller
public class TypeController {

	@Autowired
	TypeDao typeDao;

	@Autowired
	PowerplantDao powerplantDao;

	@RequestMapping("/types")
	public ModelAndView toTypes(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		String viewName = "types";
		if (!PowercoreApplication.isUserLoggedIn((User) session.getAttribute("user"))) {
			viewName = "login";
		} else {
			mv.addObject("types", typeDao.findAll());
		}
		mv.setViewName(viewName);
		return mv;
	}

	@RequestMapping("/types/add")
	public ModelAndView addType(HttpSession session, String name) {
		ModelAndView mv = new ModelAndView();
		String viewName = "/types";

		if (!PowercoreApplication.isUserLoggedIn((User) session.getAttribute("user"))) {
			viewName = "login";
		} else {
			Type type = new Type();
			type.setName(name);
			typeDao.save(type);
			mv.addObject("message", "Speichern erfolgreich!");
			mv.addObject("types", typeDao.findAll());
		}
		mv.setViewName(viewName);
		return mv;
	}

	@RequestMapping("/types/delete")
	public ModelAndView deleteType(HttpSession session, long id) {
		ModelAndView mv = new ModelAndView();
		String viewName = "/types";
		if (!PowercoreApplication.isUserLoggedIn((User) session.getAttribute("user"))) {
			viewName = "login";
		} else {
			String message = "";
			Type type = typeDao.findById(id).orElse(null);
			if (type != null) {
				List<Powerplant> powerplant = powerplantDao.findAllByType(type);
				if (powerplant == null || powerplant.isEmpty()) {
					typeDao.delete(type);
					message = "Löschen erfolgreich!";
				} else {
					message = "Typ kann nicht gelöscht werden, weil er in Verwendung ist!";
				}
			} else {
				message = "Löschen fehlgeschlagen!";
			}
			mv.addObject("message", message);
			mv.addObject("types", typeDao.findAll());
		}
		mv.setViewName(viewName);
		return mv;
	}
}
