<%-- 
    Document   : admindeletedappdatarestoration
    Created on : 2016/08/16, 15:22:39
    Author     : maimaimai
--%>
<%@page import="jums.JumsHelper"%>
<%@page import="jums.DataBeans"%>
<%@page import="java.util.ArrayList"%>
<%
    JumsHelper jh = JumsHelper.getInstance();
    ArrayList<DataBeans> deletedAppInfo=(ArrayList<DataBeans>)session.getAttribute("deletedAppInfo");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="AdminDeletedAppDataRestorationComplete" method="POST">
        作品選択
        <select name="appID">
            <option value="0">作品名を選択してください。</option>
            <% for(int i=0;i<deletedAppInfo.size();i++){ %>
            <option value="<%=deletedAppInfo.get(i).getAppID()%>"><%=deletedAppInfo.get(i).getAppName()%></option>
            <%}%>
        </select>
        <input type="submit" name="btnSubmit" value="復元する">
        </form>
        <br>
        <%=jh.home()%>
    </body>
</html>
