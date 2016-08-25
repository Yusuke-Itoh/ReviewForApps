/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import beans.DataBeans;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 *
 * @author maimaimai
 */
public class AccessAPI {
    public static AccessAPI getInstance(){
        return new AccessAPI();
    }
    /*
    *WebAPIアクセスとJSONの処理をまとめたクラス
    */

    //APIにアクセスしてデータを受け取るメソッド
    public String getResult(String urlString){
    String result = "";
    try{
        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.connect();
        //BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        InputStreamReader isr = new InputStreamReader(con.getInputStream(),"UTF-8");
        BufferedReader in = new BufferedReader(isr);
        
        String tmp = "";
        while ((tmp = in.readLine()) != null) {
        result += tmp;
        }
    in.close();
    con.disconnect();
        }catch(Exception e){
    e.printStackTrace();
    }
    return result;
}
    //Jacksonを利用して受け取ったJSONデータを変換するメソッド
    public JsonNode getJsonNode(String jsonString){
       JsonNode head = null;
       try{
       JsonFactory jfactory = new JsonFactory();
       JsonParser parser = jfactory.createParser(jsonString);
       ObjectMapper mapper = new ObjectMapper();
       head = mapper.readTree(parser);
       }catch(Exception e){
       e.printStackTrace();
    }
      return head;
    }
    
    //app_tの情報をとってきたappInfoを引数にしてiTunesAPIに接続し情報をとってくる
    public ArrayList<DataBeans> getAppData(ArrayList<DataBeans> appInfo){
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
        }
            return appData;
        }
            
    //iTunesAPIの検索機能を利用し取得したデータをDataBeans型のArrayListに格納するメソッド
    public ArrayList<DataBeans> getSearchData(String term){
        //エンコーダーでqueryの内容を変換する
        String url ="https://itunes.apple.com/search?term=" + term + "&media=software&country=jp&lang=ja_jp&limit=10";
        //yahooAPIに接続し検索結果をもらう
        AccessAPI ay = new AccessAPI();
        String str =  ay.getResult(url);
        JsonNode jn = ay.getJsonNode(str);

        ArrayList<DataBeans> searchResult = new ArrayList();
        //ItemDataBeansを呼び出し数値を入れておく
        for(int i=0;i<10;i++){
            DataBeans db = new DataBeans();
            db.setImg(jn.get("results").get(i).get("artworkUrl100").textValue());
            db.setAppName(jn.get("results").get(i).get("trackName").textValue());
            db.setPublisher(jn.get("results").get(i).get("artistName").textValue());
            db.setRate(jn.get("results").get(i).get("averageUserRating").intValue());
            db.setStoreID(jn.get("results").get(i).get("trackId").intValue());
            searchResult.add(db);
        }
        return searchResult; 
    }
}