<%-- 
    Document   : mypage
    Created on : 2016/08/24, 14:12:14
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
        <title>JSP Page</title>
    </head>
    <body>
        ユーザー名：<%=ud.getName()%><br>
        メールアドレス：<%=ud.getMail()%><br>
        獲得ポイント：<%=ud.getTotalPoints()%><br>
        <br>
        <form action="MyDelete" method="POST">
            <input id="btnSubmit" type="submit" name="btnSubmit" value="アカウント削除画面へ">
            <input type="hidden" name="ac"  value="<%= hs.getAttribute("ac")%>">
        </form>
        <br><br>
        <%=jh.home()%>       
    </body>
    <jsp:include page="footer.jsp" flush="true" />
</html>
