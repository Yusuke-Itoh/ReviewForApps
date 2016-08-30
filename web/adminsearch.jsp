<%-- 
    Document   : adminsearch
    Created on : 2016/08/15, 14:43:24
    Author     : maimaimai
--%>
<%@page import="rfa.JumsHelper"%>
<%@page import="beans.DataBeans"%>
<%@page import="java.util.ArrayList"%>
<%
    JumsHelper jh = JumsHelper.getInstance();
    HttpSession hs = request.getSession();
    ArrayList<DataBeans> searchResult=(ArrayList<DataBeans>)hs.getAttribute("searchResult");
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
         <%for(int i=0;i<searchResult.size();i++){%>
        
            <tr>
                <td><img src="<%=searchResult.get(i).getImg()%>" alt=""></td>   
                <td>
                    <%=searchResult.get(i).getAppName()%>
                    <br>
                    <%=searchResult.get(i).getPublisher()%>
                </td>
                <td>
                    <form action="AdminAddAppConfirm" method="POST">
                    <input id="btnSubmit" type="submit" name="btnSubmit" value="追加ページへ">
                    <input type="hidden" value="<%=i%>" name="appNum">
                    </form>
                </td>
            </tr>
        <%}%>
        </table>
        <br>
        <%=jh.home()%>
    </body>
</html>
