package com.smt.threenationkill.web;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.smt.threenationkill.domain.Game;
import com.smt.threenationkill.service.ThreeNationKillService;


public class GameListController extends AbstractController {

	Logger logger = Logger.getLogger(GameListController.class);

	private ThreeNationKillService service;
	
	public void setService(ThreeNationKillService service) {
		this.service = service;
	}

	protected ModelAndView handleRequestInternal(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		Resource resource = new ClassPathResource("test.txt");
		File file = null;
		try {
			file = resource.getFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView("gameList");
		List<Game> games= service.getAllGames();
		if (games == null || games.size() == 0){
			games= service.loadAndProcessGamesFromFile(file);
		}
		mav.addObject("games", games);
		return mav;
	}

}
