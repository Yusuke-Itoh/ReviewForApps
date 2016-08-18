/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author maimaimai
 */
public class DataBeans {
    //サイト表示にのみ用いる
    private String img;//アプリのアートワーク
    private double rate;//Appstore内でのアプリの評価値
    
   //共通して用いる
    private int userID;//ユーザーID
    private Timestamp newDate;//登録日時、あるいは投稿日時
    
    //user_tに用いる
    private String name;//ユーザー名
    private String password;//パスワード
    private String mail;//メールアドレス
    private int totalPoints;//総購入金額
    private int deleteFlg;//削除フラグ
    
    
    //app_tに用いる
    private String appName;//アプリ名
    private int storeID;//AppStore内での商品ID
    private int appID;//サイト内でのアプリID、reviewにも対象アプリIDとして用いる
    private String publisher;//パブリッシャー
    
    //reviewに用いる
    private int reviewID;//レビューID
    private String reviewTitle;//レビュータイトル
    private int points;//レビューポイント
    private String review;//レビュー本文
   
   
 public DataBeans(){
    this.img="";
    this.rate=0;
    
    this.userID = 0;
    
    this.name = "";
    this.password = "";
    this.deleteFlg = 0;
    this.totalPoints=0;
    this.mail="";
       
    this.appID=0;
    this.appName="";
    this.storeID=0;
    this.publisher="";

    this.reviewTitle="";
    this.review="";
    this.reviewID=0;
    this.points=0;
 }
 
   //getter,setterの設定
     public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
    }
    
    public double getRate() {
        return rate;
    }
    public void setRate(double rate) {
        this.rate = rate;
    }
    
    public int getUserID() {
        return userID;
    }
    public void setUserID(int userID) {
        this.userID = userID;
    }
    
   public String getName() {
        return name;
    }
   public void setName(String name) {
        //空文字(未入力)の場合空文字をセット
        if(name.trim().length()==0){
            this.name = "";
        }else{
            this.name = name;
        }
    }

   public String getPassword() {
        return password;
    }
   public void setPassword(String name) {
        //空文字(未入力)の場合空文字をセット
        if(name.trim().length()==0){
            this.password = "";
        }else{
            this.password = name;
        }
    }
      public String getMail() {
        return mail;
    }
   public void setMail(String name) {
        //空文字(未入力)の場合空文字をセット
        if(name.trim().length()==0){
            this.mail = "";
        }else{
            this.mail = name;
        }
    }
        public Timestamp getNewDate(){
        return newDate;
    }
    public void setNewDate(Timestamp newDate){
        this.newDate = newDate;
    }
        public int getTotalPoints() {
        return totalPoints;
    }
    public void setTotalPoints(int totalPoint) {
        this.totalPoints = totalPoint;
    }
    
    public int getPoints() {
        return points;
    }
    public void setPoints(int points) {
        this.points = points;
    }
    
    public int getAppID() {
        return appID;
    }    
    public void setAppID(int appID) {
        this.appID = appID;
    }
    
    public String getAppName(){
        return appName;
    }
    public void setAppName(String appName){
        this.appName = appName;
    }
    
    public int getDeleteFlg(){
        return deleteFlg;
    }
    public void setDeleteFlg(int deleteFlg){
        this.deleteFlg = deleteFlg;
    }
    
    public int getStoreID(){
        return storeID;
    }
    public void setStoreID(int storeID){
        this.storeID = storeID;
    }
    
    public  String getPublisher(){
        return publisher;
    }
    public void setPublisher(String publisher){
        this.publisher = publisher;
    }
    
    public int getReviewID(){
        return reviewID;
}
    public void setReviewID(int reviewID){
        this.reviewID = reviewID;
    }

    public String getReviewTitle(){
        return reviewTitle;
    }
    public void setReviewTitle(String reviewTitle){
        this.reviewTitle = reviewTitle;
    }
    
    public String getReview(){
        return review;
    }
    public void setReview(String review){
        this.review = review;
    }
    
    //ユーザー登録フォームに必要な情報が未記入かどうかをチェックするメソッド
    public ArrayList<String> chkproperties(){
        ArrayList<String> chkList = new ArrayList<String>();
        if(this.name.equals("")){
            chkList.add("name");
        }
        if(this.password.equals("")){
            chkList.add("password");
        }
        if(this.mail.equals("")){
            chkList.add("mailaddress");
        }
        return chkList;
    }
    
    //レビュー登録に必要な情報が未記入かどうかをチェックするメソッド
    public ArrayList<String> chkRev(){
        ArrayList<String> chkList = new ArrayList<String>();
        if(this.appID == 0){
            chkList.add("appName");
        }
        if(this.reviewTitle.equals("")){
            chkList.add("reviewTitle");
        }
        if(this.review.equals("")){
            chkList.add("review");
        }
        return chkList;
    }
    
        //UDの中身をデータベースにアクセスするためにDTO型に変換するメソッド
    public void UD2DTOMapping(DataBeansDTO dto){
        //ユーザー関連
        dto.setUserID(this.userID);
        dto.setName(this.name);
        dto.setPassword(this.password);
        dto.setMail(this.mail);
        dto.setTotalPoints(this.totalPoints);
        dto.setDeleteFlg(this.deleteFlg);
        //アプリ関連
        dto.setAppID(this.appID);
        dto.setAppName(this.appName);
        dto.setStoreID(this.storeID);
        dto.setPublisher(this.publisher);
        //レビュー関連
        dto.setReviewTitle(this.reviewTitle);
        dto.setReview(this.review);
        dto.setReviewID(this.reviewID);
        dto.setPoints(this.points);                
    }
    
     
    //データベースから取り出したDTO型のデータをDataBeansで利用するために変換するメソッド
    public void DTO2UDMapping(DataBeansDTO dto){
       //更新、投稿日時
       this.setNewDate(dto.getNewDate());
       //ユーザー関連
       this.setUserID(dto.getUserID());
       this.setName(dto.getName());
       this.setPassword(dto.getPassword());
       this.setMail(dto.getMail());
       this.setTotalPoints(dto.getTotalPoints());
       this.setDeleteFlg(dto.getDeleteFlg());
       //アプリ関連
       this.setAppID(dto.getAppID());
       this.setAppName(dto.getAppName());
       this.setStoreID(dto.getStoreID());
       this.setPublisher(dto.getPublisher());
       //レビュー関連
       this.setReviewID(dto.getReviewID());
       this.setReviewTitle(dto.getReviewTitle());
       this.setReview(dto.getReview());
       this.setPoints(dto.getPoints());

    }
    
    
    
    
    
    
    
    
    
    
}
