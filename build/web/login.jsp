<%-- 
    Document   : login
    Created on : 2016/08/12, 10:42:34
    Author     : maimaimai
--%>
<%@page import="jums.JumsHelper"%>
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
        <form action="LoginResult" method="POST">
            <label for="name">ユーザー名</label><input id="name" name="name"><br />
            <label for="password">パスワード</label><input id="password" name="password"><br />
            <input id="btnSubmit" type="submit" name="btnSubmit" value="ログイン"><br>
            <input type="hidden" name="ac"  value="<%= hs.getAttribute("ac")%>">
            </form>
            
        <form action="Registration" method="POST">
            <input id="btnSubmit" type="submit" name="btnSubmit2" value="新規会員登録">
            <input type="hidden" name="ac"  value="<%= hs.getAttribute("ac")%>">
        </form>
        
        <br><br>
        <div id="div"><%=jh.home()%></div>
    </body>
</html>
