<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="freeboard/table.css">
</head>
<body>
  <center>
    <h3>내용보기</h3>
    <table id="table_content">
      <tr>
        <td width=20% class="tdcenter">번호</td>
        <td width=30% class="tdcenter">${no }</td>
        <td width=20% class="tdcenter">작성일</td>
        <td width=30% class="tdcenter">
          <fmt:formatDate value="${vo.regdate }" pattern="yyyy-MM-dd"/>
        </td>
      </tr>
      <tr>
        <td width=20% class="tdcenter">이름</td>
        <td width=30% class="tdcenter">${vo.name }</td>
        <td width=20% class="tdcenter">조회수</td>
        <td width=30% class="tdcenter">${vo.hit }</td>
      </tr>
      <tr>
        <td width=20% class="tdcenter">제목</td>
        <td colspan=3 class="tdleft">${vo.subject }</td>
      </tr>
      <tr>
        <td colspan=4 class="tdleft" valign="top" height=200>
          <pre>${vo.content }</pre>
        </td>
      </tr>
    </table>
    <table id="table_content">
    	<tr>
    		<td align="right">
    			<a href="update.do?no=${no }&page=${page }">
    			<img src="freeboard/image/btn_modify.gif" border="0"></a>
    			<img src="freeboard/image/btn_delete.gif" border="0">
    			<a href="list.do?page=${page }">
    			<img src="freeboard/image/btn_list.gif" border="0"></a>
    		</td>
    	</tr>
    </table>
  </center>
</body>
</html>




