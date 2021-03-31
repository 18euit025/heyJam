package com.miniproject.heyjam.services.databaseServices;

import com.miniproject.heyjam.services.otherServices.DatabaseConnector;
import com.miniproject.heyjam.services.otherServices.PasswordSHA256;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserProfile {
    private String userProfile_Username;
    private String userProfile_Name;
    private String userProfile_Email;
    private String userProfile_Dob;
    private String userProfile_Gender;
    private String userProfile_Phone;

    public UserProfile(String userProfile_Username, String userProfile_Name, String userProfile_Email, String userProfile_Dob, String userProfile_Gender, String userProfile_Phone) {
        this.userProfile_Username = userProfile_Username;
        this.userProfile_Name = userProfile_Name;
        this.userProfile_Email = userProfile_Email;
        this.userProfile_Dob = userProfile_Dob;
        this.userProfile_Gender = userProfile_Gender;
        this.userProfile_Phone = userProfile_Phone;
    }

    public String getUserProfile_Username() {
        return userProfile_Username;
    }

    public void setUserProfile_Username(String userProfile_Username) { this.userProfile_Username = userProfile_Username; }

    public String getUserProfile_Name() {
        return userProfile_Name;
    }

    public void setUserProfile_Name(String userProfile_Name) {
        this.userProfile_Name = userProfile_Name;
    }

    public String getUserProfile_Email() {
        return userProfile_Email;
    }

    public void setUserProfile_Email(String userProfile_Email) {
        this.userProfile_Email = userProfile_Email;
    }

    public String getUserProfile_Dob() {
        return userProfile_Dob;
    }

    public void setUserProfile_Dob(String userProfile_Dob) {
        this.userProfile_Dob = userProfile_Dob;
    }

    public String getUserProfile_Gender() {
        return userProfile_Gender;
    }

    public void setUserProfile_Gender(String userProfile_Gender) {
        this.userProfile_Gender = userProfile_Gender;
    }

    public String getUserProfile_Phone() {
        return userProfile_Phone;
    }

    public void setUserProfile_Phone(String userProfile_Phone) {
        this.userProfile_Phone = userProfile_Phone;
    }

    public static UserProfile createUserProfile(String userProfile_Username,String userProfile_Name,String userProfile_Email,String userProfile_Dob,String userProfile_Gender,String userProfile_Phone,String userProfile_Password) throws SQLException, ClassNotFoundException, NoSuchAlgorithmException {
        Connection connection = DatabaseConnector.getConnection();
        String query = "INSERT INTO userprofile(" +
                "userProfile_Username," +
                "userProfile_Name," +
                "userProfile_Email," +
                "userProfile_Dob," +
                "userProfile_Gender," +
                "userProfile_Phone," +
                "userProfile_Password)" +
                "VALUE(?,?,?,?,?,?,?)";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1,userProfile_Username);
        stmt.setString(2,userProfile_Name);
        stmt.setString(3,userProfile_Email);
        stmt.setString(4,userProfile_Dob);
        stmt.setString(5,userProfile_Gender);
        stmt.setString(6,userProfile_Phone);
        stmt.setString(7, PasswordSHA256.getPassword(userProfile_Password));
        int rs  = stmt.executeUpdate();
        if(rs==1){
            return new UserProfile(
                    userProfile_Username,
                    userProfile_Name,
                    userProfile_Email,
                    userProfile_Dob,
                    userProfile_Gender,
                    userProfile_Phone
            );
        }
        return null;
    }

    public static UserProfile getUserProfile(String userProfile_Username) throws SQLException, ClassNotFoundException {
        Connection connection = DatabaseConnector.getConnection();
        String query = "SELECT * FROM userprofile WHERE userProfile_Username=?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1,userProfile_Username);
        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
            return new UserProfile(
                    rs.getString("userProfile_Username"),
                    rs.getString("userProfile_Name"),
                    rs.getString("userProfile_Email"),
                    rs.getString("userProfile_Dob"),
                    rs.getString("userProfile_Gender"),
                    rs.getString("userProfile_Phone")
            );
        }
        return null;
    }

    public static UserProfile verifyUserProfile(String userProfile_Username,String userProfile_Password) throws SQLException, ClassNotFoundException, NoSuchAlgorithmException {
        Connection connection = DatabaseConnector.getConnection();
        String query = "SELECT * FROM userprofile WHERE (userProfile_Username=? AND userProfile_Password=?)";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1,userProfile_Username);
        stmt.setString(2,PasswordSHA256.getPassword(userProfile_Password));
        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
            return new UserProfile(
                    rs.getString("userProfile_Username"),
                    rs.getString("userProfile_Name"),
                    rs.getString("userProfile_Email"),
                    rs.getString("userProfile_Dob"),
                    rs.getString("userProfile_Gender"),
                    rs.getString("userProfile_Phone")
            );
        }
        return null;
    }
}

