<%-- 
    Document   : writereview
    Created on : 2016/08/11, 6:50:51
    Author     : maimaimai
--%>
<%@page import="rfa.JumsHelper"%>
<%@page import="beans.DataBeans"%>
<%@page import="java.util.ArrayList"%>
<%
    JumsHelper jh = JumsHelper.getInstance();
    HttpSession hs = request.getSession();
    ArrayList<DataBeans> appInfo=(ArrayList<DataBeans>)hs.getAttribute("appInfo");
    DataBeans reviewInfo = null;
    boolean reinput = false;
    if(request.getParameter("mode") != null && request.getParameter("mode").equals("REINPUT")){
        reinput = true;
        reviewInfo = (DataBeans)hs.getAttribute("reviewInfo");
    }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="WriteReviewConfirm" method="POST">
        レビュー作品選択
        <select name="ReviewApp">
            <option value="0">作品名を選択してください。</option>
            <% for(int i=0;i<appInfo.size();i++){ %>
                <option value="<%=appInfo.get(i).getAppID()%>"
                <%if(reinput && reviewInfo.getAppID() == appInfo.get(i).getAppID())
                    {out.print("selected = \"selected\"");}%>><%=appInfo.get(i).getAppName()%></option>
            <%}%>
        </select>
        <br><br>
        タイトル
        <input type="text" name="reviewTitle" value="<%if(reinput){out.print(reviewInfo.getReviewTitle());}%>">
        <br><br>
        本文
        <textarea name="review" rows=10 cols=50 style="resize:none" wrap="hard"><% if(reinput){out.print(reviewInfo.getReview());}%></textarea><br><br>
        <br><br>
        <input type="submit" name="btnSubmit" value="確認画面へ">
    </form>
        
        <br>
        <%=jh.home()%>        
        
    </body>
</html>
