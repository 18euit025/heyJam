package com.miniproject.heyjam.services.databaseServices;

import com.miniproject.heyjam.services.otherServices.DatabaseConnector;
import com.miniproject.heyjam.services.otherServices.PasswordSHA256;

import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class InstitutionProfile {
    private String institutionProfile_UniqueName;
    private String institutionProfile_Name;
    private String institutionProfile_Description;
    private String institutionProfile_Email;
    private String institutionProfile_Phone;
    private int institutionProfile_Status;
    private String institutionProfile_CreationDate;

    public InstitutionProfile(String institutionProfile_UniqueName, String institutionProfile_Name, String institutionProfile_Description, String institutionProfile_Email, String institutionProfile_Phone,int institutionProfile_Status, String institutionProfile_CreationDate) {
        this.institutionProfile_UniqueName = institutionProfile_UniqueName;
        this.institutionProfile_Name = institutionProfile_Name;
        this.institutionProfile_Description = institutionProfile_Description;
        this.institutionProfile_Email = institutionProfile_Email;
        this.institutionProfile_Phone = institutionProfile_Phone;
        this.institutionProfile_Status = institutionProfile_Status;
        this.institutionProfile_CreationDate = institutionProfile_CreationDate;
    }

    @Override
    public String toString() {
        return "InstitutionProfile{" +
                "institutionProfile_UniqueName='" + institutionProfile_UniqueName + '\'' +
                ", institutionProfile_Name='" + institutionProfile_Name + '\'' +
                ", institutionProfile_Description='" + institutionProfile_Description + '\'' +
                ", institutionProfile_Email='" + institutionProfile_Email + '\'' +
                ", institutionProfile_Phone='" + institutionProfile_Phone + '\'' +
                ", institutionProfile_Status=" + institutionProfile_Status +
                ", institutionProfile_CreationDate='" + institutionProfile_CreationDate + '\'' +
                '}';
    }

    public String getInstitutionProfile_UniqueName() {
        return institutionProfile_UniqueName;
    }

    public void setInstitutionProfile_UniqueName(String institutionProfile_UniqueName) {
        this.institutionProfile_UniqueName = institutionProfile_UniqueName;
    }

    public String getInstitutionProfile_Name() {
        return institutionProfile_Name;
    }

    public void setInstitutionProfile_Name(String institutionProfile_Name) {
        this.institutionProfile_Name = institutionProfile_Name;
    }

    public String getInstitutionProfile_Description() {
        return institutionProfile_Description;
    }

    public void setInstitutionProfile_Description(String institutionProfile_Description) {
        this.institutionProfile_Description = institutionProfile_Description;
    }

    public String getInstitutionProfile_Email() {
        return institutionProfile_Email;
    }

    public void setInstitutionProfile_Email(String institutionProfile_Email) {
        this.institutionProfile_Email = institutionProfile_Email;
    }

    public String getInstitutionProfile_Phone() {
        return institutionProfile_Phone;
    }

    public void setInstitutionProfile_Phone(String institutionProfile_Phone) {
        this.institutionProfile_Phone = institutionProfile_Phone;
    }

    public int getInstitutionProfile_Status() {
        return institutionProfile_Status;
    }

    public void setInstitutionProfile_Status(int institutionProfile_Status) {
        this.institutionProfile_Status = institutionProfile_Status;
    }

    public String getInstitutionProfile_CreationDate() {
        return institutionProfile_CreationDate;
    }

    public void setInstitutionProfile_CreationDate(String institutionProfile_CreationDate) {
        this.institutionProfile_CreationDate = institutionProfile_CreationDate;
    }

    public static InstitutionProfile createInstitutionProfile(
            String institutionProfile_UniqueName,
            String institutionProfile_Name,
            String institutionProfile_Description,
            String institutionProfile_Email,
            String institutionProfile_Password,
            String institutionProfile_Phone,
            int institutionProfile_status,
            String institutionProfile_ActivationKey,
            String institutionProfile_CreationDate) throws SQLException, ClassNotFoundException, NoSuchAlgorithmException {
        Connection connection = DatabaseConnector.getConnection();
        String query = "INSERT INTO institutionprofile(" +
                "institutionProfile_UniqueName," +
                "institutionProfile_Name," +
                "institutionProfile_Description," +
                "institutionProfile_Email," +
                "institutionProfile_Password," +
                "institutionProfile_Phone," +
                "institutionProfile_Status," +
                "institutionProfile_ActivationKey," +
                "institutionProfile_CreationDate)" +
                "VALUES(?,?,?,?,?,?,?,?,?)";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1,institutionProfile_UniqueName);
        stmt.setString(2,institutionProfile_Name);
        stmt.setString(3,institutionProfile_Description);
        stmt.setString(4,institutionProfile_Email);
        stmt.setString(5, PasswordSHA256.getPassword(institutionProfile_Password));
        stmt.setString(6,institutionProfile_Phone);
        stmt.setInt(7,institutionProfile_status);
        stmt.setString(8,institutionProfile_ActivationKey);
        stmt.setString(9,institutionProfile_CreationDate);
        int rs = stmt.executeUpdate();
        if(rs==1){
            return new InstitutionProfile(
                    institutionProfile_UniqueName,
                    institutionProfile_Name,
                    institutionProfile_Description,
                    institutionProfile_Email,
                    institutionProfile_Phone,
                    institutionProfile_status,
                    institutionProfile_CreationDate
            );
        }
        return null;
    }

    public static InstitutionProfile getInstitutionProfile(String institutionProfile_UniqueName) throws SQLException, ClassNotFoundException {
        Connection connection = DatabaseConnector.getConnection();
        String query = "SELECT * FROM institutionprofile WHERE institutionProfile_UniqueName='"+institutionProfile_UniqueName+"'";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        if(rs.next()){
            return new InstitutionProfile(
                    rs.getString("institutionProfile_UniqueName"),
                    rs.getString("institutionProfile_Name"),
                    rs.getString("institutionProfile_Description"),
                    rs.getString("institutionProfile_Email"),
                    rs.getString("institutionProfile_Phone"),
                    rs.getInt("institutionProfile_Status"),
                    rs.getString("institutionProfile_CreationDate")
            );
        }
        return null;
    }

    public static InstitutionProfile verifyInstitutionProfile(String institutionProfile_UniqueName,String institutionProfile_Password) throws SQLException, ClassNotFoundException, NoSuchAlgorithmException {
        Connection connection = DatabaseConnector.getConnection();
        String query = "SELECT * FROM institutionprofile WHERE (institutionProfile_UniqueName=? AND institutionProfile_Password=?)";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1,institutionProfile_UniqueName);
        stmt.setString(2,PasswordSHA256.getPassword(institutionProfile_Password));
        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
            return new InstitutionProfile(
                    rs.getString("institutionProfile_UniqueName"),
                    rs.getString("institutionProfile_Name"),
                    rs.getString("institutionProfile_Description"),
                    rs.getString("institutionProfile_Email"),
                    rs.getString("institutionProfile_Phone"),
                    rs.getInt("institutionProfile_Status"),
                    rs.getString("institutionProfile_CreationDate")
            );
        }
        return null;
    }

    public static boolean activateCheckInstitution(String uniqueName, String activationKey) throws SQLException, ClassNotFoundException {
        Connection connection = DatabaseConnector.getConnection();
        String query = "SELECT * FROM institutionprofile WHERE (institutionProfile_UniqueName=? AND institutionProfile_ActivationKey=?)";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1,uniqueName);
        stmt.setString(2,activationKey);
        ResultSet rs = stmt.executeQuery();
        return rs.next();
    }

    public static boolean activateInstitution(String uniqueName) throws SQLException, ClassNotFoundException {
        Connection connection = DatabaseConnector.getConnection();
        String query = "UPDATE institutionprofile SET institutionProfile_Status=1 WHERE institutionProfile_UniqueName='"+uniqueName+"'";
        Statement stmt = connection.createStatement();
        int n = stmt.executeUpdate(query);
        return n!=0;
    }
}

