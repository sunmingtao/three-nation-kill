package com.smt.threenationkill.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.smt.threenationkill.domain.Player;
import com.smt.threenationkill.service.ThreeNationKillService;


public class PlayerDetailController extends AbstractController {

	Logger logger = Logger.getLogger(PlayerDetailController.class);

	private ThreeNationKillService service;
	
	public void setService(ThreeNationKillService service) {
		this.service = service;
	}

	protected ModelAndView handleRequestInternal(HttpServletRequest req,
			HttpServletResponse res) throws Exception {
//		String name = new String(req.getParameter("name").getBytes("ISO-8859-1"),"GB2312");
		String id = req.getParameter("id");
		logger.info("id: "+ id);
		Player player = service.getPlayerById(new Long(id));
		ModelAndView mav = new ModelAndView("playerDetail");
		mav.addObject("player", player);
		return mav;
	}
	
	

}
