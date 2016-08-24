<%-- 
    Document   : header
    Created on : 2016/08/12, 10:45:50
    Author     : maimaimai
--%>

<%@page import="beans.DataBeans"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    HttpSession hs = request.getSession();
    DataBeans ud = (DataBeans) hs.getAttribute("ud");
    DataBeans ad = (DataBeans) hs.getAttribute("ad");
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<header>
        <%if(ud == null && ad != null){%>
            <div style="display:inline-flex">
            管理者<%=ad.getName()%>さん。ただいまログイン中です。
            <form action="AdminLogin" method="POST">
            <input type="submit" name="btnSubmit" value="管理者ログアウト">
            </form>
            </div>
            <hr>
        <%}%>        
        <%if(ud == null && ad == null){%>
            <div style="display:inline-flex">
            <form action="Login" method="POST">
            <input type="submit" name="btnSubmit" value="ログイン">
            </form>
            </div>
            <hr>
       
        <%}if(ud != null){%>
            <div style="display:inline-flex">
            こんにちは、<%=ud.getName()%>さん。ただいまログイン中です。
            <form action="Login" method="POST">
            <input type="submit" name="btnSubmit" value="ログアウト">
            </form>
            </div>
            <hr>
        <%}%>
        
</header>
