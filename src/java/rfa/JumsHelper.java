/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rfa;

import java.util.ArrayList;

/**
 *
 * @author maimaimai
 */
public class JumsHelper {
    
    /*
    *画面表示をサポートするクラス
    */
    
         //トップへのリンクを定数として設定
    private final String homeURL = "index.jsp";
    
    public static JumsHelper getInstance(){
        return new JumsHelper();
    }
    
    //トップへのリンクを返却
    public String home(){
        return "<a href=\""+homeURL+"\">トップへ戻る</a>";
    }
    
        public String chkInputAcc(ArrayList<String> chkList){
        String output = "";
        for(String val : chkList){
                if(val.equals("name")){
                    output += "名前";
                }
                if(val.equals("password")){
                    output += "パスワード";
                }
                if(val.equals("mailaddress")){
                    output += "メールアドレス";
                }
                
                output +="が未記入です。<br>";
            }
        return output;
    }
        
        public String chkInputRev(ArrayList<String> chkList){
        String output = "";
        for(String val : chkList){
                if(val.equals("appName")){
                    output += "レビューするアプリが未選択です。";
                }
                if(val.equals("reviewTitle")){
                    output += "タイトルが未記入です。";
                }
                if(val.equals("review")){
                    output += "本文が未記入です。";
                }
                
                output +="<br>";
            }
        return output;
    }
}
