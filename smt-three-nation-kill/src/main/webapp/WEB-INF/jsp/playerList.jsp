<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>
<%@ taglib prefix="m" uri="resultTag" %>
<%@page pageEncoding="gb2312"%>

<h1>玩家列表</h1>

<table border="1">
	<tr>
		<th>名字</th>
		<th>主公</th>
		<th>忠臣</th>
		<th>内奸</th>
		<th>反贼</th>
		<th>总计</th>
		<th>胜率</th>
	</tr>
	<c:forEach var="player" items="${players}">
		<tr>
			<td><a href="playerDetail.do?id=${player.id}">${player.name}</a></td>
			<td>${player.totalZhugongNumWon}胜-${player.totalZhugongNumLost}负</td>
			<td>${player.totalZhongchenNumWon}胜-${player.totalZhongchenNumLost}负</td>
			<td>${player.totalNeijianNumWon}胜-${player.totalNeijianNumLost}负</td>
			<td>${player.totalFanzeiNumWon}胜-${player.totalFanzeiNumLost}负</td>
			<td>${player.totalNumWon}胜-${player.totalNumLost}负</td>
			<td>${player.winRate}%</td>
		</tr>
	</c:forEach>
</table>

<a href="gameList.do">游戏记录</a>
