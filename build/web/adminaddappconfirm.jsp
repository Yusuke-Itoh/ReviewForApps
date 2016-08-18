<%-- 
    Document   : adminaddappconfirm
    Created on : 2016/08/15, 15:14:44
    Author     : maimaimai
--%>
<%@page import="beans.DataBeans"%>
<%@page import="rfa.JumsHelper"%>
<%
    JumsHelper jh = JumsHelper.getInstance();
    DataBeans appData = (DataBeans)session.getAttribute("addAppData");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="header.jsp" flush="true" />
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP Page</title>
        <style>
table {
	border-collapse: collapse;
}
td {
	border: solid 1px;
	padding: 0.5em;
}
        </style>
    </head>
<body>
    <table>
        <tr>
            <td><img src="<%=appData.getImg()%>" alt=""></td>
            <td><%=appData.getAppName()%><br>
                <%=appData.getPublisher()%><br>
                レビュー点数：<%=appData.getRate()%>
            </td>
        </tr>    
    </table>                    
            <br><br>
        <form action="AdminAddAppComplete" method="POST">
        <input type="submit" name="btnSubmit" value="追加">
        </form>
        <br>
        <a href="javascript:history.back();">戻る</a><br><br>
        <br><br>
        <%=jh.home()%>
</body>
</html>