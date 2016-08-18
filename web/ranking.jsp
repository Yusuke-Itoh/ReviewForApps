<%-- 
    Document   : ranking
    Created on : 2016/08/14, 23:06:41
    Author     : maimaimai
--%>
<%@page import="rfa.JumsHelper"%>
<%@page import="beans.DataBeans"%>
<%@page import="java.util.ArrayList"%>
<%
    JumsHelper jh = JumsHelper.getInstance();
    HttpSession hs = request.getSession();
    ArrayList<DataBeans> rankingList=(ArrayList<DataBeans>)hs.getAttribute("rankingList");
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
            <td>順位</td>
            <td>ユーザー名</td>
            <td>獲得ポイント</td>
            <%for(int i=0;i<rankingList.size();i++){%>
            <tr>
                <td><%=i+1%>位</td>   
                <td><%=rankingList.get(i).getName()%></td>
                <td><%=rankingList.get(i).getTotalPoints()%></td>
            </tr>
        <%}%>
        </table>
        <br>
        <%=jh.home()%>
    </body>
</html>
