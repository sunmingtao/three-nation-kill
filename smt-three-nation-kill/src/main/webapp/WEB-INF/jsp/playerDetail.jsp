<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>
<%@ taglib prefix="m" uri="resultTag" %>
<%@page pageEncoding="gb2312"%>

<table>
	<tr>
		<td><m:photo id="${player.id}"/></td>
		<td><h1>${player.name}</h1></td>
	</tr>
</table>

<br/>

<table border="1">
	<tr>
		<th>人数</th>
		<th>主公</th>
		<th>忠臣</th>
		<th>内奸</th>
		<th>反贼</th>
		<th>总计</th>
	</tr>
	<c:forEach var="gameStat" items="${player.gameStats}">
		<tr>
			<td>${gameStat.playerNum}</td>
			<td>${gameStat.zhugongNumWon}胜-${gameStat.zhugongNumLost}负</td>
			<td>${gameStat.zhongchenNumWon}胜-${gameStat.zhongchenNumLost}负</td>
			<td>${gameStat.neijianNumWon}胜-${gameStat.neijianNumLost}负</td>
			<td>${gameStat.fanzeiNumWon}胜-${gameStat.fanzeiNumLost}负</td>
			<td>${gameStat.gameNumWon}胜-${gameStat.gameNumLost}负</td>
		</tr>
	</c:forEach>
	<tr>
		<td>总计</td>
		<td>${player.totalZhugongNumWon}胜-${player.totalZhugongNumLost}负</td>
		<td>${player.totalZhongchenNumWon}胜-${player.totalZhongchenNumLost}负</td>
		<td>${player.totalNeijianNumWon}胜-${player.totalNeijianNumLost}负</td>
		<td>${player.totalFanzeiNumWon}胜-${player.totalFanzeiNumLost}负</td>
		<td>${player.totalNumWon}胜-${player.totalNumLost}负</td>
	</tr>
</table>
<br/>
<a href="gameList.do">游戏记录</a> <a href="playerList.do">玩家列表</a>
