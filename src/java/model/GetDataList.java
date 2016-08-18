/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import beans.DataBeans;
import beans.DataBeansDAO;
import beans.DataBeansDTO;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author maimaimai
 */
public class GetDataList {    
    public static GetDataList getInstance(){
        return new GetDataList();
    }


    //DB内のアプリデータを取得するメソッド
    public ArrayList<DataBeans> getAppData()throws SQLException{
            ArrayList <DataBeans> appInfo= new <DataBeans>ArrayList();
            DataBeansDAO dao = new DataBeansDAO();
            ArrayList<DataBeansDTO> appList= dao.getAppData();
                //DTO2DTmappingしてDataBeans型への変換をする           
                for(int i=0;i<appList.size();i++){
                    DataBeans db= new DataBeans();
                    db.DTO2UDMapping(appList.get(i));
                    appInfo.add(db);
                }
                return appInfo;                
    }
    
    //ランキングデータの取得メソッド
    public ArrayList<DataBeans> getRanking()throws SQLException{
                //データベースアクセス
            //DBへの検索処理
            ArrayList<DataBeansDTO> rankingData= DataBeansDAO.getInstance().getRankingData();
            //取得したランキングデータをUserData型のArrayListにする
            ArrayList <DataBeans> rankingList= new <DataBeans>ArrayList();
                for(int i=0;i<rankingData.size();i++){
                    DataBeans rev = new DataBeans();
                    DataBeansDTO udd = rankingData.get(i);
                    rev.DTO2UDMapping(udd);
                    rankingList.add(rev);
                }
            return rankingList;
    }
    
}
