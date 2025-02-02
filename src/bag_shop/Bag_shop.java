/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package bag_shop;


public class Bag_shop {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Bag_shop.Home();
        
        
    }
    
     
    
    public static void M_Bags(){
        Manager_Bags m_bags=new Manager_Bags();
        m_bags.setVisible(true);
    }
    
    public static void Home(){
        Index index = new Index();
         index.setVisible(true);
    }
    
    public static void MLogin_access(){
        Manager_login mlogin=new Manager_login();
        mlogin.setVisible(true);
    }
    
    public static void cashier_login_access(){
        Cashier_Login clogin=new Cashier_Login();
        clogin.setVisible(true);
    }
}
