package com.smt.threenationkill.domain;

import com.smt.threenationkill.enums.Role;

/**
 * This class represents a Role-Number of players in a game bean
 * @author Mingtao Sun
 */
public final class RolePlayerNumBean {
	/** The role played in a game (Zhu Gong/Zhong Chen/Nei Jian/Fan Zei)*/
	private final Role role;
	/** The number of players in a game */
	private final int playerNum;
	
	/**
	 * Constructor 
	 * @param role the role played in a game (Zhu Gong/Zhong Chen/Nei Jian/Fan Zei)
	 * @param playerNum the number of players in a game
	 */
	public RolePlayerNumBean(Role role, int playerNum) {
		super();
		this.role = role;
		this.playerNum = playerNum;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + playerNum;
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RolePlayerNumBean other = (RolePlayerNumBean) obj;
		if (playerNum != other.playerNum)
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		return true;
	}
}
