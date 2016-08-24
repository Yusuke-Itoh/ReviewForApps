<%-- 
    Document   : logout
    Created on : 2016/08/19, 10:55:54
    Author     : maimaimai
--%>
<%@page import="rfa.JumsHelper"%>
<%JumsHelper jh = JumsHelper.getInstance();%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ログアウト</title>
    </head>
    <body>
        <p>ログアウトしました</p>
        <div id="div"><%=jh.home()%></div>
    </body>
</html>
