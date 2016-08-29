<%-- 
    Document   : adminuserdatarestoration
    Created on : 2016/08/17, 11:35:12
    Author     : maimaimai
--%>
<%@page import="rfa.JumsHelper"%>
<%@page import="beans.DataBeans"%>
<%@page import="java.util.ArrayList"%>
<%
    JumsHelper jh = JumsHelper.getInstance();
    ArrayList<DataBeans> deletedUserInfo=(ArrayList<DataBeans>)session.getAttribute("deletedUserInfo");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="AdminUserDataRestorationComplete" method="POST">
        作品選択
            <select name="userID">
                <option value="0">ユーザーを選択してください。</option>
                <% for(int i=0;i<deletedUserInfo.size();i++){ %>
                <option value="<%=deletedUserInfo.get(i).getUserID()%>">ID:<%=deletedUserInfo.get(i).getUserID()%>,<%=deletedUserInfo.get(i).getName()%></option>
                <%}%>
            </select>
            <input type="submit" name="btnSubmit" value="復元する">
        </form>
        <br>
        <%=jh.home()%>
    </body>
</html>
