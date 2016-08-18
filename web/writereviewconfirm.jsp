<%-- 
    Document   : writereviewconfirm
    Created on : 2016/08/11, 6:51:19
    Author     : maimaimai
--%>
<%@page import="rfa.JumsHelper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="beans.DataBeans"%>
<%
    JumsHelper jh = JumsHelper.getInstance();
    HttpSession hs = request.getSession();
    DataBeans reviewInfo = (DataBeans)hs.getAttribute("reviewInfo");
    ArrayList<DataBeans> appInfo=(ArrayList<DataBeans>)hs.getAttribute("appInfo");
    ArrayList<String> chkList = reviewInfo.chkRev();
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% if(chkList.size() == 0){ %>
        <h2>登録確認</h2>
        アプリ名:<%= appInfo.get(reviewInfo.getAppID() -1).getAppName()%><br>
        タイトル:<%= reviewInfo.getReviewTitle()%><br>
        本文:<%= reviewInfo.getReview()%><br>
        上記の内容で登録します。よろしいですか？
        <br><br>
        <form action="WriteReviewResult" method="POST">
            <input type="hidden" name="ac"  value="<%= hs.getAttribute("ac")%>">
            <input type="submit" name="yes" value="はい">
        </form><% }else{ %>
        <h1>入力が不完全です。</h1>
        <%=jh.chkInputRev(chkList) %><br>
        <% } %><br>
        <form action="WriteReview" method="POST">
            <input type="submit" name="no" value="登録画面に戻る">
            <input type="hidden" name="mode" value="REINPUT">
            <input type="hidden" name="ac"  value="<%= hs.getAttribute("ac")%>">
        </form>
            <br><br>
        <%=jh.home()%>
            
    </body>
</html>
