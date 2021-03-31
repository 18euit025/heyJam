package com.miniproject.heyjam.services.databaseServices;

import com.miniproject.heyjam.services.otherServices.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InstitutionSurveyVotes {
    private int institutionSurveyVoteId;
    private int institutionSurveyId;
    private String userProfileUsername;
    private String institutionSurveyVoteOption;

    public InstitutionSurveyVotes(int institutionSurveyVoteId, int institutionSurveyId, String userProfileUsername, String institutionSurveyVoteOption) {
        this.institutionSurveyVoteId = institutionSurveyVoteId;
        this.institutionSurveyId = institutionSurveyId;
        this.userProfileUsername = userProfileUsername;
        this.institutionSurveyVoteOption = institutionSurveyVoteOption;
    }

    public int getInstitutionSurveyVoteId() {
        return institutionSurveyVoteId;
    }

    public void setInstitutionSurveyVoteId(int institutionSurveyVoteId) {
        this.institutionSurveyVoteId = institutionSurveyVoteId;
    }

    public int getInstitutionSurveyId() {
        return institutionSurveyId;
    }

    public void setInstitutionSurveyId(int institutionSurveyId) {
        this.institutionSurveyId = institutionSurveyId;
    }

    public String getUserProfileUsername() {
        return userProfileUsername;
    }

    public void setUserProfileUsername(String userProfileUsername) {
        this.userProfileUsername = userProfileUsername;
    }

    public String getInstitutionSurveyVoteOption() {
        return institutionSurveyVoteOption;
    }

    public void setInstitutionSurveyVoteOption(String institutionSurveyVoteOption) {
        this.institutionSurveyVoteOption = institutionSurveyVoteOption;
    }

    public static InstitutionSurveyVotes isVoted(int institutionSurveyId,String userProfileUsername) throws SQLException, ClassNotFoundException {
        Connection con = DatabaseConnector.getConnection();
        String query = "SELECT * FROM institutionsurveyvotes WHERE institutionSurvey_id=? AND userProfile_Username=?";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setInt(1,institutionSurveyId);
        stmt.setString(2,userProfileUsername);
        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
            return new InstitutionSurveyVotes(
                    rs.getInt("institutionSurveyVotes_id"),
                    rs.getInt("institutionSurvey_id"),
                    rs.getString("userProfile_Username"),
                    rs.getString("institutionSurveyVote_Option")
            );
        }
        return null;
    }

    public static boolean voteSurvey(int institutionSurveyId,String userProfileUsername,String institutionSurveyVoteOption) throws SQLException, ClassNotFoundException {
        Connection con = DatabaseConnector.getConnection();
        String query = "INSERT INTO institutionsurveyvotes VALUE(0,?,?,?)";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setInt(1,institutionSurveyId);
        stmt.setString(2,userProfileUsername);
        stmt.setString(3,institutionSurveyVoteOption);
        return stmt.executeUpdate()!=0;
    }

    public static int voteCount(int institutionSurveyId,String institutionSurveyVoteOption) throws SQLException, ClassNotFoundException {
        Connection con = DatabaseConnector.getConnection();
        String query = "SELECT COUNT(*) AS total FROM institutionsurveyvotes WHERE institutionSurvey_id=? AND institutionSurveyVote_Option=?";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setInt(1,institutionSurveyId);
        stmt.setString(2,institutionSurveyVoteOption);
        ResultSet rs  = stmt.executeQuery();
        if(rs.next()){
            return rs.getInt("total");
        }
        return 0;
    }

}

