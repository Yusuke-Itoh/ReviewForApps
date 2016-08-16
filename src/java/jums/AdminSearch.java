/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jums;

import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author maimaimai
 */
public class AdminSearch extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            
            //セッションの呼び出し
            HttpSession hs = request.getSession();
             
            request.setCharacterEncoding("UTF-8");//リクエストパラメータの文字コードをUTF-8に変更
            //エンコーダーでqueryの内容を変換する
            String st = request.getParameter("term");
            String term = URLEncoder.encode(st,"UTF-8");
            String url ="https://itunes.apple.com/search?term=" + term + "&media=software&country=jp&lang=ja_jp&limit=10";
            //yahooAPIに接続し検索結果をもらう
            AccessAPI ay = new AccessAPI();
            String str =  ay.getResult(url);
            JsonNode jn = ay.getJsonNode(str);

            ArrayList<DataBeans> searchResult = new ArrayList();
            //ItemDataBeansを呼び出し数値を入れておく
            for(int i=0;i<10;i++){
                DataBeans db = new DataBeans();
                db.setImg(jn.get("results").get(i).get("artworkUrl100").textValue());
                db.setAppName(jn.get("results").get(i).get("trackName").textValue());
                db.setPublisher(jn.get("results").get(i).get("artistName").textValue());
                db.setRate(jn.get("results").get(i).get("averageUserRating").intValue());
                db.setStoreID(jn.get("results").get(i).get("trackId").intValue());
                searchResult.add(db);
            }
            
            //セッションに格納
            hs.setAttribute("searchResult", searchResult);
            request.getRequestDispatcher("adminsearch.jsp").forward(request, response);
            
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
