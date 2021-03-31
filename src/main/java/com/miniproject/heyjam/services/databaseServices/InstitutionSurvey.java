package com.miniproject.heyjam.services.databaseServices;

import com.miniproject.heyjam.services.otherServices.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InstitutionSurvey {
    private int institutionSurvey_id;
    private String institutionProfile_UniqueName;
    private String institutionSurvey_Title;
    private String institutionSurvey_Content;
    private String institutionSurvey_ExpiryDate;
    private String institutionSurvey_TargetRangeTo;
    private String institutionSurvey_OptionA;
    private String institutionSurvey_OptionB;
    private String institutionSurvey_CreationDate;

    public InstitutionSurvey(int institutionSurvey_id, String institutionProfile_UniqueName, String institutionSurvey_Title, String institutionSurvey_Content, String institutionSurvey_ExpiryDate, String institutionSurvey_TargetRangeTo, String institutionSurvey_OptionA, String institutionSurvey_OptionB, String institutionSurvey_CreationDate) {
        this.institutionSurvey_id = institutionSurvey_id;
        this.institutionProfile_UniqueName = institutionProfile_UniqueName;
        this.institutionSurvey_Title = institutionSurvey_Title;
        this.institutionSurvey_Content = institutionSurvey_Content;
        this.institutionSurvey_ExpiryDate = institutionSurvey_ExpiryDate;
        this.institutionSurvey_TargetRangeTo = institutionSurvey_TargetRangeTo;
        this.institutionSurvey_OptionA = institutionSurvey_OptionA;
        this.institutionSurvey_OptionB = institutionSurvey_OptionB;
        this.institutionSurvey_CreationDate = institutionSurvey_CreationDate;
    }

    public int getInstitutionSurvey_id() {
        return institutionSurvey_id;
    }

    public void setInstitutionSurvey_id(int institutionSurvey_id) {
        this.institutionSurvey_id = institutionSurvey_id;
    }

    public String getInstitutionProfile_UniqueName() {
        return institutionProfile_UniqueName;
    }

    public void setInstitutionProfile_UniqueName(String institutionProfile_UniqueName) {
        this.institutionProfile_UniqueName = institutionProfile_UniqueName;
    }

    public String getInstitutionSurvey_Title() {
        return institutionSurvey_Title;
    }

    public void setInstitutionSurvey_Title(String institutionSurvey_Title) {
        this.institutionSurvey_Title = institutionSurvey_Title;
    }

    public String getInstitutionSurvey_Content() {
        return institutionSurvey_Content;
    }

    public void setInstitutionSurvey_Content(String institutionSurvey_Content) {
        this.institutionSurvey_Content = institutionSurvey_Content;
    }

    public String getInstitutionSurvey_ExpiryDate() {
        return institutionSurvey_ExpiryDate;
    }

    public void setInstitutionSurvey_ExpiryDate(String institutionSurvey_ExpiryDate) {
        this.institutionSurvey_ExpiryDate = institutionSurvey_ExpiryDate;
    }

    public String getInstitutionSurvey_TargetRangeTo() {
        return institutionSurvey_TargetRangeTo;
    }

    public void setInstitutionSurvey_TargetRangeTo(String institutionSurvey_TargetRangeTo) {
        this.institutionSurvey_TargetRangeTo = institutionSurvey_TargetRangeTo;
    }

    public String getInstitutionSurvey_OptionA() {
        return institutionSurvey_OptionA;
    }

    public void setInstitutionSurvey_OptionA(String institutionSurvey_OptionA) {
        this.institutionSurvey_OptionA = institutionSurvey_OptionA;
    }

    public String getInstitutionSurvey_OptionB() {
        return institutionSurvey_OptionB;
    }

    public void setInstitutionSurvey_OptionB(String institutionSurvey_OptionB) {
        this.institutionSurvey_OptionB = institutionSurvey_OptionB;
    }

    public String getInstitutionSurvey_CreationDate() {
        return institutionSurvey_CreationDate;
    }

    public void setInstitutionSurvey_CreationDate(String institutionSurvey_CreationDate) {
        this.institutionSurvey_CreationDate = institutionSurvey_CreationDate;
    }

    public static boolean createSurvey(String institutionProfile_UniqueName,
                                       String institutionSurvey_Title,
                                       String institutionSurvey_Content,
                                       String institutionSurvey_ExpiryDate,
                                       String institutionSurvey_TargetRangeTo,
                                       String institutionSurvey_OptionA,
                                       String institutionSurvey_OptionB,
                                       String institutionSurvey_CreationDate) throws SQLException, ClassNotFoundException {
        Connection con = DatabaseConnector.getConnection();
        String query = "INSERT INTO institutionsurvey VALUES(0,?,?,?,?,?,?,?,?)";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1,institutionProfile_UniqueName);
        stmt.setString(2,institutionSurvey_Title);
        stmt.setString(3,institutionSurvey_Content);
        stmt.setString(4,institutionSurvey_ExpiryDate);
        stmt.setString(5,institutionSurvey_TargetRangeTo);
        stmt.setString(6,institutionSurvey_OptionA);
        stmt.setString(7,institutionSurvey_OptionB);
        stmt.setString(8,institutionSurvey_CreationDate);
        return stmt.executeUpdate()!=0;
    }

    public static boolean removeSurvey(int institutionSurveyId,String institutionProfileUniqueName) throws SQLException, ClassNotFoundException {
        Connection con = DatabaseConnector.getConnection();
        String query = "DELETE FROM institutionsurvey WHERE institutionProfile_UniqueName=? AND institutionSurvey_id=?";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1,institutionProfileUniqueName);
        stmt.setInt(2,institutionSurveyId);
        if(stmt.executeUpdate()!=0){
            String removeVotes = "DELETE FROM institutionsurveyvotes WHERE institutionSurvey_id=?";
            PreparedStatement prep = con.prepareStatement(removeVotes);
            prep.setInt(1,institutionSurveyId);
            prep.executeUpdate();
            return true;
        }
        return false;
    }

    public static ArrayList<InstitutionSurvey> getSurveys(String institutionProfile_UniqueName) throws SQLException, ClassNotFoundException {
        Connection con = DatabaseConnector.getConnection();
        String query = "SELECT * FROM institutionsurvey WHERE institutionProfile_UniqueName=? ORDER BY institutionSurvey_id DESC";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1,institutionProfile_UniqueName);
        ArrayList<InstitutionSurvey> surveys = new ArrayList<>();
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            surveys.add(new InstitutionSurvey(
                    rs.getInt("institutionSurvey_id"),
                    rs.getString("institutionProfile_UniqueName"),
                    rs.getString("institutionSurvey_Title"),
                    rs.getString("institutionSurvey_Content"),
                    rs.getString("institutionSurvey_ExpiryDate"),
                    rs.getString("institutionSurvey_TargetRangeTo"),
                    rs.getString("institutionSurvey_OptionA"),
                    rs.getString("institutionSurvey_OptionB"),
                    rs.getString("institutionSurvey_CreationDate")
            ));
        }
        return surveys;
    }
}
