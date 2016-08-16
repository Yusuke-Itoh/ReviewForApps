<%-- 
    Document   : index
    Created on : 2016/08/09, 17:10:58
    Author     : maimaimai
--%>
<%@page import="jums.DataBeans"%>
<%
    HttpSession hs = request.getSession();
    DataBeans ud = (DataBeans)hs.getAttribute("ud");
    DataBeans ad = (DataBeans)hs.getAttribute("ad");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
       <jsp:include page="header.jsp" flush="true" />
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ReviewForApp</title>
    </head>
    <body>
        <form action="SelectApps" method="POST">
        <input id="btnSubmit" type="submit" name="btnSubmit" value="レビューを見る">
        </form>
        <%if(ud != null){%>
        <br>
        <br>
        <form action="WriteReview" method="POST">
        <input id="btnSubmit" type="submit" name="btnSubmit" value="レビューを書く">
        </form>
        <br><br>
        <form action="Ranking" method="POST">
        <input id="btnSubmit" type="submit" name="btnSubmit" value="ランキング">
        </form>
        <%}%>
        <br>
        <!--管理者用-->
        <%if(ad != null){%>
        <br>
        <br>
        <form action="AdminSearch" method="GET">
        検索キーワード入力:<input type="text" name="term" value="">      
        <input type="submit" name="btnSubmit" value="検索">
        </form>
        <%}%>       
    </body>
    　　<jsp:include page="footer.jsp" flush="true" />
</html>
