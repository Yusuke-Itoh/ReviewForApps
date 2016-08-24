/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.AccessAPI;
import beans.DataBeans;
import rfa.Log;

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
            
            //iTunesAPIにアクセスし、受け取ったデータをDataBeans型のArrayListに格納し終えたものを受け取る
            ArrayList<DataBeans> searchResult =null;
            searchResult=AccessAPI.getInstance().getSearchData(term);
            
            //セッションに格納
            hs.setAttribute("searchResult", searchResult);
            
            //ログに書き込み
            Log.getInstance().log("管理者アプリ検索ページへ遷移");
            
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
