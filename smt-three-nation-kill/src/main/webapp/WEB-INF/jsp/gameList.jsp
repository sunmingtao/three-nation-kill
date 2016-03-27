<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>
<%@ taglib prefix="m" uri="resultTag" %>
<%@page pageEncoding="gb2312"%>

<h1>��Ϸ��¼</h1>

<table border="1">
	<tr>
		<th>ID</th>
		<th>����</th>
		<th>����</th>
		<th>�ҳ�</th>
		<th>�ڼ�</th>
		<th>����</th>
		<th>���</th>
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
<a href="playerList.do">����б�</a>