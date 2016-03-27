package com.smt.threenationkill.domain;

import com.smt.threenationkill.enums.Role;
import com.smt.threenationkill.util.PointUtils;

import junit.framework.TestCase;

public class PointUtilsTest extends TestCase {
	public void testGetNumInGroup(){
		System.out.println(PointUtils.getNumInGroup(Role.ZHU_GONG, 7));
	}
	
	public void testPointSystem(){
		WinPercent wp = new WinPercent();
		WinPercentDetail detail = new WinPercentDetail(100, 70, 20, 10);
		detail.setPlayerNum(5);
		wp.addWinPercentDetail(detail);
		PointUtils.setWinPercent(wp);
		System.out.println(wp);
		Player p = new Player("Sun");
		GameStat stat = new GameStat(5);
		stat.updateByRole(Role.ZHU_GONG, true);
		stat.updateByRole(Role.ZHU_GONG, true);
		stat.updateByRole(Role.ZHU_GONG, true);
		stat.updateByRole(Role.ZHU_GONG, true);
		stat.updateByRole(Role.ZHU_GONG, true);
		stat.updateByRole(Role.ZHU_GONG, true);
		stat.updateByRole(Role.ZHU_GONG, true);
		stat.updateByRole(Role.ZHU_GONG, false);
		stat.updateByRole(Role.ZHU_GONG, false);
		stat.updateByRole(Role.ZHU_GONG, false);
		p.addGameStat(stat);
		System.out.println("Zhu Gong Points="+p.getPointsByRole(Role.ZHU_GONG));
		System.out.println("Zhong Chen Points="+p.getPointsByRole(Role.ZHONG_CHEN));
		System.out.println("Fan Zei Points="+p.getPointsByRole(Role.FAN_ZEI));
		System.out.println("Nei Jian Points="+p.getPointsByRole(Role.NEI_JIAN));
		System.out.println("Total Points="+p.getTotalPoints());
		
	}
	
}
