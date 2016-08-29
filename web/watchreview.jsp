<%-- 
    Document   : watchreview
    Created on : 2016/08/11, 0:10:11
    Author     : maimaimai
--%>
<%@page import="rfa.JumsHelper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="beans.DataBeans"%>
<%
    JumsHelper jh = JumsHelper.getInstance();
    HttpSession hs = request.getSession();
    DataBeans ud = (DataBeans)hs.getAttribute("ud");
    ArrayList<DataBeans> appData=(ArrayList<DataBeans>)hs.getAttribute("appData");
    ArrayList<DataBeans> revList=(ArrayList<DataBeans>)hs.getAttribute("revList");
    DataBeans appIDnum =(DataBeans)hs.getAttribute("appIDnum");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>レビューアプリ選択ページ</title>
        <script type="text/javascript" src="js/jquery-3.1.0.min.js"></script>
        <script type="text/javascript" src="js/doubleclick.js"></script>
    <style type="text/css">
   .FreezePaneOff
   {
      visibility: hidden;
      display: none;
      position: absolute;
      top: -100px;
      left: -100px;
   }

   .FreezePaneOn
   {
      position: absolute;
      top: 0px;
      left: 0px;
      visibility: visible;
      display: block;
      width: 100%;
      height: 100%;
      background-color: #666;
      z-index: 999;
      filter:alpha(opacity=85);
      -moz-opacity:0.85;
      padding-top: 20%;
   }

   .InnerFreezePane
   {
      text-align: center;
      width: 66%;
      background-color: #171;
      color: White;
      font-size: large;
      border: dashed 2px #111;
      padding: 9px;
   }
    </style>
    <style>
    table {
            border-collapse: collapse;
    }
    td {
            border: solid 1px;
            padding: 0.5em;
    }
    </style>
    <script type="text/javascript" src="js/doubleclick.js"></script>
    </head>
    <body>
        <div align="center" id="FreezePane" class="FreezePaneOff">
    <div id="InnerFreezePane" class="InnerFreezePane"> </div>
    </div>
        <table>
            <tr>
                <td>
                    <img src="<%=appData.get(appIDnum.getAppID()-1).getImg()%>" alt="">
                </td>   
                <td>
                    <%=appData.get(appIDnum.getAppID()-1).getAppName()%>
                    <br>
                    <%=appData.get(appIDnum.getAppID()-1).getPublisher()%>
                    <br><br>
                    Appstore内での評価：<%=appData.get(appIDnum.getAppID()-1).getRate()%>
                </td>
            </tr>
        </table>
                <hr>        
        <%for(int i=0;i<revList.size();i++){%>
            <table>
                <tr>
                    <td>レビュータイトル：<%=revList.get(i).getReviewTitle()%></td>
                </tr>
                <tr>
                    <td>
                        <div style="display:inline-flex">
                            獲得ポイント：<%=revList.get(i).getPoints()%>
                            <!--未ログイン時と、自分のレビューには評価ボタンを表示しないようにする。-->
                            <%if(ud != null && ud.getUserID() != revList.get(i).getUserID()){%>
                                <form action="PointAdd" method="POST">
                                    <input id="btnSubmit" type="submit" name="submit" value="いいね！"  onclick="FreezeScreen('更新中...');">
                                    <input hidden name="UserID" value="<%=revList.get(i).getUserID()%>">
                                    <input hidden name="ReviewID" value="<%=revList.get(i).getReviewID()%>">
                                </form>
                            <%}%>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>本文：<%=revList.get(i).getReview()%></td>
                </tr>
            </table>
            <br>
        <%}%>
        <br>
        <%=jh.home()%>
    </body>
</html>
