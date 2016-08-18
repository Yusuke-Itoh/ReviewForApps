<%-- 
    Document   : mydelete
    Created on : 2016/08/17, 10:56:26
    Author     : maimaimai
--%>
<%@page import="beans.DataBeans"%>
<%@page import="rfa.JumsHelper"%>
<%
    JumsHelper jh = JumsHelper.getInstance();
    HttpSession hs = request.getSession();
    DataBeans ud = (DataBeans)session.getAttribute("ud");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="header.jsp" flush="true" />
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>削除確認ページ</title>
        <style>
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
        ユーザー名：<%=ud.getName()%><br />
        パスワード：<%=ud.getPassword()%><br />
        メールアドレス：<%=ud.getMail()%><br />
        <br>
        アカウントを削除しますか？
        <br><br>
        <form action="MyDeleteResult" method="POST">
        <input id="btnSubmit" type="submit" name="btnSubmit" value="はい">
        <input type="hidden" name="ac"  value="<%= hs.getAttribute("ac")%>">
        </form>
        <br>
        <form action="Index" method="POST">
        <input id="btnSubmit" type="submit" name="btnSubmit" value="いいえ">
        </form>
        <br><br>
        <%=jh.home()%>
    </body>
</html>
