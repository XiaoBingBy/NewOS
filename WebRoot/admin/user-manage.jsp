<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" type="text/css" href="../css/common.css"/>
		<style type="text/css">
			body {
				font-family: 楷体;
			}
			.head-manage {
				font-size: 40px;
				text-align: center;
				margin-top: 20px;
			}
			.show-info {
				margin-top: 15px;
				font-size: 20px;
			}
		</style>
	</head>
	<body>
		<jsp:include page="/userManageServlet"></jsp:include>
		<h3 class="head-manage">帐号管理</h3>
		<div class="show-info" align="center">
			<table border="1">
				<tr>
					<th>编 号</th>
					<th>帐 号</th>
					<th>密 码</th>
					<th>性 别</th>
					<th>职 业</th>
					<th>爱 好</th>
					<th>个人描述</th>
					<th>类  型</th>
					<th colspan="2">管 理</th>
				</tr>
				<c:forEach items="${userAll }" var="userAll">
					<tr>	
						<td>${userAll.id }</td>
						<td>${userAll.username }</td>
						<td>${userAll.password }</td>
						<td>${userAll.sex }</td>
						<td>${userAll.profession }</td>
						<td>${userAll.favourite }</td>
						<td>${userAll.note }</td>
						<td>${userAll.type }</td>
						<td>
							<button onclick="upUser('${userAll.id }')">修改</button>
						</td>
						<td>
							<button onclick="delUser('${userAll.id }')">删除</button>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<script type="text/javascript">
			
			function delUser(id) {
				
				var r=confirm("是否删除");
				if (r==true)
				{
				   // x="你按下了\"确定\"按钮!";
				    location.href = "${pageContext.request.contextPath }/deleteUserOne?id="+id;
				}
				else
				{
				    //x="你按下了\"取消\"按钮!";
				}
			}
			function upUser(id) {
				location.href = "${pageContext.request.contextPath }/admin/update-info.jsp?id="+id;
			}
		</script>
	</body>
</html>
