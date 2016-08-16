<%-- 
    Document   : registrationcomplete
    Created on : 2016/08/12, 11:50:01
    Author     : maimaimai
--%>
<%@page import="jums.JumsHelper"%>
<%@page import="jums.DataBeans"%>
<%
    JumsHelper jh = JumsHelper.getInstance();
    DataBeans ud = (DataBeans)request.getAttribute("ud");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>登録結果画面</title>
    </head>
    <body>
        <h2>登録結果</h2><br>
        名前:<%= ud.getName()%><br>
        パスワード:<%= ud.getPassword()%><br>
        メールアドレス:<%= ud.getMail()%><br>
        以上の内容で登録しました。<br><br>

        <h3><%=jh.home()%></h3>
    </body>
</html>
