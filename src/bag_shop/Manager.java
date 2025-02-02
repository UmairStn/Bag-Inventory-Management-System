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
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


//manager login


public class Manager {
    // Class-level private static variable for password
    private static String password = "password";

    // Getter for password (Encap)
    public static String getPassword() {
        return password;
    }

    // Setter for password 
    public static void setPassword(String newPassword) {
        password = newPassword;
    }

    // Manager Login method
    public static void Manager_Login(String pswd) {
        // Use the private password variable for validation
        if (password.equals(pswd)) {
            Manager.Manager(); // Navigate to the Manager dashboard
        } else {
            JOptionPane.showMessageDialog(null, "Login Failed! Incorrect Password.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Manager dashboard display
    public static void Manager() {
        M_Dashboard mdashboard = new M_Dashboard();
        mdashboard.setVisible(true);
    }

    // Create cashier
    public static void Create_cashier(String fname, String lname, String email,
                                      String Phone, String Ad1, String Ad2, String paswd, String cpaswd) {
        if (fname.isEmpty() || lname.isEmpty() || email.isEmpty() || Phone.isEmpty() ||
                Ad1.isEmpty() || Ad2.isEmpty() || paswd.isEmpty() || cpaswd.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Fill All the Fields", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!paswd.equals(cpaswd)) {
            JOptionPane.showMessageDialog(null, "Password Doesn't Match!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int NewId = 1;
        File file = new File("Cashiers.txt");

        try {
            // Create the file if it doesn't exist
            if (!file.exists()) {
                file.createNewFile();
            }

            // Read the last ID from the file
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String lastLine = null, line;
                while ((line = reader.readLine()) != null) {
                    lastLine = line;
                }

                if (lastLine != null && !lastLine.trim().isEmpty()) {
                    String[] parts = lastLine.split(",");
                    if (parts.length > 0) {
                        NewId = Integer.parseInt(parts[0]) + 1;
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error reading or parsing file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Append the new cashier to the file
        try (FileWriter writer = new FileWriter(file, true)) {
            writer.write(NewId + "," + fname + " " + lname + "," + email + "," + Phone + "," + Ad1 + " " + Ad2 + "," + paswd + "\n");
            JOptionPane.showMessageDialog(null, "Account " + fname + " " + lname + " Added Successfully");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error writing to file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Clear Data
    public static void Clear_Data(JTextField fnameField, JTextField lnameField,
                                  JTextField emailField, JTextField phoneField,
                                  JTextField address1Field, JTextField address2Field,
                                  JPasswordField passwordField, JPasswordField confirmPasswordField) {
        fnameField.setText("");
        lnameField.setText("");
        emailField.setText("");
        phoneField.setText("");
        address1Field.setText("");
        address2Field.setText("");
        passwordField.setText("");
        confirmPasswordField.setText("");
    }

    public static void SearchCashier(String input, JTable table) {
        boolean results = false;

        try (Scanner search = new Scanner(new File("Cashiers.txt"))) {
            DefaultTableModel tblModel = (DefaultTableModel) table.getModel();
            tblModel.setRowCount(0); // Clear the table before adding results

            while (search.hasNextLine()) {
                String line = search.nextLine().trim();
                String[] parts = line.split(",");

                if (parts.length >= 5 && (parts[1].equals(input) || parts[2].equals(input) || parts[3].equals(input) || parts[4].equals(input))) {
                    tblModel.addRow(parts);
                    results = true;
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        if (!results) {
            JOptionPane.showMessageDialog(null, "No Results Found!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void View_Cashier(JTable table) {
        try (Scanner search = new Scanner(new File("Cashiers.txt"))) {
            DefaultTableModel tblModel = (DefaultTableModel) table.getModel();
            tblModel.setRowCount(0); // Clear the table before adding all cashiers

            while (search.hasNextLine()) {
                String line = search.nextLine().trim();
                String[] parts = line.split(",");
                tblModel.addRow(parts);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

