<%-- 
    Document   : footer
    Created on : 2016/08/15, 10:46:43
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
        <footer>
            <hr>
            <%if(ud == null && ad == null){%>
                <div style="display:inline-flex">
                <form action="AdminLogin" method="POST">
                <input type="submit" name="btnSubmit" value="管理者ログイン">
                </form>
                </div>
            <%}%>
        </footer>
