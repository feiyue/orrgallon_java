<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String basePath = request.getServletContext().getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=basePath%>/static/bootstrap/css/bootstrap-theme.min.css"
	rel="stylesheet">
<link href="<%=basePath%>/static/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/static/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/static/easyui/themes/icon.css">

<script type="text/javascript"
	src="<%=basePath%>/static/bootstrap/js/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/static/bootstrap/js/bootstrap.min.js"></script>

<script type="text/javascript"
	src="<%=basePath%>/static/easyui/jquery.easyui.min.js"></script>
<title>explainplan</title>
<script type="text/javascript">
	
</script>
</head>
<jsp:include page="/pages/template/header.jsp"></jsp:include>
<body style="padding-top: 60px;">
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<table title="执行计划" class="easyui-datagrid" style="width: 99%;"
					data-options="
											data: <c:out value='${dcjsonlist}'/> ,
											rownumbers: true,
											singleSelect:true,
											idField: 'ID',
											treeField: 'OPERATION'
										">
					<thead>
						<tr>
							<th data-options="field:'OPERATION'" width="300">OPERATION</th>
							<th data-options="field:'NAME'" width="200">NAME</th>
							<th data-options="field:'ROWS'" width="50">ROWS</th>
							<th data-options="field:'BYTES'" width="50">BYTES</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>

</body>
</html>