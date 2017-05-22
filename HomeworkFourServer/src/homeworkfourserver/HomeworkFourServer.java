/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homeworkfourserver;

import java.net.*;
import java.util.*;
import java.io.*;

/**
 *
 * @author Jabid
 */
public class HomeworkFourServer {

    /**
     * @param args the command line arguments
     */
    static PrintStream sout;
    static Scanner sin;
    static String username;
    static ArrayList<ConnectedClient> listofclients;
    
    public static void main(String[] args) 
    {
        ServerSocket ss=null;
        listofclients = new ArrayList();
        try{
            ss = new ServerSocket(5190);
            while(true){
                Socket client = ss.accept();
                sin = new Scanner(client.getInputStream()); //listen from client
                sout = new PrintStream(client.getOutputStream());//send to client
                username =  sin.nextLine();
               // System.out.println(username);
                ConnectedClient cc = new ConnectedClient(username,sin,sout);
                cc.start();
                listofclients.add(cc);
            }
        }
        catch (IOException e){
            System.out.println("Could not get the socket to work!");
            System.exit(1);
        }
    }

    static class ConnectedClient extends Thread
    {
        Scanner sc;
        PrintStream ps;
        String uname; 
        
        ConnectedClient(String name, Scanner scan, PrintStream p){
            uname = name;
            sc = scan;
            ps = p;
        }
        
        @Override
        public void run()
        {
            
            try{
                String msg;
                while(true)
                {
                    msg = sc.nextLine();
                   // System.out.println(sin);
                    for(int i =0; i<listofclients.size();i++)
                    {
                        listofclients.get(i).ps.println(uname + ": " + msg);
                    }
                }
            }
            catch(Exception e){}

    }
}
}

