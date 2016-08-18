/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rfa;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author maimaimai
 */
public class Log {
        public static Log getInstance(){
        return new Log();
    }
    
    public void log(String text) throws IOException{
        Date d = new Date();
        FileWriter fw = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        try{
            
            File txt = new File("C:\\Users\\maimaimai\\Documents\\NetBeansProjects\\ReviewForApps\\web\\WEB-INF\\log\\log.txt");
            fw = new FileWriter(txt,true);
            fw.write(sdf.format(d) + "---" + text + "\r\n");
            fw.flush();
            
        }catch(IOException e){
            System.out.print("書き込みに失敗しました");
        }finally{
            if(fw != null){
                    fw.close();
            }
            
        }
    }
    
}
