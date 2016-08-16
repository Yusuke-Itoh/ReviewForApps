/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jums;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
public class WriteReview extends HttpServlet {

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
            
            //何度もデータベースにアクセスするのを防ぐためにappInfoにデータが入ってなければデータベースにアクセスし、アプリ情報を取り出す
            if(hs.getAttribute("appInfo") == null){
                    //DBからappNameを取得
                    DataBeansDAO dao = new DataBeansDAO();
                    ArrayList<DataBeansDTO> appList= dao.getAppData();
                    //DTOマッピングしてDataBeans型のappInfoに入れていく
                    //もしかしてDTO自体必要ない？でも他のページとの整合性のために一応使っとく。            
                    ArrayList <DataBeans> appInfo= new <DataBeans>ArrayList();
                    for(int i=0;i<appList.size();i++){
                    DataBeans db= new DataBeans();
                    db.DTO2UDMapping(appList.get(i));
                    appInfo.add(db);            
                    }
                    //セッションに収納
                    hs.setAttribute("appInfo", appInfo);
            
            }
            
            request.getRequestDispatcher("writereview.jsp").forward(request, response);
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
