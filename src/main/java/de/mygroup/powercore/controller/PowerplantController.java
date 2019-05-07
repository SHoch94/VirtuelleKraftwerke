package de.mygroup.powercore.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.h2.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import de.mygroup.powercore.PowercoreApplication;
import de.mygroup.powercore.dao.PowerplantDao;
import de.mygroup.powercore.dao.TypeDao;
import de.mygroup.powercore.dao.VirtualPowerplantDao;
import de.mygroup.powercore.model.Powerplant;
import de.mygroup.powercore.model.Type;
import de.mygroup.powercore.model.User;

@Controller
public class PowerplantController {

	@Autowired
	PowerplantDao powerplantDao;

	@Autowired
	TypeDao typeDao;

	@Autowired
	VirtualPowerplantDao virtualPowerplantDao;

	private Map<Long, String> getTypeSelectData() {
		Iterable<Type> types = typeDao.findAll();
		Map<Long, String> typeMap = new HashMap<>();
		for (Type type : types) {
			typeMap.put(type.getId(), type.getName());
		}
		return typeMap;
	}

	@RequestMapping("/powerplants")
	public ModelAndView toPowerplants(HttpSession session) {
		ModelAndView mv;
		String viewName = "powerplants";
		if (!PowercoreApplication.isUserLoggedIn((User) session.getAttribute("user"))) {
			viewName = "login";
		}
		mv = new ModelAndView(viewName);
		setViewObjects(mv);
		return mv;
	}

	@RequestMapping("/powerplants/delete")
	public ModelAndView deletePowerplant(HttpSession session, long id) {
		ModelAndView mv;
		String viewName = "powerplants";
		String message = "";

		if (!PowercoreApplication.isUserLoggedIn((User) session.getAttribute("user"))) {
			viewName = "login";
		} else {
			Powerplant powerplant = powerplantDao.findById(id).orElse(null);
			if (powerplant == null) {
				message = "Kraftwerk nicht gefunden!";
			} else {
				powerplantDao.delete(powerplant);
				message = "Kraftwerk erfolgreich gelöscht!";
			}
		}
		mv = new ModelAndView(viewName);
		mv.addObject("message", message);
		setViewObjects(mv);
		return mv;
	}

	@RequestMapping("/powerplants/add")
	public ModelAndView addPowerplant(HttpSession session, String name, long type, double powerConversion,
			MultipartFile image, Date acquisition, String manufacturer, double price, String location,
			long runtimeInHours, long virtual) {
		ModelAndView mv = new ModelAndView();
		String viewName = "/powerplants";
		if (!PowercoreApplication.isUserLoggedIn((User) session.getAttribute("user"))) {
			viewName = "/login";
		} else {
			Powerplant powerplant = new Powerplant();
			byte[] imageBytes = new byte[0];
			System.out.println(image);
			if (image != null) {
				try {
					imageBytes = image.getBytes();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			setPowerplantData(name, type, powerConversion, imageBytes, acquisition, manufacturer, price, location,
					runtimeInHours, virtual, powerplant);
			powerplantDao.save(powerplant);
			setViewObjects(mv);
		}
		mv.setViewName(viewName);
		return mv;
	}

	@RequestMapping("/powerplants/edit")
	public ModelAndView edit(HttpSession session, long id) {
		ModelAndView mv = new ModelAndView();
		String viewName = "/editPowerplant";
		if (!PowercoreApplication.isUserLoggedIn((User) session.getAttribute("user"))) {
			viewName = "/login";
		} else {
			mv.addObject("powerplant", powerplantDao.findById(id).orElse(null));
		}
		mv.setViewName(viewName);
		mv.addObject("types", typeDao.findAll());
		mv.addObject("virtuals", virtualPowerplantDao.findAll());
		return mv;
	}

	@RequestMapping("/powerplants/save")
	public ModelAndView save(HttpSession session, long id, String name, long type, double powerConversion,
			MultipartFile image, int updateImage, Date acquisition, String manufacturer, double price, String location,
			long runtimeInHours, long virtual) {
		ModelAndView mv = new ModelAndView();
		String viewName = "/editPowerplant";
		if (!PowercoreApplication.isUserLoggedIn((User) session.getAttribute("user"))) {
			viewName = "/login";
		} else {
			String message = "Unbekannter Fehler!";
			Powerplant powerplant = powerplantDao.findById(id).orElse(null);
			if (powerplant != null) {
				byte[] imageBytes = new byte[0];
				if (updateImage == 1 && image != null) {
					try {
						imageBytes = image.getBytes();
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					imageBytes = powerplant.getImage();
				}
				setPowerplantData(name, type, powerConversion, imageBytes, acquisition, manufacturer, price, location,
						runtimeInHours, virtual, powerplant);
				powerplantDao.save(powerplant);
				message = "Erfolgreich gespeichert!";
				viewName = "/powerplants";
			}
			mv.addObject("message", message);
			setViewObjects(mv);
		}
		mv.setViewName(viewName);
		return mv;
	}

	@GetMapping("/powerplants/image/{id}")
	public void showProductImage(@PathVariable long id, HttpServletResponse response) throws IOException {
		response.setContentType("image/jpeg");
		Powerplant powerplant = powerplantDao.findById(id).orElse(null);
		if (powerplant != null && powerplant.getImage() != null) {
			InputStream is = new ByteArrayInputStream(powerplant.getImage());
			IOUtils.copy(is, response.getOutputStream());
		}
	}

	@InitBinder
	private void dateBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
		binder.registerCustomEditor(Date.class, editor);
	}

	private void setPowerplantData(String name, long type, double powerConversion, byte[] image, Date acquisition,
			String manufacturer, double price, String location, long runtimeInHours, long virtual,
			Powerplant powerplant) {
		powerplant.setType(typeDao.findById(type).orElse(new Type(0l, "Fehler")));
		powerplant.setName(name);
		powerplant.setPowerConversion(powerConversion);
		powerplant.setImage(image);
		powerplant.setAcquisition(acquisition);
		powerplant.setManufacturer(manufacturer);
		powerplant.setPrice(price);
		powerplant.setLocation(location);
		powerplant.setRuntimeInHours(runtimeInHours);
		powerplant.setVirtualPowerplant(virtualPowerplantDao.findById(virtual).orElse(null));
	}

	private void setViewObjects(ModelAndView mv) {
		mv.addObject("powerplants", powerplantDao.findAll());
		mv.addObject("typesMap", getTypeSelectData());
		mv.addObject("types", typeDao.findAll());
		mv.addObject("virtuals", virtualPowerplantDao.findAll());
	}
}