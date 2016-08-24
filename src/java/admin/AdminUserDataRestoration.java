/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
public class AdminUserDataRestoration extends HttpServlet {

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
            ArrayList <DataBeans> deletedUserInfo= new <DataBeans>ArrayList();
            
            //DBからstoreIDを取得、iTunesのLookupAPIで画像データ、名前を習得
            DataBeansDAO dao = new DataBeansDAO();
            ArrayList<DataBeansDTO> userList= dao.getDeletedUserData();

            //DTO2DTmappingしてDataBeans型にし、取り出した情報をセッションで持ちまわせるようにする            
            for(int i=0;i<userList.size();i++){
                DataBeans db= new DataBeans();
                db.DTO2UDMapping(userList.get(i));
                deletedUserInfo.add(db);            
                }
            //appInfoをセッションに格納
            hs.setAttribute("deletedUserInfo", deletedUserInfo);
            //ログに書き込み
            Log.getInstance().log("管理者ユーザー復元ページへ遷移");
            request.getRequestDispatcher("adminuserdatarestoration.jsp").forward(request, response);
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }finally {
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
