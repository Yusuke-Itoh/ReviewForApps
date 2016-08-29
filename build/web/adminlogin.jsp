<%-- 
    Document   : adminlogin
    Created on : 2016/08/15, 11:06:24
    Author     : maimaimai
--%>
<%@page import="rfa.JumsHelper"%>
<%
    JumsHelper jh = JumsHelper.getInstance();
    HttpSession hs = request.getSession();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
            <style>
            label,input 
            {
                display: block;
                width: 150px;
                float: left;
                margin-bottom: 10px;
            }
            label {
                text-align: right;
                padding-right: 15px;
            }
            br { clear: left; }
            #btnSubmit{
                margin-left: 170px;
            }
            #div{
                margin-left: 220px;
            }
            </style>
    </head>
    <body>
        <form action="AdminLoginResult" method="POST">
            <label for="admin">管理者名</label><input id="name" name="admin"><br />
            <label for="password">パスワード</label><input id="password" name="password"><br />
            <input id="btnSubmit" type="submit" name="btnSubmit" value="ログイン"><br>
            <input type="hidden" name="ac"  value="<%= hs.getAttribute("ac")%>">
        </form>
            *管理者以外はアクセスしないでくださいね。
        
        <br><br>
        <div id="div"><%=jh.home()%></div>
    </body>
</html>
