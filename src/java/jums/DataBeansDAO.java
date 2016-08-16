/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jums;

import base.DBManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author maimaimai
 */
public class DataBeansDAO {
    
    //インスタンスオブジェクトを返却させてコードの簡略化
    public static DataBeansDAO getInstance(){
        return new DataBeansDAO();
    }
    
    public ArrayList<DataBeansDTO> getAppData() throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            st =  con.prepareStatement("select * from app_t where deleteFlg = 0");
            ResultSet rs = st.executeQuery();
            ArrayList<DataBeansDTO> appInfo = new ArrayList();
            
            while(rs.next()){
                DataBeansDTO resultDb = new DataBeansDTO();
                resultDb.setAppID(rs.getInt(1));
                resultDb.setAppName(rs.getString(2));
                resultDb.setPublisher(rs.getString(3));
                resultDb.setStoreID(rs.getInt(4));
                appInfo.add(resultDb);                
            }
            System.out.println("select completed");
            return appInfo;
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }
    }
    
            //ユーザーデータ登録時に用いるメソッド
        public void reviewPosting(DataBeansDTO dto) throws SQLException{
            Connection con = null;
            PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            st =  con.prepareStatement("INSERT INTO Review(userID,reviewAppID,reviewTitle,review,newDate) VALUES(?,?,?,?,?)");
            st.setInt(1, dto.getUserID());
            st.setInt(2, dto.getAppID());
            st.setString(3, dto.getReviewTitle());
            st.setString(4, dto.getReview());
            st.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
            st.executeUpdate();
            System.out.println("insert completed");
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }

    }
        
        //ユーザーデータ登録時に用いるメソッド
        public void MakeAccount(DataBeansDTO ud) throws SQLException{
            Connection con = null;
            PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            st =  con.prepareStatement("INSERT INTO user_t(userName,password,mail,newDate) VALUES(?,?,?,?)");
            st.setString(1, ud.getName());
            st.setString(2, ud.getPassword());
            st.setString(3, ud.getMail());
            st.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            st.executeUpdate();
            System.out.println("insert completed");
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }

    }
        //ユーザーのログイン処理を実現するメソッド
        public ArrayList<DataBeansDTO> forLogin(DataBeansDTO ud) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            st =  con.prepareStatement("select * from user_t where userName = ? and password = ?");
            st.setString(1, ud.getName());
            st.setString(2, ud.getPassword());
            ResultSet rs = st.executeQuery();
            DataBeansDTO resultUd = new DataBeansDTO();
            ArrayList<DataBeansDTO> UserData = new ArrayList();
            while(rs.next()){
                
                resultUd.setUserID(rs.getInt(1));
                resultUd.setName(rs.getString(2));
                resultUd.setPassword(rs.getString(3));
                resultUd.setMail(rs.getString(4));
                resultUd.setTotalPoints(rs.getInt(5));
                resultUd.setDeleteFlg(rs.getInt(6));
                resultUd.setNewDate(rs.getTimestamp(7));
                UserData.add(resultUd);
                
            }
            System.out.println("search completed");
            return UserData;
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }
    }
        
        //レビュー表示のための検索処理
        public ArrayList<DataBeansDTO> SearchReview(DataBeansDTO ud) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            st =  con.prepareStatement("select * from review where reviewAppID = ?");
            st.setInt(1, ud.getAppID());
            ResultSet rs = st.executeQuery();
            ArrayList<DataBeansDTO> reviewData = new ArrayList();
                while(rs.next()){
                    DataBeansDTO resultRev = new DataBeansDTO();
                    resultRev.setReviewID(rs.getInt(1));
                    resultRev.setUserID(rs.getInt(2));
                    resultRev.setAppID(rs.getInt(3));
                    resultRev.setReviewTitle(rs.getString(4));
                    resultRev.setReview(rs.getString(5));
                    resultRev.setPoints(rs.getInt(6));
                    resultRev.setNewDate(rs.getTimestamp(7));
                    reviewData.add(resultRev);
                }
            
            System.out.println("search completed");
            return reviewData;
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }
    }
        
        //購入処理時の総金額の更新を行うメソッド
        public void pointAdd(DataBeansDTO ud) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            st =  con.prepareStatement("update user_t set totalPoints = totalPoints+1 where userID=?");
            st.setInt(1, ud.getUserID());
            st.executeUpdate();
            st =  con.prepareStatement("update review set Points = Points+1 where reviewID=?");
            st.setInt(1, ud.getReviewID());
            st.executeUpdate();
            System.out.println("update completed");
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }
    }
    
        //ランキング情報のために総取得ポイントでソートしたユーザー情報を取得するメソッド
        public ArrayList<DataBeansDTO> getRankingData() throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            st =  con.prepareStatement("select userID,userName,totalPoints,deleteFlg from user_t order by totalPoints desc");
            ResultSet rs = st.executeQuery();
            ArrayList<DataBeansDTO> rankingInfo = new ArrayList();
            
            while(rs.next()){
                DataBeansDTO resultDb = new DataBeansDTO();
                resultDb.setUserID(rs.getInt(1));
                resultDb.setName(rs.getString(2));
                resultDb.setTotalPoints(rs.getInt(3));
                
                //deleteFlgが起動していない時のみリストに表示させるためにArrayList入れる。
                if(rs.getInt(4) == 0){
                rankingInfo.add(resultDb);
                }
            }
            System.out.println("select completed");
            return rankingInfo;
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }
    }
        
        //管理者のログイン処理を実現するメソッド
        public ArrayList<DataBeansDTO> forAdminLogin(DataBeansDTO ud) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            st =  con.prepareStatement("select * from admin where admin = ? and password = ?");
            st.setString(1, ud.getName());
            st.setString(2, ud.getPassword());
            ResultSet rs = st.executeQuery();
            DataBeansDTO resultAd = new DataBeansDTO();
            ArrayList<DataBeansDTO> adminData = new ArrayList();
            while(rs.next()){
                
                resultAd.setName(rs.getString(1));
                resultAd.setPassword(rs.getString(2));
                adminData.add(resultAd);
            }
            System.out.println("search completed");
            return adminData;
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }
    }
        
        //管理者がアプリの追加を行うメソッド
        public void adminAddApp(DataBeansDTO ud) throws SQLException{
            Connection con = null;
            PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            st =  con.prepareStatement("insert into app_t(appName,publisher,storeID) VALUES(?,?,?)");
            st.setString(1, ud.getAppName());
            st.setString(2, ud.getPublisher());
            st.setInt(3, ud.getStoreID());
            st.executeUpdate();
            System.out.println("insert completed");
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }

    }
        
        //管理者が個別にレビューの削除を行うメソッド
        //必要ないかも？
        public void adminDeleteReview(DataBeansDTO ud) throws SQLException{
            Connection con = null;
            PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            st =  con.prepareStatement("delete from review where reviewID = ?");
            st.setInt(1, ud.getReviewID());
            st.executeUpdate();
            System.out.println("delete completed");
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }

    }
        
        /*
        *管理者権限によるレビューの削除処理を行うメソッド
        *14日を過ぎたレビューは削除対象とし、管理者の削除行為によって対象のレビューはすべて削除される。
        */
            public void adminDeleteOldReviews()throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            st =  con.prepareStatement("delete from review where newDate = current_date() - 14");
            st.executeUpdate();
            System.out.println("delete completed");
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }
            }
            
    /*
    *アプリのデリートフラグを起動するメソッド
    *ユーザーデータ削除処理は外部キー連結のために行うことができないので、
    *UPDETE処理で削除のフラグの数字を0から1にすることで対応する
    *また、この際不具合の原因になるので該当アプリIDと結びついているレビューは消去する。
    */
    public void appDataDelete(DataBeansDTO ud) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            st =  con.prepareStatement("update app_t set deleteFlg = 1 where appID = ?");
            st.setInt(1, ud.getAppID());
            st.executeUpdate();
            st =  con.prepareStatement("delete from review where reviewAppID = ?");
            st.setInt(1, ud.getAppID());
            st.executeUpdate();
            System.out.println("delete completed");
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }
    }
    
    /*
    *アプリのデリートフラグを折るメソッド
    *同名アプリを追加するとややこしくなるため
    */
    public void deletedAppDataRestoration(DataBeansDTO ud) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            st =  con.prepareStatement("update app_t set deleteFlg = 0 where appID=?");
            st.setInt(1, ud.getAppID());
            st.executeUpdate();
            System.out.println("update completed");
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }
    }

    public ArrayList<DataBeansDTO> getDeletedAppData() throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            st =  con.prepareStatement("select * from app_t where deleteFlg = 1");
            ResultSet rs = st.executeQuery();
            ArrayList<DataBeansDTO> appInfo = new ArrayList();
            
            while(rs.next()){
                DataBeansDTO resultDb = new DataBeansDTO();
                resultDb.setAppID(rs.getInt(1));
                resultDb.setAppName(rs.getString(2));
                //resultDb.setPublisher(rs.getString(3));
                //resultDb.setStoreID(rs.getInt(4));
                appInfo.add(resultDb);                
            }
            System.out.println("select completed");
            return appInfo;
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }
    }
    
}
