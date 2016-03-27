package com.smt.threenationkill.domain;

import com.smt.threenationkill.enums.Role;

import junit.framework.TestCase;

public class PlayerTest extends TestCase{
	public void testGameStat(){
		Player player = new Player("laosun");
		assertEquals("laosun", player.getName());
		GameStat gameStat = new GameStat(5);
		gameStat.updateByRole(Role.ZHU_GONG, true);
		gameStat.updateByRole(Role.ZHU_GONG, true);
		gameStat.updateByRole(Role.ZHU_GONG, false);
		player.addGameStat(gameStat);
		assertEquals(3, player.getGameNumPlayedByRole(Role.ZHU_GONG));
		assertEquals(2, player.getGameNumWonByRole(Role.ZHU_GONG));
	}
}
