/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

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
public class AdminLoginResult extends HttpServlet {
    /*
    *管理者ログイン処理を行うサーブレット
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
                HttpSession hs = request.getSession();
                request.setCharacterEncoding("UTF-8");//リクエストパラメータの文字コードをUTF-8に変更
                DataBeans ad= new DataBeans();
                
            //アクセスルートチェック
                String accesschk = request.getParameter("ac");
                if(accesschk ==null || (Integer)hs.getAttribute("ac")!=Integer.parseInt(accesschk)){
                    throw new Exception("不正なアクセスです");
                }
                
            //フォームからユーザー名とパスワードを取得する
                ad.setName(request.getParameter("admin"));
                ad.setPassword(request.getParameter("password"));           

            //DTOオブジェクトにマッピング。DB専用のパラメータに変換
                DataBeansDTO searchData = new DataBeansDTO();
                ad.UD2DTOMapping(searchData);

            //DBへの検索処理
                DataBeansDAO dao = new DataBeansDAO();
                ArrayList<DataBeansDTO> adList= dao.forAdminLogin(searchData);
                
                //ユーザー名、パスワードが正しければDBから要素がとれている。
                if(adList.size() == 0){
                    //ログに情報を記載
                    Log.getInstance().log("不正なログインを確認");
                    request.getRequestDispatcher("/loginerror.jsp").forward(request, response);  
                }else{
                    //要素をUserDataにマッピング    
                    for(int i=0;i<adList.size();i++){
                        DataBeansDTO udd = adList.get(i);
                        ad.DTO2UDMapping(udd);
                        
                        hs.setAttribute("ad", ad);
                        
                            //ログに情報を記載
                            Log.getInstance().log("ログイン成功");    
                            //一つ前の操作をしていたページにリダイレクト
                            response.sendRedirect(String.valueOf(hs.getAttribute("page")));
                            
                            
                    }
                }
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
