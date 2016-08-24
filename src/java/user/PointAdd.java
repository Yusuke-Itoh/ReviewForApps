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
import beans.DataBeansDAO;
import beans.DataBeansDTO;
import rfa.Log;

/**
 *
 * @author maimaimai
 */
public class PointAdd extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    /*
    *レビューにポイントを付与するページ
    *同時にレビュー主であるユーザーにもポイントが加算されます。
    */
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            //セッションの呼び出し
            HttpSession hs = request.getSession();
            
            //ポイント追加処理した後閲覧ページに戻るためにページ情報を保存しておく           
            String page =request.getHeader("Referer").substring(36);
            hs.setAttribute("page",page);
            
            //ポイント追加処理をする。
            DataBeans ud = new DataBeans();
            ud.setUserID(Integer.parseInt(request.getParameter("UserID")));
            ud.setReviewID(Integer.parseInt(request.getParameter("ReviewID")));
            
            //DTOオブジェクトにマッピング。DB専用のパラメータに変換
            DataBeansDTO dto = new DataBeansDTO();
            ud.UD2DTOMapping(dto);
            
            //DAOにアクセス。ポイント追加処理
            DataBeansDAO dao = new DataBeansDAO();
            dao.pointAdd(dto);
            
            //ログに書き込み
            Log.getInstance().log("ポイント追加処理完了");
            //一つ前の操作をしていたページにリダイレクト
            response.sendRedirect(String.valueOf(hs.getAttribute("page")));
        
        
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
