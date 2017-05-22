/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homeworkfive;

import java.io.*;
import java.sql.*;
import java.util.*;
/**
 *
 * @author Jabid
 */
public class HomeworkFive {

    /**
     * @param args the command line arguments
     */
    static ArrayList<Character>characterInput;
    static ArrayList<Character>characterOutput;
    
    static String changeString(String s) //encryptor
    {
        String str ="";
        int indexInInputArrayList=-1;
        char charToBeAdded;
        
        for(int i= 0;i<s.length();i++)
        {
            char x = s.charAt(i);
            for(int j =0;j<characterInput.size();j++)
            {
                if(characterInput.get(j).equals(x))
                {
                    indexInInputArrayList = j;
                }
            }
            
            if(indexInInputArrayList == -1)
            {
                //Not on From/To columns
                charToBeAdded = x;
                str +=charToBeAdded;
            }
            else
            {
                charToBeAdded = characterOutput.get(indexInInputArrayList);
                str +=charToBeAdded;
                indexInInputArrayList = -1;
            }
        }
        return str;
    }

    
    public static void main(String[] args) throws IOException {
        characterInput= new ArrayList<Character>();
        characterOutput= new ArrayList<Character>();
        
        //DB stuff
        Connection conn=null; 
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String url = "jdbc:mysql://127.0.0.1/cs3913";
            String dbuser = "cs3913";
            String dbpass = "abc123";
            conn = DriverManager.getConnection(url,dbuser,dbpass);
            Statement s = conn.createStatement();
            s.executeQuery("select * from Map;");
            ResultSet rs = s.getResultSet();
            while(rs.next()){
                String From = rs.getString("From");
                String To = rs.getString("To");
                char cFrom = From.charAt(0);
                char cTo = To.charAt(0);
                characterInput.add(cFrom);
                characterOutput.add(cTo);
               // System.out.println(From+", "+To);
            }
            rs.close();
            s.close();
        }catch(Exception e){
            System.out.println("Connection Error: "+e.toString());
        }
        
        
        //User stuff
        System.out.println("What is the name of the file you wish to encrypt? (Example: input.txt )");
        Scanner sc = new Scanner(System.in);
        String inputFileName, outputFileName;
        inputFileName = sc.nextLine();
        
        System.out.println("What is the name of your output file? (Example: output.txt )");
        outputFileName= sc.nextLine();
        
        Scanner inFile = new Scanner(new File(inputFileName));
        String unencryptedInput;
        String encryptedInput;
        ArrayList<String> printIntoOut = new ArrayList<String>();
        
        while(inFile.hasNextLine()){
            unencryptedInput = inFile.nextLine();  
            encryptedInput = changeString(unencryptedInput);
            printIntoOut.add(encryptedInput);
        }
        
        //print into output file
        PrintStream ps = new PrintStream(outputFileName);
        for(int i=0;i<printIntoOut.size();i++)
        {
            ps.println(printIntoOut.get(i));
        }
            
        System.out.println("Encryption Complete");   
    }
    
}
