/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import beans.DataBeans;
import beans.DataBeansDAO;
import beans.DataBeansDTO;
import rfa.Log;

/**
 *
 * @author maimaimai
 */
public class AdminUserDataRestorationComplete extends HttpServlet {
    /*
    *ユーザーデータ復旧完了ページへと遷移するサーブレット
    *デリートフラグを折ることで表示上のデータ復旧を実現するための準備を行う
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

            //UserIDを受け取ってDTOにマッピングしデータベースアクセス、削除処理をする。（実際には更新処理だけどね）
            request.setCharacterEncoding("UTF-8");//リクエストパラメータの文字コードをUTF-8に変更
            DataBeans ud = new DataBeans();
            
            //数値を受け取る
            ud.setUserID(Integer.parseInt(request.getParameter("userID")));
            
            //DTO呼び出しマッピング
            DataBeansDTO userID = new DataBeansDTO();
            ud.UD2DTOMapping(userID);
            
            //DBへの復元処理
            DataBeansDAO.getInstance().deletedUserDataRestoration(userID);
            
            //ログに書き込み
            Log.getInstance().log("ユーザー復元処理完了");
            request.getRequestDispatcher("/adminuserdatarestorationcomplete.jsp").forward(request, response);
            
            
        }catch(Exception e){
            //何らかの理由で失敗したらエラーページにエラー文を渡して表示。想定は不正なアクセスとDBエラー
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
