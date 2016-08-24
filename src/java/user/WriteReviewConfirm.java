/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import beans.DataBeans;
import rfa.Log;

/**
 *
 * @author maimaimai
 */
public class WriteReviewConfirm extends HttpServlet {
    /*
    *投稿レビュー確認ページへ遷移するサーブレット
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
            
            //セッションスタート
            HttpSession hs = request.getSession();

            //フォームからの入力を取得して、JavaBeansに格納
            request.setCharacterEncoding("UTF-8");//リクエストパラメータの文字コードをUTF-8に変更
            DataBeans reviewInfo = new DataBeans();
            reviewInfo.setAppID(Integer.parseInt(request.getParameter("ReviewApp")));
            reviewInfo.setReviewTitle(request.getParameter("reviewTitle"));
            reviewInfo.setReview(request.getParameter("review"));
            
            //データベースの書き込みに備えてユーザーIDもセッションから一度取得し、レビュー情報と一緒に入れておく。
            DataBeans ud = (DataBeans)hs.getAttribute("ud");
            reviewInfo.setUserID(ud.getUserID());
            
            //ユーザー情報群をセッションに格納
            hs.setAttribute("reviewInfo", reviewInfo);
            System.out.println("Session updated!!");
            
            //ログに書き込み
            Log.getInstance().log("投稿確認ページへ遷移");
            request.getRequestDispatcher("writereviewconfirm.jsp").forward(request, response);
            
        }catch(Exception e){
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
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
