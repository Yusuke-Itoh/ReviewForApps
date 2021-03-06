/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

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
public class Ranking extends HttpServlet {
    /*
    *ランキングページへと遷移するサーブレット
    *データベースにアクセスし、ユーザーデータを取得し、ランキングとして表示する準備を行う。
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
            //セッションの呼び出し
            HttpSession hs = request.getSession();
                            
            //データベースアクセス
            //DBへの検索処理
            ArrayList<DataBeansDTO> rankingData= DataBeansDAO.getInstance().getRankingData();
            //取得したランキングデータをUserData型のArrayListにする
            //ランキングデータをUserData型のArrayListに格納
            ArrayList <DataBeans> rankingList= new <DataBeans>ArrayList();
                for(int i=0;i<rankingData.size();i++){
                    DataBeans rev = new DataBeans();
                    DataBeansDTO udd = rankingData.get(i);
                    rev.DTO2UDMapping(udd);
                    rankingList.add(rev);
                }
                
            //revInfoをセッションに格納
            hs.setAttribute("rankingList", rankingList);
            
            //ログに書き込み
            Log.getInstance().log("ランキングページへ遷移");
            request.getRequestDispatcher("ranking.jsp").forward(request, response);
            
            
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
