<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="freeboard/table.css">
</head>
<body>
	<center>
		<h3>�۾���</h3>
		<table id="table_content">
			<tr>
				<td align="right" width="15%">�̸�</td>
				<td align="left" width="85%">
					<input type="text" name="name" size="15">
				</td>
			</tr>
			<tr>
				<td align="right" width="15%">����</td>
				<td align="left" width="85%">
					<input type="text" name="name" size="50">
				</td>
			</tr>
			<tr>
				<td align="right" width="15%">����</td>
				<td align="left" width="85%">
					<textarea rows="10" cols="55" name=content></textarea>
				</td>
			</tr>
			<tr>
				<td align="right" width="15%">��й�ȣ</td>
				<td align="left" width="85%">
					<input type="password" name="pwd" size="10">
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					<input type="button" value="�۾���">
					<input type="button" value="���"
					onclick="javascript:history.back()">
				</td>
			</tr>
		</table>
	</center>
</body>
</html>