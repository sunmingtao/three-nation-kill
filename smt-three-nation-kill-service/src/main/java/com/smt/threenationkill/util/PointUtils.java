package com.smt.threenationkill.util;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.smt.threenationkill.domain.RolePlayerNumBean;
import com.smt.threenationkill.domain.WinPercent;
import com.smt.threenationkill.enums.Result;
import com.smt.threenationkill.enums.Role;
import com.smt.threenationkill.exception.InvalidPlayerNumException;

/**
 * This class contains utility methods to calculate the points
 * @author Mingtao Sun
 */
public class PointUtils {
	/**
	 * The map containing the number of players in the group.
	 * e.g. For a 5 person game, the number of players for a Zhu Gong group is 2 (Zhu Gong, Zhong Chen)
	 * Key: RolePlayerNumBean object (String role, int playerNum)
	 * Value: the number of players in the group
	 */
	private final static Map<RolePlayerNumBean, Integer> GROUP_NUM = new HashMap<RolePlayerNumBean, Integer>();
	
	/** League's average win percent */
	private static WinPercent winPercent;
	
	static{
		GROUP_NUM.put(new RolePlayerNumBean(Role.ZHU_GONG, 5), 2);
		GROUP_NUM.put(new RolePlayerNumBean(Role.ZHU_GONG, 6), 2);
		GROUP_NUM.put(new RolePlayerNumBean(Role.ZHU_GONG, 7), 3);
		GROUP_NUM.put(new RolePlayerNumBean(Role.ZHU_GONG, 8), 3);
		GROUP_NUM.put(new RolePlayerNumBean(Role.NEI_JIAN, 5), 1);
		GROUP_NUM.put(new RolePlayerNumBean(Role.NEI_JIAN, 6), 1);
		GROUP_NUM.put(new RolePlayerNumBean(Role.NEI_JIAN, 7), 1);
		GROUP_NUM.put(new RolePlayerNumBean(Role.NEI_JIAN, 8), 1);
		GROUP_NUM.put(new RolePlayerNumBean(Role.FAN_ZEI, 5), 2);
		GROUP_NUM.put(new RolePlayerNumBean(Role.FAN_ZEI, 6), 3);
		GROUP_NUM.put(new RolePlayerNumBean(Role.FAN_ZEI, 7), 3);
		GROUP_NUM.put(new RolePlayerNumBean(Role.FAN_ZEI, 8), 4);
	}
	
	/**
	 * Checks if the player number is valid for the point system
	 * @param playerNum the player number to check
	 * @return true if the player number is valid for the point system
	 */
	public static boolean isPlayerNumValid(int playerNum){
		//9,10 person game is considered invalid as too little data is available.
		return playerNum >= 5 && playerNum <= 8;
	}
	
	/**
	 * Returns the player number in group based on role and player number
	 * @param role the role played in a game (Zhu Gong/Zhong Chen/Nei Jian/Fan Zei)
	 * @param playerNum the player number
	 * @return the player number in group based on role and player number
	 */
	public static int getNumInGroup(Role role, int playerNum){
		if (role == Role.ZHONG_CHEN){
			role = Role.ZHU_GONG;
		}
		if (role != Role.ZHU_GONG && role != Role.NEI_JIAN && role != Role.FAN_ZEI) {
			throw new IllegalArgumentException("Role is invalid:"+role);
		}
		if (!isPlayerNumValid(playerNum)){
			throw new InvalidPlayerNumException("Player number is out of range for point system");
		}
		return GROUP_NUM.get(new RolePlayerNumBean(role, playerNum));
	}
	
	/**
	 * Returns the league's average win rate based on role and player number
	 * @param role the role played in a game (Zhu Gong/Zhong Chen/Nei Jian/Fan Zei)
	 * @param playerNum the player number
	 * @return the league's average win rate
	 */
	public static BigDecimal getAverageWinRate(Role role, int playerNum){
		if (winPercent == null){
			throw new IllegalStateException("The league average win percent has not been calculated");
		}
		if (!isPlayerNumValid(playerNum)){
			throw new InvalidPlayerNumException("Player number is out of range for point system");
		}
		return winPercent.getWinPercentDetail(playerNum).getWonPercent(role);
	}
	
	/**
	 * Calculates and returns the points earned based on the role, number of players, and the result
	 * If lost, then returns the points lost
	 * @param role the role played in a game (Zhu Gong/Zhong Chen/Nei Jian/Fan Zei)
	 * @param playerNum the player number
	 * @param result
	 * @return the points earned/lost
	 */
	public static BigDecimal earnPoints(Role role, int playerNum, Result result){
		try{
			BigDecimal winRate = getAverageWinRate(role, playerNum);
			int playerNumInGroup = getNumInGroup(role, playerNum);
			if (result == Result.WON){
				return CalculateUtils.divide((new BigDecimal(100)).subtract(winRate), playerNumInGroup);
			}else if(result == Result.LOST){
				return CalculateUtils.divide(BigDecimal.ZERO.subtract(winRate), playerNumInGroup);
			}	
			throw new IllegalArgumentException("Game result is invalid:"+result);
		}catch(InvalidPlayerNumException e){
			return BigDecimal.ZERO;
		}
	}
	
	/**
	 * Returns the league's average win percent
	 * @return the league's average win percent
	 */
	public static synchronized WinPercent getWinPercent(){
		return winPercent;
	}
	
	/**
	 * Sets the league's average win percent
	 * @param wp the league's average win percent to set
	 */
	public static synchronized void setWinPercent(WinPercent wp){
		winPercent = wp;
	}
}
