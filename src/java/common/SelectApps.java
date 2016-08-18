/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.AccessAPI;
import beans.DataBeans;
import model.GetDataList;

/**
 *
 * @author maimaimai
 */
public class SelectApps extends HttpServlet {

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
            ArrayList <DataBeans> appInfo= null;
            ArrayList <DataBeans> appData= null;

            //何度もデータベースにアクセスするのを防ぐためにセッションに既にデータがあるかの確認処理
            if(hs.getAttribute("appInfo") == null){

                //アプリ情報を取得
                appInfo = GetDataList.getInstance().getAppData();
                //appInfoをセッションに格納
                hs.setAttribute("appInfo", appInfo);
            
            }
            //何度もWebAPIにアクセスするのを避けるためにセッションにデータがあるかの確認
            if(hs.getAttribute("appData") == null){
                appData = AccessAPI.getInstance().getAppData(appInfo);
                       
                        //セッションに格納
                        hs.setAttribute("appData", appData);
            }                           
            request.getRequestDispatcher("selectapps.jsp").forward(request, response);
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
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
