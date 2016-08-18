<%-- 
    Document   : index
    Created on : 2016/08/09, 17:10:58
    Author     : maimaimai
--%>
<%@page import="beans.DataBeans"%>
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
        <script type="text/javascript">
            
    function disp(){

	// 「OK」時の処理開始 ＋ 確認ダイアログの表示
	if(window.confirm('古いレビューを削除してよろしいですか？')){

		location.href = "AdminDeleteOldReviews"; // example_confirm.html へジャンプ
	}   
	// 「OK」時の処理終了
}
        </script>
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
        <br><br>
        <form action="MyDelete" method="POST">
        <input id="btnSubmit" type="submit" name="btnSubmit" value="アカウント削除画面へ">
        <input type="hidden" name="ac"  value="<%= hs.getAttribute("ac")%>">
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
        <p><input type="button" value="古いレビューを削除" onClick="disp()"></p>
        
        <form action="AdminDeletedAppDataRestoration" method="POST">
        <input id="btnSubmit" type="submit" name="btnSubmit" value="削除したアプリデータを復元する">
        </form>
        <br>
        <form action="AdminUserDataRestoration" method="POST">
        <input id="btnSubmit" type="submit" name="btnSubmit" value="ユーザーデータの復元">
        </form>
        <%}%>       
    </body>
    　　<jsp:include page="footer.jsp" flush="true" />
</html>
