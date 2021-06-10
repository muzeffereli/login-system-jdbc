package com.company.dao;

import com.company.PBKDF2;
import com.company.connect.ConMySql;
import com.company.entity.Contact;
import com.company.entity.User;
import java.sql.*;
import java.util.Scanner;

public class UserDAO {
    private final Connection con;
    Scanner sc=new Scanner(System.in);

    public UserDAO() {
        con=new ConMySql().getConnection();
    }
    User user=new User();
    Contact contact=new Contact();

    public void insertUser() throws SQLException {

        System.out.println("Enter user details:");

        while(true) {
            System.out.print("1.Username:");
            user.setUsername(sc.next());
            String sql="SELECT * from user_management.user_details WHERE username=?";
            PreparedStatement statement=con.prepareStatement(sql);
            statement.setString(1,user.getUsername());

            ResultSet rs=statement.executeQuery();

            if(!rs.next()){

                break;
            }
            System.out.println("This username already in use!");
        }



        System.out.print("2.Firstname:");
        user.setFirstname(sc.next());
        System.out.print("3.Lastname:");
        user.setLastname(sc.next());
        System.out.print("4.Password:");
        user.setPassword(PBKDF2.returnHashed(sc.next()));

        long millis=System.currentTimeMillis();
        java.sql.Date registeredDate=new java.sql.Date(millis);

        user.setRegisterdate(registeredDate);


        String sql="INSERT INTO user_details  (username,firstname,lastname,registerdate,password)"
                +" VALUES(?,?,?,?,?)";


        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1,user.getUsername());
        statement.setString(2,user.getFirstname());
        statement.setString(3,user.getLastname());
        statement.setDate(4,user.getRegisterdate());
        statement.setString(5,user.getPassword());


        int rows=statement.executeUpdate();


        if(rows>0){
            System.out.println("User created successfully.");
            System.out.println("Please enter contact details:");
            System.out.print("1.Phone number(necessary):");
            contact.setMainphonenumber(sc.next());
            System.out.print("2.Second phone number:");
            contact.setSecondphonenumber(sc.next());
            System.out.print("3.Third phone number:");
            contact.setThirdphonenumber(sc.next());

            while(true) {
                System.out.print("4.First email(necessary):");
                contact.setFirstemail(sc.next());
                String sqlForCheck="SELECT * from user_management.contact_details WHERE firstemail=?";
                PreparedStatement stm=con.prepareStatement(sqlForCheck);
                stm.setString(1,contact.getFirstemail());

                ResultSet rs1=stm.executeQuery();

                if(!rs1.next()){
                    break;
                }
                System.out.println("This email already in use!");

            }
            System.out.print("5.Second email:");
            contact.setSecondemail(sc.next());
            System.out.println("6.Residence place:");
            contact.setResidenceplace(sc.next());


            String sql2="SELECT * from user_management.user_details WHERE username=?";

            PreparedStatement statement2=con.prepareStatement(sql2);
            statement2.setString(1,user.getUsername());

            ResultSet rs=statement2.executeQuery();

            String sql3="INSERT INTO contact_details  (mainphonenumber,secondphonenumber,thirdphonenumber,firstemail,secondemail,residenceplace,userid)"
                    +" VALUES(?,?,?,?,?,?,?)";


            PreparedStatement stmt = con.prepareStatement(sql3);
            stmt.setString(1,contact.getMainphonenumber());
            stmt.setString(2,contact.getSecondphonenumber());
            stmt.setString(3,contact.getThirdphonenumber());
            stmt.setString(4,contact.getFirstemail());
            stmt.setString(5,contact.getSecondemail());
            stmt.setString(6,contact.getResidenceplace());
            while(rs.next()){
                stmt.setInt(7,rs.getInt("user_id"));
            }


           int secondrow= stmt.executeUpdate();
            if(secondrow>0) {
                System.out.println("Contact details added.");
            }else{
                System.out.println("Contact details could not been created.");
            }


        }else{
            System.out.println("User could not been created.");
        }


    }

    public void loginAccount() throws SQLException {

        System.out.print("Enter username:");
        String username = sc.next();
        System.out.print("Enter password:");
        String password = sc.next();


        String sql="SELECT * from user_management.user_details WHERE username=?";

        PreparedStatement statement=con.prepareStatement(sql);
        statement.setString(1,username);
        ResultSet rs=statement.executeQuery();

        user.setUsername(username);
        user.setPassword(password);


        boolean x=false;

        while(rs.next()){
            String sql2="SELECT * from user_management.contact_details WHERE userid=?";
            PreparedStatement statement2= con.prepareStatement(sql2);
            statement2.setInt(1,rs.getInt("user_id"));
            ResultSet rs2=statement2.executeQuery();

            if(rs.getString("password").equals(PBKDF2.returnHashed(password))){
                System.out.println("Welcome,"+username);
                while(true) {
                    System.out.println("1.Do you want to see details?");
                    System.out.println("2.Change password.");
                    System.out.println("3.Log out.");
                    System.out.print("Enter command:");
                    String cmd = sc.next();
                    if (cmd.equals("1")) {
                        System.out.println("User details:\n");
                        System.out.println("Username:" + rs.getString("username"));
                        System.out.println("Firstname:" + rs.getString("firstname"));
                        System.out.println("Lastname:" + rs.getString("lastname"));
                        System.out.println("Registered date:" + rs.getString("registerdate")+"\n");
                        while (rs2.next()) {
                            System.out.println("Contact details:\n");
                            System.out.println("Main phone number:" + rs2.getString("mainphonenumber"));
                            System.out.println("Second phone number:" + rs2.getString("secondphonenumber"));
                            System.out.println("Third phone number:" + rs2.getString("thirdphonenumber"));
                            System.out.println("First email:" + rs2.getString("firstemail"));
                            System.out.println("Second email:" + rs2.getString("secondemail"));
                            System.out.println("Residence place:" + rs2.getString("residenceplace")+"\n");

                        }

                    } else if (cmd.equals("3")) {
                        System.out.println("Logged out");
                        break;
                    } else if(cmd.equals("2")){
                        System.out.print("Enter username:");
                        String usernm =sc.next();
                        if(user.getUsername().equals(usernm)){
                            System.out.println("Enter old password:");
                            String oldpass =sc.next();
                            if(user.getPassword().equals(oldpass)){
                                System.out.println("Enter new password:");
                                String newpass =sc.next();
                                System.out.println("Enter new password:");
                                String newpass2 =sc.next();
                                if(newpass.equals(newpass2)){
                                    user.setPassword(PBKDF2.returnHashed(newpass));
                                    String sqlUpdate="UPDATE user_details SET password=? where username=? ";
                                    PreparedStatement stmt2=con.prepareStatement(sqlUpdate);
                                    stmt2.setString(1,user.getPassword());
                                    stmt2.setString(2,user.getUsername());
                                    stmt2.executeUpdate();
                                    System.out.println("Password changed successfully");
                                }
                            }
                        }
                    }
                    else {
                        System.out.println("There is no such a command.");
                    }
                }
            }else{
                System.out.println("Wrong password.");
            }
            x=true;
        }

        //This code is for checking is there username or not
        if(!x){
            System.out.println("There is no account for this username");
        }
    }

}
