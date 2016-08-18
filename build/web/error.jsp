<%-- 
    Document   : error
    Created on : 2016/08/18, 13:21:31
    Author     : maimaimai
--%>
<%@page import="rfa.JumsHelper" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="header.jsp" flush="true" />
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>error</title>
    </head>
    <body>
        エラーが発生しました。以下の項目を確認してください。<br>
        <%=request.getAttribute("error")%><br>
        <%=JumsHelper.getInstance().home()%>
    </body>
</html>
