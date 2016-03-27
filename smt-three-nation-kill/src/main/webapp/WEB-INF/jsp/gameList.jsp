<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>
<%@ taglib prefix="m" uri="resultTag" %>
<%@page pageEncoding="gb2312"%>

<h1>游戏记录</h1>

<table border="1">
	<tr>
		<th>ID</th>
		<th>日期</th>
		<th>主公</th>
		<th>忠臣</th>
		<th>内奸</th>
		<th>反贼</th>
		<th>结果</th>
	</tr>
	<c:forEach var="game" items="${games}">
		<tr>
			<td>${game.id}</td>
			<td><fmt:formatDate value="${game.date}" pattern="yyyy/MM/dd"/></td>
			<td>${game.zhugong.name}</td>
			<td>${game.zhongchen[0].name}</td>
			<td>${game.neijian[0].name}</td>
			<td>
				<c:forEach var="fanzei" items="${game.fanzei}">
					${fanzei.name}
				</c:forEach>
			</td>
			<td><m:gameResult name="${game.result}"/></td>
		</tr>
	</c:forEach>
</table>
<a href="playerList.do">玩家列表</a>