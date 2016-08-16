<%-- 
    Document   : adminappdatadelete
    Created on : 2016/08/16, 14:32:27
    Author     : maimaimai
--%>
<%@page import="jums.JumsHelper"%>
<%
    JumsHelper jh = JumsHelper.getInstance();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>アプリ削除完了</title>
    </head>
    <body>
        削除が完了しました。
            <br><br>
        <%=jh.home()%>
    </body>
</html>
