<%-- 
    Document   : registrationconfirm
    Created on : 2016/08/12, 11:11:50
    Author     : maimaimai
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="beans.DataBeans"%>
<%@page import="rfa.JumsHelper"%>
<%
    JumsHelper jh = JumsHelper.getInstance();
    HttpSession hs = request.getSession();
    DataBeans newAc = (DataBeans)hs.getAttribute("newAc");
    ArrayList<String> chkList = newAc.chkproperties();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>登録確認ページ</title>
    </head>
    <body>
        <% if(chkList.size()==0){ %>
            <h1>登録確認</h1>
            名前:<%= newAc.getName()%><br>
            パスワード:<%= newAc.getPassword()%><br>
            メールアドレス:<%= newAc.getMail()%><br>
            上記の内容で登録します。よろしいですか？
            <form action="RegistrationComplete" method="POST">
                <input type="submit" name="yes" value="はい">
                <input type="hidden" name="ac"  value="<%= hs.getAttribute("ac")%>">
            </form>
        <% }else{ %>
            <h1>入力が不完全です。</h1>
            <%=jh.chkInputAcc(chkList) %><br>
        <% } %><br>
        <form action="Registration" method="POST">
            <input type="submit" name="no" value="登録画面に戻る">
            <input type="hidden" name="mode" value="REINPUT">
            <input type="hidden" name="ac"  value="<%= hs.getAttribute("ac")%>">
        </form>
        <br><br>
        <%=jh.home()%>
    </body>
</html>