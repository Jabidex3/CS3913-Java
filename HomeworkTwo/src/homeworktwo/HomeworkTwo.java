/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homeworktwo;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
import java.awt.Color;

/**
 *
 * @author Jabid Methun - Homework #2
 */
public class HomeworkTwo 
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
        JFrame jf = new JFrame("Homework 2");
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
            jb[i].addActionListener(new ButtonPress());
        }
    }
    
static class ButtonPress implements ActionListener
{
    JButton[] buttonList = jb;
    @Override
    public void actionPerformed(ActionEvent ae)
    {
        JButton jb1 = (JButton) ae.getSource();
        for(int i =0;i < buttonList.length;i++)
        {
            if(buttonList[i].equals(jb1))
            {
            }
            else
            {
                buttonList[i].setBackground(colorPicker());
            }
        }        
    }
}
}

