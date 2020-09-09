/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scences;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ThinhIt
 */
public class ketnoi {
    private BufferedReader reader;
    private PrintWriter gui;
    public void connect(){
        try {
            int port=11006;
            System.out.println("client.Client.main()");
            Socket socket= new Socket("peter2.kilatiron.com", port);
            InputStreamReader streamreader = new InputStreamReader(socket.getInputStream());
            reader = new BufferedReader(streamreader);
            gui = new PrintWriter(socket.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(ketnoi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public String nhan(){
        try {
            return reader.readLine();
        } catch (IOException ex) {
            Logger.getLogger(ketnoi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "khong  nhan dc ";
    }
    public void ghi(String n){
         gui.println(n);
         gui.flush();
    }
    
//    public static void main(String[] args) throws UnknownHostException, IOException{
//         
//        
//          
//        
//         if(n.equals("danh truoc")){
//             while(true){
//                System.out.println("nhap de gui len server: ");
//                n=s.nextLine();
//               
//                if(n.equals("e")){
//                    break;
//                }
//                n=reader.readLine();
//                if(n.equals("e")){
//                    System.out.println(" dong ket noi");
//                    socket.close();
//                    break;
//                }
//                System.out.println("nhan dc tu server: "+ n);
//             }
//                         
//         }else{
//             while(true){
//                n=reader.readLine();
//                if(n.equals("e")){
//                    System.out.println(" dong ket noi");
//                    socket.close();
//                    break;
//                }
//                System.out.println("nhan dc tu server: "+ n);
//                System.out.println("nhap de gui len server: ");
//                String g=s.nextLine();
//                gui.println(g);
//                gui.flush();
//                if(g.equals("e")){
//                    break;
//                }
//             }
//         }      
//    }

}
