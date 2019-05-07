package de.mygroup.powercore.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import de.mygroup.powercore.PowercoreApplication;
import de.mygroup.powercore.dao.PowerplantDao;
import de.mygroup.powercore.dao.VirtualPowerplantDao;
import de.mygroup.powercore.model.Powerplant;
import de.mygroup.powercore.model.User;
import de.mygroup.powercore.model.VirtualPowerplant;

@Controller
public class VirtualsController {

	@Autowired
	VirtualPowerplantDao virtualPowerplantDao;

	@Autowired
	PowerplantDao powerplantDao;

	@RequestMapping("/virtuals")
	public ModelAndView toVirtuals(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		String viewName = "/virtualPowerplants";
		if (!PowercoreApplication.isUserLoggedIn((User) session.getAttribute("user"))) {
			viewName = "login";
		} else {
			addViewObjects(mv);
		}
		mv.setViewName(viewName);
		return mv;
	}

	private void addViewObjects(ModelAndView mv) {
		Map<Long, Long> powerMap = new HashMap<>();
		List<Powerplant> powerplants = powerplantDao.findAll();
		List<VirtualPowerplant> virtualPowerplants = virtualPowerplantDao.findAll();
		for (VirtualPowerplant virtualPowerplant : virtualPowerplants) {
			long powerSum = 0;
			for (Powerplant powerplant : powerplants) {
				if (powerplant.getVirtualPowerplant() != null
						&& virtualPowerplant.getId() == powerplant.getVirtualPowerplant().getId()) {
					powerSum += powerplant.getPowerConversion();
				}
			}
			powerMap.put(virtualPowerplant.getId(), powerSum);
		}
		mv.addObject("virtualPowerplants", virtualPowerplants);
		mv.addObject("overallPower", powerMap);
	}

	@RequestMapping("/virtuals/add")
	public ModelAndView addVirtualPowerPlant(HttpSession session, String name) {
		ModelAndView mv = new ModelAndView();
		String message = "";
		String viewName = "virtualPowerplants";
		if (!PowercoreApplication.isUserLoggedIn((User) session.getAttribute("user"))) {
			viewName = "login";
		} else {
			if (null != name && name.length() > 0) {
				VirtualPowerplant vPp = new VirtualPowerplant();
				vPp.setName(name);
				virtualPowerplantDao.save(vPp);
				message = "Erfolgreich hinzugefügt!";
			} else {
				message = "Bitte Namen eingeben!";
			}
			mv.addObject("message", message);
			addViewObjects(mv);
		}
		mv.setViewName(viewName);
		return mv;
	}

	@RequestMapping("/virtuals/delete")
	public ModelAndView deleteVirtualPowerplant(HttpSession session, long id) {
		ModelAndView mv = new ModelAndView();
		String message = "Unbekannter Fehler!";
		String viewName = "virtualPowerplants";
		if (!PowercoreApplication.isUserLoggedIn((User) session.getAttribute("user"))) {
			viewName = "login";
		} else {
			VirtualPowerplant vPp = virtualPowerplantDao.findById(id).orElse(null);
			if (vPp != null) {
				List<Powerplant> powerplants = powerplantDao.findAllByVirtualPowerplant(vPp);
				if (null == powerplants || powerplants.isEmpty()) {
					virtualPowerplantDao.delete(vPp);
					message = "Erfolgreich gelöscht!";
				} else {
					message = "Virtuelles Kraftwerk ist noch in Verwendung!";
				}
			}
			mv.addObject("message", message);
			addViewObjects(mv);
		}
		mv.setViewName(viewName);
		return mv;
	}
}
