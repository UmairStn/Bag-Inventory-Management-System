/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bag_shop;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class Cashier {
   
    
     public static void Add_cashier_popup(){
        Add_Cashier addc=new Add_Cashier();
        addc.setVisible(true);
    }
     
     public static void CashierD(){
        Cashier_Dashboard cd=new Cashier_Dashboard();
         cd.setVisible(true);
        }
    
     public static void Cashier_Login(String uname,String Password){
         
         boolean result=false;
       try{
           File f=new File("Cashiers.txt");
           Scanner reader=new Scanner(f);
           while(reader.hasNextLine()){
              String line=reader.nextLine().trim();
              String[] parts = line.split(",");
              
              if(parts[2].equals(uname) && parts[5].equals(Password)){
                  result=true;
                  break;
              }
           }
           
       }catch(IOException e){
           System.out.println(e);
       }
       
       if(result==true){
             Cashier.CashierD();
             
            
       }else{
           JOptionPane.showMessageDialog(null, "Login Failed! Incorrect Password.", "Error", JOptionPane.ERROR_MESSAGE);
           Bag_shop.cashier_login_access();
           
       }
    }
     
     public static void openPopupBags(){
         Add_Bag addbag=new Add_Bag();
         addbag.setVisible(true);
     }
     
     public static void addBags(String bagname,String price,String quantity,String category){
         
         int nextId = 1;  
boolean created = false;



 
try {
    FileWriter writer = new FileWriter("Bags.txt", true);
    writer.write( bagname + "," + price + "," + quantity + "," + category);
    writer.write("\n");
    writer.close();
    created = true;
} catch (IOException e) {
    JOptionPane.showMessageDialog(null, "Error writing to file: " + e.getMessage());
}

if (created) {
    
    JOptionPane.showMessageDialog(null, "Bag " + bagname + " added successfully!");
     
    
} else {
    JOptionPane.showMessageDialog(null, "Failed to add bag!");
}
     }
     
     
     //search bags
     public static void searchBags(String input,JTable table){
         try {
        File s = new File("Bags.txt");
        Scanner search = new Scanner(s);
        DefaultTableModel tblModel = (DefaultTableModel) table.getModel();

        // Clear existing rows
        tblModel.setRowCount(0);
        
        boolean found = false;
        while (search.hasNextLine()) {
            String line = search.nextLine().trim();
            String[] parts = line.split(",");
            
            // Check if search text matches the bag name or category
            if (input.equals(parts[0]) || input.equals(parts[3])) {
                tblModel.addRow(parts);
                found = true;
            }
        }
        
        if (!found) {
            JOptionPane.showMessageDialog(null, "No results found!");
        }

    } catch (Exception e) {
        System.out.println(e);
        JOptionPane.showMessageDialog(null, e);
    }
}
     
      
     
     
     public static void ViewBags(JTable table){
          try {
        File s = new File("Bags.txt");
        Scanner search = new Scanner(s);
        DefaultTableModel tblModel = (DefaultTableModel) table.getModel();

        // Clear existing rows
        tblModel.setRowCount(0);
        
        boolean found = false;
        while (search.hasNextLine()) {
            String line = search.nextLine().trim();
            String[] parts = line.split(",");
            
          
             
                tblModel.addRow(parts);
              
        }
        
       

    } catch (Exception e) {
        System.out.println(e);
        JOptionPane.showMessageDialog(null, e);
    }
     }
             
}

