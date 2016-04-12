<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="search/table.css">
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
/*
 * 	jquery => selector
 		$(selector)
 		  ========
 			  1) 객체 => window, document, location, form
 			  $(window)
 			  2) 태그명
 			  $('tr') $("td")
 			  3) ID
 			  $('#aaa')
 			  4) class
 			  $('.aaa')
 			  
 			  => 윈도우(브라우저)에 화면이 로딩되었을 때
 			  	 window.onload=function() {
 				 ===
 			  }
 			  $(document).ready(function() {
 				  
 			  });
 			  $(function() {
 				  
 			  });
 */
 var i=0;
$(function() {
	$('.dataTr').click(function(){
		var id=$(this).attr("id");
		var no=id.substring(1);
		var tr=$('#m'+no); // document.getElementById("m"+no);
		if(i==0) {
			tr.show();
			i=1;
		}
		else {
			tr.hide();
			i=0;
		}
	});
	$('#findBtn').click(function(){
		var ss=$('#ss').val();
		if(ss=="") {
			$('#ss').focus();
			return;
		}
		$('#sfrm').submit();
	});
});
</script>
</head>
<body>
	<center>
		<h3>Naver News Search</h3>
		<table id="table_content">
			<form method="post" action="search.do" id="sfrm">
			<tr>
				<td class="tdleft">
				Search:<input type="text" name="title" size="20" id="ss">
				<input type="button" value="검색" id="findBtn">
				</td>
			</tr>
			</form>
		</table>
		<table id="table_content">
			<c:forEach var="vo" items="${list }">
				<tr class="dataTr" id="c${vo.no }">
					<td class="tdleft">
					<font color="blue"><b>${vo.title }</b></font>
					</td>
				</tr>
				<tr id="m${vo.no }" style="display:none">
					<td class="tdleft">
					<b>${vo.msg }</b>
					</td>
				</tr>
			</c:forEach>
		</table>
		<table id="table_content">
			<tr>
				<td align=right>
					<a href="search.do?title=${title }&page=${curpage>1?curpage-1:curpage}">이전</a>&nbsp;
					<a href="search.do?title=${title }&page=${curpage<totalpage?curpage+1:curpage}">다음</a>&nbsp;&nbsp;
					${curpage } page / ${totalpage } pages
				</td>
			</tr>
		</table>
	</center>
</body>
</html>