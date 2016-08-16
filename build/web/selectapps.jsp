<%-- 
    Document   : selectapps
    Created on : 2016/08/10, 14:03:29
    Author     : maimaimai
--%>
<%@page import="jums.JumsHelper"%>
<%@page import="jums.DataBeans"%>
<%@page import="java.util.ArrayList"%>
<%
    JumsHelper jh = JumsHelper.getInstance();
    HttpSession hs = request.getSession();
    ArrayList<DataBeans> appData=(ArrayList<DataBeans>)hs.getAttribute("appData");
    ArrayList<DataBeans> appInfo=(ArrayList<DataBeans>)hs.getAttribute("appInfo");
    DataBeans ad=(DataBeans)hs.getAttribute("ad");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>レビューアプリ選択ページ</title>
               <style>
table {
	border-collapse: collapse;
}
td {
	border: solid 1px;
	padding: 0.5em;
}
        </style>
    </head>
    <body>
        <table>
         <%for(int i=0;i<appData.size();i++){%>
        
            <tr>
                <td><img src="<%=appData.get(i).getImg()%>" alt=""></td>   
                <td><a href="WatchReview?appIDnum=<%=i+1%>"><%=appData.get(i).getAppName()%></a>
                    <br>
                    <%=appData.get(i).getPublisher()%>
                </td>
                <%if(ad != null){%>
                <td>
                    <form action="AdminAppDataDelete" method="POST">
                    <input id="btnSubmit" type="submit" name="btnSubmit" value="アプリを削除" onClick="disp()">
                    <input type="hidden" name="appID"  value="<%=appInfo.get(i).getAppID()%>">
                    </form>
                </td>
                <%}%>
            </tr>
        <%}%>
        </table>
        <br>
        <%=jh.home()%>
    </body>
</html>
