package com.miniproject.heyjam.services.databaseServices;

import com.miniproject.heyjam.services.otherServices.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserInstitutionRelation {
    private String userProfileUsername;
    private String institutionProfileUniqueName;
    private String userInstitutionRelation_JoiningYear;
    private String userInstitutionRelation_Department;
    private int userInstitutionRelation_Status;

    public UserInstitutionRelation(String userProfileUsername, String institutionProfileUniqueName, String userInstitutionRelation_JoiningYear, String userInstitutionRelation_Department, int userInstitutionRelation_Status) {
        this.userProfileUsername = userProfileUsername;
        this.institutionProfileUniqueName = institutionProfileUniqueName;
        this.userInstitutionRelation_JoiningYear = userInstitutionRelation_JoiningYear;
        this.userInstitutionRelation_Department = userInstitutionRelation_Department;
        this.userInstitutionRelation_Status = userInstitutionRelation_Status;
    }

    public String getUserProfileUsername() {
        return userProfileUsername;
    }

    public void setUserProfileUsername(String userProfileUsername) {
        this.userProfileUsername = userProfileUsername;
    }

    public String getInstitutionProfileUniqueName() {
        return institutionProfileUniqueName;
    }

    public void setInstitutionProfileUniqueName(String institutionProfileUniqueName) {
        this.institutionProfileUniqueName = institutionProfileUniqueName;
    }

    public String getUserInstitutionRelation_JoiningYear() {
        return userInstitutionRelation_JoiningYear;
    }

    public void setUserInstitutionRelation_JoiningYear(String userInstitutionRelation_JoiningYear) {
        this.userInstitutionRelation_JoiningYear = userInstitutionRelation_JoiningYear;
    }

    public String getUserInstitutionRelation_Department() {
        return userInstitutionRelation_Department;
    }

    public void setUserInstitutionRelation_Department(String userInstitutionRelation_Department) {
        this.userInstitutionRelation_Department = userInstitutionRelation_Department;
    }

    public int getUserInstitutionRelation_Status() {
        return userInstitutionRelation_Status;
    }

    public void setUserInstitutionRelation_Status(int userInstitutionRelation_Status) {
        this.userInstitutionRelation_Status = userInstitutionRelation_Status;
    }

    public static int getParticipantCount(String uniqueName) throws SQLException, ClassNotFoundException {
        Connection connection = DatabaseConnector.getConnection();
        String query = "SELECT COUNT(*) AS total FROM userinstitutionrelation WHERE institutionProfileUniqueName=? AND userInstitutionRelation_Status=1";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1,uniqueName);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        return (rs.getInt("total"));
    }

    public static ArrayList<UserInstitutionRelation> getInstitutionParticipants(String institutionProfileUniqueName) throws SQLException, ClassNotFoundException {
        Connection connection  = DatabaseConnector.getConnection();
        String query = "SELECT * FROM userinstitutionrelation WHERE institutionProfileUniqueName=? AND userInstitutionRelation_Status=1";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1,institutionProfileUniqueName);
        ResultSet rs = stmt.executeQuery();
        ArrayList<UserInstitutionRelation> participants = new ArrayList<>();
        while(rs.next()){
            participants.add(
                    new UserInstitutionRelation(
                            rs.getString("userProfileUsername"),
                            rs.getString("institutionProfileUniqueName"),
                            rs.getString("userInstitutionRelation_JoiningYear"),
                            rs.getString("userInstitutionRelation_Department"),
                            rs.getInt("userInstitutionRelation_Status")
                    )
            );
        }
        return participants;
    }

    //0:not following    1:requested     2:following
    public static int userFollowStatus(String username,String uniqueName) throws SQLException, ClassNotFoundException {
        Connection connection = DatabaseConnector.getConnection();
        String query = "SELECT * FROM userinstitutionrelation WHERE userProfileUsername=? AND institutionProfileUniqueName=?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1,username);
        stmt.setString(2,uniqueName);
        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
            if(rs.getInt("userInstitutionRelation_Status")==0){
                return 1;
            }
            else{
                return 2;
            }
        }
        return 0;
    }

    public static ResultSet userInstitutions(String username) throws SQLException, ClassNotFoundException {
        Connection con = DatabaseConnector.getConnection();
        String query = "SELECT * FROM userinstitutionrelation WHERE userProfileUsername=? AND userInstitutionRelation_Status=1";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1,username);
        ResultSet rs = stmt.executeQuery();
        return rs;
    }

    public static UserInstitutionRelation getUserInstitutionRelationData(String username,String institutionProfileUniqueName) throws SQLException, ClassNotFoundException {
        Connection con = DatabaseConnector.getConnection();
        String query = "SELECT * FROM userinstitutionrelation WHERE userProfileUsername=? AND institutionProfileUniqueName=?";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1,username);
        stmt.setString(2,institutionProfileUniqueName);
        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
            return new UserInstitutionRelation(
                    rs.getString("userProfileUsername"),
                    rs.getString("institutionProfileUniqueName"),
                    rs.getString("userInstitutionRelation_JoiningYear"),
                    rs.getString("userInstitutionRelation_Department"),
                    rs.getInt("userInstitutionRelation_Status")
            );
        }
        return null;
    }

    public static boolean userInstitutionRelationCreateRequest(
            String userProfileUsername,
            String institutionProfileUniqueName,
            String userInstitutionRelation_JoiningYear,
            String userInstitutionRelation_Department) throws SQLException, ClassNotFoundException {
        Connection con = DatabaseConnector.getConnection();
        String query = "INSERT INTO userinstitutionrelation(userProfileUsername, " +
                "institutionProfileUniqueName, " +
                "userInstitutionRelation_JoiningYear, " +
                "userInstitutionRelation_Department, " +
                "userInstitutionRelation_Status) " +
                "VALUES (?,?,?,?,?)";
        PreparedStatement stmt= con.prepareStatement(query);
        stmt.setString(1,userProfileUsername);
        stmt.setString(2,institutionProfileUniqueName);
        stmt.setString(3,userInstitutionRelation_JoiningYear);
        stmt.setString(4,userInstitutionRelation_Department);
        stmt.setInt(5,0);
        int n = stmt.executeUpdate();
        return n!=0;
    }

    public static boolean userInstitutionRelationAcceptRequest(String institutionProfileUniqueName, String userProfileUsername) throws SQLException, ClassNotFoundException {
        Connection con = DatabaseConnector.getConnection();
        String query = "UPDATE userinstitutionrelation SET userInstitutionRelation_Status=1 WHERE institutionProfileUniqueName=? AND userProfileUsername=?";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1,institutionProfileUniqueName);
        stmt.setString(2,userProfileUsername);
        int n = stmt.executeUpdate();
        return n!=0;
    }

    public static boolean userInstitutionRelationRejectRequest(String institutionProfileUniqueName, String userProfileUsername) throws SQLException, ClassNotFoundException {
        Connection con = DatabaseConnector.getConnection();
        String query = "DELETE FROM userinstitutionrelation WHERE institutionProfileUniqueName=? AND userProfileUsername=?";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1,institutionProfileUniqueName);
        stmt.setString(2,userProfileUsername);
        int n = stmt.executeUpdate();
        return n!=0;
    }

    public static ResultSet getAllNotifications(String institutionProfileUniqueName) throws SQLException, ClassNotFoundException {
        Connection con = DatabaseConnector.getConnection();
        String query = "SELECT * FROM userinstitutionrelation WHERE institutionProfileUniqueName=? AND userInstitutionRelation_Status=0";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1,institutionProfileUniqueName);
        return stmt.executeQuery();
    }
}
