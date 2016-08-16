<%-- 
    Document   : admindeleteoldreviews
    Created on : 2016/08/16, 13:21:55
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
        <title>旧レビュー削除完了ページ</title>
    </head>
    <body>
        削除が完了しました！
        <br><br>
        <%=jh.home()%>
    </body>
</html>
