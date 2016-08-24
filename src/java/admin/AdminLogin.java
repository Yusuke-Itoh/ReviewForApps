/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import beans.DataBeans;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import rfa.Log;

/**
 *
 * @author maimaimai
 */
public class AdminLogin extends HttpServlet {
    /*
    *非ログイン時には管理者ログインページへ、
    *管理者ログイン時にはログアウト処理を行うサーブレット
    */

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
            //セッションの準備            
            HttpSession hs = request.getSession();
            
            //管理者情報を入れるadを呼び出す。
            DataBeans ad = (DataBeans)hs.getAttribute("ad");
            
            //ログイン処理かログアウト処理かの判断
            if(ad == null){
                //以下ログイン時の処理
                
                //ログイン後に遷移するために前のページ情報を取得してセッションに格納しておく。
                String page =request.getHeader("Referer").substring(36);
                hs.setAttribute("page",page);

                //ユーザー認証が必要なページへのアクセスルートチェックのためのランダムな数字を用意
                hs.setAttribute("ac", (int) (Math.random() * 1000));



                //ログに情報を記載
                Log.getInstance().log("管理者ログインページに遷移");
                request.getRequestDispatcher("/adminlogin.jsp").forward(request, response);
            
            }else{
                //以下ログアウト時の処理

                //セッションの削除
                hs.removeAttribute("ad");
                hs.removeAttribute("ac");

                //ログに情報を記載
                //Log.getInstance().log("管理者ログアウト完了");
                
                request.getRequestDispatcher("/adminlogout.jsp").forward(request, response);
            }            
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
