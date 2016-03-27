package com.smt.threenationkill.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import com.smt.threenationkill.domain.GamePO;
import com.smt.threenationkill.domain.PlayerDetail;

/**
 * This classes contains methods to handle game IO
 * @author Mingtao Sun
 */
public class GameLoader {
	
	/** Logger */
	private static Logger logger = Logger.getLogger(GameLoader.class);
	
	/**
	 * Loads games from a file and returns a list of GamePO
	 * @param file the file that contains game data 
	 * @return a list of GamePO
	 */
	public static List<GamePO> loadFromFile(File file){
		List<GamePO> games = new ArrayList<GamePO>();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String line = null;
		try {
			BufferedReader in = new BufferedReader(new FileReader(file));
			while ((line = in.readLine()) != null) {
				GamePO game = new GamePO();
				StringTokenizer st = new StringTokenizer(line);
				String dateStr = st.nextToken();
				game.setDate(sf.parse(dateStr));
				game.setPlayerNum(Integer.parseInt(st.nextToken()));
				PlayerDetail playerDetail = new PlayerDetail();
				playerDetail.setZhugong(st.nextToken());
				playerDetail.setZhongchen(getPlayers(st.nextToken()));
				playerDetail.setNeijian(getPlayers(st.nextToken()));
				playerDetail.setFanzei(getPlayers(st.nextToken()));
				game.setPlayerDetail(playerDetail);
				game.setResult(st.nextToken());
				if (!game.validate()){
					logger.fatal("Found invalid game data="+game);
				}else{
					games.add(game);	
				}
			}
		} catch (FileNotFoundException e){
			logger.fatal("File Not Found:"+file);
			throw new RuntimeException("File Not Found"+file);
		} catch (Exception e) {
			logger.fatal("Invalid File Format"+line);
			throw new RuntimeException("Invalid File Format"+line);
		}
		return games;
	}
	
	/**
	 * Parses a name string and returns a list of player names
	 * e.g. getPlayers("Zhao/Qian/Sun") = {"Zhao", "Qian", "Sun"}
	 * @param names a name string
	 * @return a list of player names
	 */
	private static List<String> getPlayers(String names){
		List<String> players = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(names, "/");
		while(st.hasMoreTokens()){
			players.add(st.nextToken());
		}
		return players;
	}
}
