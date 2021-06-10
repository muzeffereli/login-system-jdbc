package com.company;

import com.company.dao.UserDAO;
import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        UserDAO userDAO=new UserDAO();
        while(true) {
            System.out.println("1.Do you have an account?Login.");
            System.out.println("2.Don't you have an account?Sign Up.");
            System.out.println("3.Finish the program.");
            System.out.print("Enter command:");
            Scanner sc = new Scanner(System.in);
            String command = sc.next();
            try {
                if (command.equals("1")) {
                    userDAO.loginAccount();
                } else if (command.equals("2")) {
                    userDAO.insertUser();
                } else if(command.equals("3")){
                    System.out.println("Finishing the progarm...");
                    break;
                }
                else {
                    System.out.println("Not such a command.");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
