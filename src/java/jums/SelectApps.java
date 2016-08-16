/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jums;

import com.fasterxml.jackson.databind.JsonNode;
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
            ArrayList <DataBeans> appInfo= new <DataBeans>ArrayList();
            
            //何度もデータベースにアクセスするのを防ぐためにセッションに既にデータがあるかの確認処理
            if(hs.getAttribute("appInfo") == null){
                
                //DBからstoreIDを取得、iTunesのLookupAPIで画像データ、名前を習得
                DataBeansDAO dao = new DataBeansDAO();
                ArrayList<DataBeansDTO> appList= dao.getAppData();

                //DTO2DTmappingしてDataBeans型にし、取り出した情報をセッションで持ちまわせるようにする            
                for(int i=0;i<appList.size();i++){
                DataBeans db= new DataBeans();
                db.DTO2UDMapping(appList.get(i));
                appInfo.add(db);            
                }
                //appInfoをセッションに格納
                hs.setAttribute("appInfo", appInfo);
            
            }
            //何度もWebAPIにアクセスするのを避けるためにセッションにデータがあるかの確認
            if(hs.getAttribute("appData") == null){
                    //iTunesAPIに接続し情報をとってくる
                    ArrayList<DataBeans> appData= new ArrayList();
                    for(int i=0;i<appInfo.size();i++){          
                        String storeID = String.valueOf(appInfo.get(i).getStoreID());
                        String url = "https://itunes.apple.com/jp/lookup?id=" + storeID;
                        
                        //iTunesAPIに接続し検索結果をもらう
                        AccessAPI aa = new AccessAPI();
                        String str =  aa.getResult(url);
                        JsonNode jn = aa.getJsonNode(str);
                        DataBeans db = new DataBeans();
                        
                        //jsonからデータを取得
                        db.setImg(jn.get("results").get(0).get("artworkUrl100").textValue());
                        db.setAppName(jn.get("results").get(0).get("trackName").textValue());
                        db.setPublisher(jn.get("results").get(0).get("artistName").textValue());
                        db.setRate(jn.get("results").get(0).get("averageUserRating").intValue());
                        appData.add(db);
                       
                        //セッションに格納
                        hs.setAttribute("appData", appData);
                    }                
                

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
