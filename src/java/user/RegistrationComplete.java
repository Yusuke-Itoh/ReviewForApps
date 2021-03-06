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
public class RegistrationComplete extends HttpServlet {
    /*
    *ユーザーアカウント登録完了ページへ遷移するサーブレット
    *データベースにアクセスし、ユーザー情報を追加してアカウント管理を実現する
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
            //アクセスルートチェック
            String accesschk = request.getParameter("ac");
            if(accesschk ==null || (Integer)hs.getAttribute("ac")!=Integer.parseInt(accesschk)){
                throw new Exception("不正なアクセスです");
            }
            

            //セッションスタート
            HttpSession session = request.getSession();
            DataBeans ud = (DataBeans)session.getAttribute("newAc");

            //DTOオブジェクトにマッピング。DB専用のパラメータに変換
            DataBeansDTO userdata = new DataBeansDTO();
            ud.UD2DTOMapping(userdata);

            //DBへデータの挿入
            DataBeansDAO.getInstance().MakeAccount(userdata);

            //登録は完了した。ログイン状態であることを示すためにセッション名udとして入れ直し、不要なnewAcは消しておく。
            hs.setAttribute("ud", ud);
            
            hs.removeAttribute("newAc");

            //ログに情報を記載
            Log.getInstance().log("会員登録完了");
            
            request.getRequestDispatcher("registrationcomplete.jsp").forward(request, response);
            
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
