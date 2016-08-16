<%-- 
    Document   : adminlogout
    Created on : 2016/08/15, 11:45:14
    Author     : maimaimai
--%>
<%@page import="jums.JumsHelper"%>
<%JumsHelper jh = JumsHelper.getInstance();%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>管理者ログアウト</title>
    </head>
    <body>
        管理者がログアウトしました。
        <div id="div"><%=jh.home()%></div>
    </body>
</html>