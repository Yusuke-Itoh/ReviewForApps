<%-- 
    Document   : adminaddappcomplete
    Created on : 2016/08/15, 16:13:53
    Author     : maimaimai
--%>
<%@page import="rfa.JumsHelper"%>
<%
    JumsHelper jh = JumsHelper.getInstance();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>管理者アプリ追加成功ページ</title>
    </head>
    <body>
        アプリが追加されました！
            <br><br>
        <%=jh.home()%>
    </body>
</html>
