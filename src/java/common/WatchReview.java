/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
public class WatchReview extends HttpServlet {

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
            /*
            *レビュー閲覧ページのサーブレット
            *受け取ったappIDnumを使ってDBのreviewテーブルにアクセスし、レビューを取り出してくる。
            *それをjspページに表示させればOK。ログイン時にはいいねボタンを表示し、ポイント付与機能を与える。
            */
            
            //セッションの呼び出し
            HttpSession hs = request.getSession();
            
            request.setCharacterEncoding("UTF-8");//リクエストパラメータの文字コードをUTF-8に変更
            
            //レビューを見るアプリのIDを取得しデータベースに接続する
            DataBeans appIDnum = new DataBeans();
            appIDnum.setAppID(Integer.parseInt(request.getParameter("appIDnum")));
            
            //セッションに入れておく。
            hs.setAttribute("appIDnum", appIDnum);
            
            //データベースアクセスのためのDTOへのマッピング処理
            //DTOオブジェクトにマッピング。DB専用のパラメータに変換
            DataBeansDTO searchData = new DataBeansDTO();
            appIDnum.UD2DTOMapping(searchData);
                
            //データベースアクセス
            //DBへの検索処理
            DataBeansDAO dao = new DataBeansDAO();
            ArrayList<DataBeansDTO> revInfo= dao.SearchReview(searchData);
            
            //レビューをUserData型のArrayListに格納
            ArrayList <DataBeans> revList= new <DataBeans>ArrayList();
                for(int i=0;i<revInfo.size();i++){
                    DataBeans rev = new DataBeans();
                    DataBeansDTO udd = revInfo.get(i);
                    rev.DTO2UDMapping(udd);
                    revList.add(rev);
                }
                
            //revInfoをセッションに格納
            hs.setAttribute("revList", revList);
            
            //ログへ書き込み
            Log.getInstance().log("レビュー閲覧ページへ遷移");
            request.getRequestDispatcher("watchreview.jsp").forward(request, response);
            
            
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
