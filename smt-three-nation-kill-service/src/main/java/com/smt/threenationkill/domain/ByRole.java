package com.smt.threenationkill.domain;

import java.math.BigDecimal;

import com.smt.threenationkill.enums.Role;

public interface ByRole {
	public int getGameNumPlayedByRole(Role role);
	public int getGameNumWonByRole(Role role);
	public BigDecimal getPointsByRole(Role role);
}
