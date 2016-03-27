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
		<th>����</th>
		<th>����</th>
		<th>�ҳ�</th>
		<th>�ڼ�</th>
		<th>����</th>
		<th>�ܼ�</th>
	</tr>
	<c:forEach var="gameStat" items="${player.gameStats}">
		<tr>
			<td>${gameStat.playerNum}</td>
			<td>${gameStat.zhugongNumWon}ʤ-${gameStat.zhugongNumLost}��</td>
			<td>${gameStat.zhongchenNumWon}ʤ-${gameStat.zhongchenNumLost}��</td>
			<td>${gameStat.neijianNumWon}ʤ-${gameStat.neijianNumLost}��</td>
			<td>${gameStat.fanzeiNumWon}ʤ-${gameStat.fanzeiNumLost}��</td>
			<td>${gameStat.gameNumWon}ʤ-${gameStat.gameNumLost}��</td>
		</tr>
	</c:forEach>
	<tr>
		<td>�ܼ�</td>
		<td>${player.totalZhugongNumWon}ʤ-${player.totalZhugongNumLost}��</td>
		<td>${player.totalZhongchenNumWon}ʤ-${player.totalZhongchenNumLost}��</td>
		<td>${player.totalNeijianNumWon}ʤ-${player.totalNeijianNumLost}��</td>
		<td>${player.totalFanzeiNumWon}ʤ-${player.totalFanzeiNumLost}��</td>
		<td>${player.totalNumWon}ʤ-${player.totalNumLost}��</td>
	</tr>
</table>
<br/>
<a href="gameList.do">��Ϸ��¼</a> <a href="playerList.do">����б�</a>
