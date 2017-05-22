/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homeworkthree;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author Jabid Methun - Homework 3
 */

public class HomeworkThree 
{
    static JButton[] jb;
    static Color colorPicker()
    {
        Random ran = new Random();
        
        int r = ran.nextInt(256);
        int g = ran.nextInt(256);
        int b = ran.nextInt(256);
        
        Color c = new Color(r,g,b);
        return c;
    }

    public static void main(String[] args) 
    {
        JFrame jf = new JFrame("Homework 3");
        jf.setSize(400,400);
        jf.setResizable(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
        
        JPanel jp = new JPanel();
        jf.add(jp);
        jp.setLayout(new GridLayout(4,2));   
       
        jb = new JButton[8];
        for(int i =0; i < 8;i++)
        {
            jb[i] = new JButton();
        }
       
        for(int i=0;i<jb.length;i++)
        {
           jp.add(jb[i]);
        }
        
        for(int i =0; i < jb.length; i++){
            jb[i].setBackground(colorPicker());
            ButtonPress bp = new ButtonPress(jb[i]);
            bp.start();
            jb[i].addActionListener(bp);
        }
    }
    
    static class ButtonPress extends Thread implements ActionListener
    {       
        JButton buttonThing;
        boolean pressed;
        ButtonPress(JButton aButton)
        {
            buttonThing = aButton;
            pressed = false;
        }

        @Override
        public void actionPerformed(ActionEvent ae)
        {
            if(pressed == false)
                pressed = true;
            else if(pressed == true)
            {
                pressed =false;
            }
        }

        public void run()
        {
               try {
                    boolean stopChange = false;
                    while(stopChange== false)
                    {
                        if(pressed == true)
                        {
                            ButtonPress.yield();
                        }
                        else{
                            buttonThing.setBackground(colorPicker()); 
                            sleep(1000); //1000millisec = 1 sec
                        }

                   }
                } catch (InterruptedException e) {}
        }   
           
    }
}



