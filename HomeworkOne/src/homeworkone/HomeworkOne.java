/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homeworkone;

import java.io.*;
import java.util.*;

/**
 *
 * @author Jabid Methun - Homework 1
 */


public class HomeworkOne {
    static String changeString(String s, ArrayList<Integer> a) //is both encryptor and decryptor
    {
        String str ="";
        Boolean isupper = false;
        Boolean islower = false;
        int listCtr =0;
        for(int i= 0;i<s.length();i++)
        {
            char x = s.charAt(i);
            int val=0;
            
            if(Character.isUpperCase(x)==true)
            {
                isupper = true;
                val=(int)x-65;
                //System.out.println(val); //(prints out value from 0-25 where a =0 and 25 =z)
            }
            else if(Character.isLowerCase(x)==true) 
            {
                islower = true;
                val =(int) x - 97;
                //System.out.println(val); //(prints out value from 0-25)
            } 
            
            if(islower == true || isupper==true){
                val+= a.get(listCtr); //add key value to char value of unencrypted string to get encrypted char value
                //System.out.println(val + "now"); //(prints encrypted char value)
                
                if(val>25)
                    val= val-26;
                    //System.out.println(val + "now now"); //(if value of char + key char is greater than 25, subtract 26) = prints value in range 0-25 
            }
            
            
            if(isupper == true){
                x=(char)(val+65);
                isupper = false;
            }
            if(islower == true){
                x=(char)(val+97);
                islower = false;
            }
            
            //System.out.println(x); //(prints encrypted char value
            str +=x;
            listCtr++;
            if(listCtr >= a.size()) // if string to be encrypted is longer than string key, we must reset string key once at end
                listCtr =0;
        }
        return str;
    }
    
    static boolean checkString(String sk) {
        for (int i = 0; i < sk.length(); i++) {
            if (Character.isLetter(sk.charAt(i))==false) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)throws IOException {
        String prompt;
        //String free="";
        String stringKey;
        Scanner sn = new Scanner(System.in);
        System.out.println("What do you want to do?  Choose one of the following numbers: ");
        System.out.println("1 - encrypt a file          2 - decrpyt a file           3 - exit ");
        prompt = sn.nextLine();
        
        while(prompt.equals("3")==false && prompt.equals("2")==false && prompt.equals("1")==false)
        {
            System.out.print("Invalid choice. Choose number again: ");
            prompt = sn.nextLine();
        }
        
        //exit
        if(prompt.equals("3")){
            System.exit(0);
        }
        
        //ask for string key
        System.out.println("What is your string key? (Must contain only letters)");
        stringKey = sn.nextLine();
        while (checkString(stringKey)==false) {
            System.out.print("Invalid key. Enter another key: ");
            stringKey = sn.nextLine();
        }
        
        //ask for input/out file names
        String input;
        String output;
        System.out.println("What is the name of your input file? (Example: input.txt )");
        input = sn.nextLine();
        
        System.out.println("What is the name of your output file? (Example: output.txt )");
        output = sn.nextLine();
        
        //encrypt
        if(prompt.equals("1"))
        {
            ArrayList<Integer> key = new ArrayList<Integer>();
            for(int i =0;i < stringKey.length();i++)
            {
                char c = stringKey.charAt(i);
                if (Character.isLowerCase(c)) 
                {
                    key.add(((int) c - 97));
                } 
                else if (Character.isUpperCase(c)) 
                {
                    key.add((int) c - 65);
                }
            }
             //print out values in stringKey
           /* for(int i =0;i < stringKey.length();i++)
            {
                System.out.println(key.get(i));
            }*/


            Scanner inFile = new Scanner(new File(input));
            String unencryptedInput;
            String encryptedInput;
            ArrayList<String> printIntoOut = new ArrayList<String>();
            while(inFile.hasNextLine()){
                unencryptedInput = inFile.nextLine();  
                encryptedInput = changeString(unencryptedInput,key);
                printIntoOut.add(encryptedInput);
            }

            //print out encrypted value
           /* for(int i=0;i<printIntoOut.size();i++)
            {
                System.out.println(printIntoOut.get(i));
            } */


            //print into output file
            PrintStream ps = new PrintStream(output);
            for(int i=0;i<printIntoOut.size();i++)
            {
                ps.println(printIntoOut.get(i));
            }
            
            System.out.println("Encryption Complete");
        }
        
        //decrypt
        if(prompt.equals("2"))
        {
            ArrayList<Integer> key = new ArrayList<Integer>();
            for(int i =0;i < stringKey.length();i++)
            {
                char c = stringKey.charAt(i);
                if (Character.isLowerCase(c)) 
                {
                    key.add((int) (26-(c - 97)));
                } 
                else if (Character.isUpperCase(c)) 
                {
                    key.add((int)(26-(c - 65)));
                }
            }
            
            
            Scanner inFile = new Scanner(new File(input));
            String encryptedInput;
            String deencryptedInput;
            ArrayList<String> printIntoOut = new ArrayList<String>();
            while(inFile.hasNextLine()){
                encryptedInput = inFile.nextLine();  
                deencryptedInput = changeString(encryptedInput,key); //acts as a decryptor now
                printIntoOut.add(deencryptedInput);
            }
            
            PrintStream ps = new PrintStream(output);
            for(int i=0;i<printIntoOut.size();i++)
            {
                ps.println(printIntoOut.get(i));
            }
            
            System.out.println("Decryption Complete");

        }
    }
}
