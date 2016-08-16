<%-- 
    Document   : registration
    Created on : 2016/08/12, 11:11:32
    Author     : maimaimai
--%>
<%@page import="jums.DataBeans"%>
<%@page import="jums.JumsHelper"%>
<%JumsHelper jh = JumsHelper.getInstance();
    HttpSession hs = request.getSession();
    DataBeans ud = null;
  boolean reinput = false;
  if(request.getParameter("mode") != null && request.getParameter("mode").equals("REINPUT")){
      reinput = true;
      ud = (DataBeans)hs.getAttribute("ud");
    }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>会員登録ページ</title>
        <style>
            label,input 
            {
                display: block;
                width: 150px;
                float: left;
                margin-bottom: 10px;
            }
            label {
                text-align: right;
                padding-right: 15px;
            }
            br { clear: left; }
            #btnSubmit{
                margin-left: 170px;
            }
            #div{
                margin-left: 220px;
            }
        </style>
    </head>
    <body>
        <form action="RegistrationConfirm" method="POST">
            <label for="name">ユーザー名</label><input id="name" name="name" value="<% if(reinput){out.print(ud.getName());}%>"><br />
            <label for="password">パスワード</label><input id="password" name="password" value="<% if(reinput){out.print(ud.getPassword());}%>"><br />
            <label for="mail">メールアドレス</label><input id="mailaddress" name="mail" value="<% if(reinput){out.print(ud.getMail());}%>"><br />
            <br>
        <input id="btnSubmit" type="submit" name="btnSubmit" value="確認画面へ">
        <input type="hidden" name="ac"  value="<%= hs.getAttribute("ac")%>">
        </form>
        <br><br>
        <div id="div"><%=jh.home()%></div>
    </body>
    
</html>
