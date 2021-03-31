package com.miniproject.heyjam.services.databaseServices;

import com.miniproject.heyjam.services.otherServices.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserUserRelation {
    private int userUserRelation_id;
    private String userProfile_Username_follower;
    private String userProfile_Username_followee;
    private int getUserUserRelation_Status;

    public UserUserRelation(int userUserRelation_id, String userProfile_Username_follower, String userProfile_Username_followee, int getUserUserRelation_Status) {
        this.userUserRelation_id = userUserRelation_id;
        this.userProfile_Username_follower = userProfile_Username_follower;
        this.userProfile_Username_followee = userProfile_Username_followee;
        this.getUserUserRelation_Status = getUserUserRelation_Status;
    }


    public int getUserUserRelation_id() {
        return userUserRelation_id;
    }

    public void setUserUserRelation_id(int userUserRelation_id) {
        this.userUserRelation_id = userUserRelation_id;
    }

    public String getUserProfile_Username_follower() {
        return userProfile_Username_follower;
    }

    public void setUserProfile_Username_follower(String userProfile_Username_follower) {
        this.userProfile_Username_follower = userProfile_Username_follower;
    }

    public String getUserProfile_Username_followee() {
        return userProfile_Username_followee;
    }

    public void setUserProfile_Username_followee(String userProfile_Username_followee) {
        this.userProfile_Username_followee = userProfile_Username_followee;
    }

    public int getGetUserUserRelation_Status() {
        return getUserUserRelation_Status;
    }

    public void setGetUserUserRelation_Status(int getUserUserRelation_Status) {
        this.getUserUserRelation_Status = getUserUserRelation_Status;
    }

    public static boolean userUserRelationCreateRequest(String userProfile_Username_follower,String userProfile_Username_followee) throws SQLException, ClassNotFoundException {
        Connection connection = DatabaseConnector.getConnection();
        String query = "INSERT INTO useruserrelation VALUE(0,?,?,0)";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1,userProfile_Username_follower);
        stmt.setString(2,userProfile_Username_followee);
        int n = stmt.executeUpdate();
        return n!=0;
    }

    public static ArrayList<UserUserRelation> userUserRelationUserRequests(String userProfile_Username_followee) throws SQLException, ClassNotFoundException {
        Connection connection = DatabaseConnector.getConnection();
        String query = "SELECT * FROM useruserrelation WHERE userProfile_Username_followee=? AND userUserRelation_Status=0";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1,userProfile_Username_followee);
        ResultSet rs = stmt.executeQuery();
        ArrayList<UserUserRelation> followRequests = new ArrayList<>();
        while(rs.next()){
            followRequests.add(new UserUserRelation(
                    rs.getInt("userUserRelation_id"),
                    rs.getString("userProfile_Username_follower"),
                    rs.getString("userProfile_Username_followee"),
                    rs.getInt("userUserRelation_Status"))
            );
        }
        return followRequests;
    }

    public static boolean userUserRelationRejectRequest(String userProfile_Username_follower,String userProfile_Username_followee) throws SQLException, ClassNotFoundException {
        Connection connection = DatabaseConnector.getConnection();
        String query = "DELETE FROM useruserrelation WHERE userProfile_Username_follower=? AND userProfile_Username_followee=?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1,userProfile_Username_follower);
        stmt.setString(2,userProfile_Username_followee);
        int n = stmt.executeUpdate();
        return n!=0;
    }

    public static boolean userUserRelationAcceptRequest(String userProfile_Username_follower,String userProfile_Username_followee) throws SQLException, ClassNotFoundException {
        Connection connection = DatabaseConnector.getConnection();
        String query = "UPDATE useruserrelation SET userUserRelation_Status=1 WHERE userProfile_Username_follower=? AND userProfile_Username_followee=?";
        PreparedStatement stmt= connection.prepareStatement(query);
        stmt.setString(1,userProfile_Username_follower);
        stmt.setString(2,userProfile_Username_followee);
        int n = stmt.executeUpdate();
        return n!=0;
    }

    public static ResultSet userUserRelationFollowSet(String userProfile_Username_follower,String userProfile_Username_followee) throws SQLException, ClassNotFoundException {
        Connection connection = DatabaseConnector.getConnection();
        String query = "SELECT * FROM useruserrelation WHERE userProfile_Username_follower=? AND userProfile_Username_followee=?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1,userProfile_Username_follower);
        stmt.setString(2,userProfile_Username_followee);
        ResultSet rs = stmt.executeQuery();
        return rs;
    }

    //1: following  2: requested  3: follow back 4: accept  5: follow
    public static int userUserRelationFollowStatus(String userProfile_Username_follower,String userProfile_Username_followee) throws SQLException, ClassNotFoundException {
        ResultSet forward = userUserRelationFollowSet(userProfile_Username_follower,userProfile_Username_followee);
        ResultSet backward = userUserRelationFollowSet(userProfile_Username_followee,userProfile_Username_follower);
        int followStatus=5;
        if(forward.next()){
            if(forward.getInt("userUserRelation_Status")==1){
                followStatus = 1;
            }else{
                followStatus = 2;
            }
        }
        else if(backward.next()){
            if(backward.getInt("userUserRelation_Status")==1){
                followStatus = 3;
            }else{
                followStatus = 4;
            }
        }else{
            followStatus = 5;
        }
        return followStatus;
    }

    public static ArrayList<UserUserRelation> userFollowing(String username) throws SQLException, ClassNotFoundException {
        Connection con = DatabaseConnector.getConnection();
        String query = "SELECT * FROM useruserrelation WHERE userProfile_Username_follower=? AND userUserRelation_Status=1";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1,username);
        ResultSet rs = stmt.executeQuery();
        ArrayList<UserUserRelation> following = new ArrayList<>();
        while(rs.next()){
            following.add(
                    new UserUserRelation(
                            rs.getInt("userUserRelation_id"),
                            rs.getString("userProfile_Username_follower"),
                            rs.getString("userProfile_Username_followee"),
                            rs.getInt("userUserRelation_Status")
                    )
            );
        }
        return following;
    }

    public static ArrayList<UserUserRelation> userFollower(String username) throws SQLException, ClassNotFoundException {
        Connection con = DatabaseConnector.getConnection();
        String query = "SELECT * FROM useruserrelation WHERE userProfile_Username_followee=? AND userUserRelation_Status=1";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1,username);
        ResultSet rs = stmt.executeQuery();
        ArrayList<UserUserRelation> following = new ArrayList<>();
        while(rs.next()){
            following.add(
                    new UserUserRelation(
                            rs.getInt("userUserRelation_id"),
                            rs.getString("userProfile_Username_follower"),
                            rs.getString("userProfile_Username_followee"),
                            rs.getInt("userUserRelation_Status")
                    )
            );
        }
        return following;
    }
}

