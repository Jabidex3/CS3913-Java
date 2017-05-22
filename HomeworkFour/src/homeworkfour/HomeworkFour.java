/*.
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homeworkfour;

import java.net.*;
import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Jabid
 */
public class HomeworkFour {

    /**
     * @param args the command line arguments
     */
    static Scanner listen_to_server; 
    static PrintStream send_to_server;
    static String user;
    static String ip;
    static TextField tf1, tf2, tf3;
    static JPanel jp;
    static JPanel chatPanel;
    static JButton enterCredentials;
    static TextArea groupMessages;
    static Socket s;
    static JButton send;
    
    public static void main(String[] args) {
        JFrame jf = new JFrame("Chat Server v2.0");
        jf.setSize(400,400);
        jf.setResizable(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
        
        jp = new JPanel();
        jf.add(jp);
        jp.setLayout(new GridLayout(3,1));   
        
        
        
        tf1 = new TextField("Enter Username Here", 30);
        tf2 = new TextField("Enter IP of Server", 30);
        jp.add(tf1);
        jp.add(tf2);
        
        enterCredentials = new JButton("Connect");
        jp.add(enterCredentials);
        
        enterCredentials.addActionListener(new ButtonPress());
        
        
        
        /*Initially hidden chat panel*/
        chatPanel = new JPanel();
        jf.add(chatPanel);
        chatPanel.setVisible(false);
        chatPanel.setLayout(new GridLayout(3,1));
        groupMessages = new TextArea();
        groupMessages.setEditable(false);
        tf3 = new TextField("Enter Message Here", 30);
        send = new JButton("Send");
        chatPanel.add(groupMessages);
        chatPanel.add(tf3);
        chatPanel.add(send);
        send.addActionListener(new ButtonPress());        
    }
    
    static class reciever extends Thread{
        public void run(){
            try{
                while(true){
                    groupMessages.append("\n" + listen_to_server.nextLine());
                } 
            }
            catch(Exception e){}
        }
    }
    
    static class ButtonPress implements ActionListener
    {  
        @Override
        public void actionPerformed(ActionEvent ae)
        {
           JButton jb = (JButton) ae.getSource();
           if(jb.equals(enterCredentials)){
               user = tf1.getText();
               ip = tf2.getText();
                //System.out.println(usernameVal +" " + ipAddress);
                try{
                    s = new Socket(ip, 5190);
                    jp.setVisible(false);
                    chatPanel.setVisible(true);
                    send_to_server = new PrintStream(s.getOutputStream());
                    listen_to_server = new Scanner(s.getInputStream());
                    groupMessages.setText("Welcome to the chat, "  + user );
                    send_to_server.println(user);
                    reciever updateMessages = new reciever();
                    updateMessages.start();
                  /*  if(s.isConnected())
                    {
                        send_to_server.print(user);
                    }*/
                }
                catch(IOException ie){
                    System.out.println("Cant connect to server");
                }
           }
           else if(jb.equals(send)){//when send
               String msg;
               msg = tf3.getText();
               send_to_server.println(msg);
               tf3.setText("");
               //groupMessages.append("\n"+ user + ": "+ msg);
           }
        }
    }
}

