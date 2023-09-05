<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
.td_h {
	width:20px;
	padding:8px;
	white-space: nowrap;
}
input {
	padding:8px;
}
#input_content {
	
}
</style>
<meta charset="UTF-8">
<title>WriteForm.jsp</title>
</head>
<body>
<br>
<p>
<table width="700" cellpadding="0" cellspacing="0" border="1">
	<form action="write" method="post">
		<tr>
			<td class="td_h">작성자</td>
			<td><input type="text" name="writer" size="100"> </td>
		</tr>
		<tr>
			<td class="td_h">제목</td>
			<td><input type="text" name="title" size="100"> </td>
		</tr>
		<tr>
			<td class="td_h">내용</td>
			<td><input type="text" name="content" size="100"> </td>
		</tr>		
		<tr>
			<td colspan="2"><input type="submit" value="입력">
			&nbsp;&nbsp; <a href="list">목록보기</a> </td>
		</tr>
	</form>
</table>
</body>
</html>