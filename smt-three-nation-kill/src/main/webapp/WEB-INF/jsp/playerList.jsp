<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>
<%@ taglib prefix="m" uri="resultTag" %>
<%@page pageEncoding="gb2312"%>

<h1>����б�</h1>

<table border="1">
	<tr>
		<th>����</th>
		<th>����</th>
		<th>�ҳ�</th>
		<th>�ڼ�</th>
		<th>����</th>
		<th>�ܼ�</th>
		<th>ʤ��</th>
	</tr>
	<c:forEach var="player" items="${players}">
		<tr>
			<td><a href="playerDetail.do?id=${player.id}">${player.name}</a></td>
			<td>${player.totalZhugongNumWon}ʤ-${player.totalZhugongNumLost}��</td>
			<td>${player.totalZhongchenNumWon}ʤ-${player.totalZhongchenNumLost}��</td>
			<td>${player.totalNeijianNumWon}ʤ-${player.totalNeijianNumLost}��</td>
			<td>${player.totalFanzeiNumWon}ʤ-${player.totalFanzeiNumLost}��</td>
			<td>${player.totalNumWon}ʤ-${player.totalNumLost}��</td>
			<td>${player.winRate}%</td>
		</tr>
	</c:forEach>
</table>

<a href="gameList.do">��Ϸ��¼</a>
