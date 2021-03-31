package com.miniproject.heyjam.services.databaseServices;

import com.miniproject.heyjam.services.otherServices.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InstitutionEvent {
    private int institutionEventId;
    private String institutionProfileUniqueName;
    private String institutionEventTitle;
    private String institutionEventContent;
    private String institutionEventExpiryDate;
    private String institutionEventTargetRangeTo;
    private String institutionEventLinkTitle;
    private String institutionEventLink;
    private String institutionEventCreationDate;

    public InstitutionEvent(int institutionEventId, String institutionProfileUniqueName, String institutionEventTitle, String institutionEventContent, String institutionEventExpiryDate, String institutionEventTargetRangeTo, String institutionEventLinkTitle, String institutionEventLink, String institutionEventCreationDate) {
        this.institutionEventId = institutionEventId;
        this.institutionProfileUniqueName = institutionProfileUniqueName;
        this.institutionEventTitle = institutionEventTitle;
        this.institutionEventContent = institutionEventContent;
        this.institutionEventExpiryDate = institutionEventExpiryDate;
        this.institutionEventTargetRangeTo = institutionEventTargetRangeTo;
        this.institutionEventLinkTitle = institutionEventLinkTitle;
        this.institutionEventLink = institutionEventLink;
        this.institutionEventCreationDate = institutionEventCreationDate;
    }

    public int getInstitutionEventId() {
        return institutionEventId;
    }

    public void setInstitutionEventId(int institutionEventId) {
        this.institutionEventId = institutionEventId;
    }

    public String getInstitutionProfileUniqueName() {
        return institutionProfileUniqueName;
    }

    public void setInstitutionProfileUniqueName(String institutionProfileUniqueName) {
        this.institutionProfileUniqueName = institutionProfileUniqueName;
    }

    public String getInstitutionEventTitle() {
        return institutionEventTitle;
    }

    public void setInstitutionEventTitle(String institutionEventTitle) {
        this.institutionEventTitle = institutionEventTitle;
    }

    public String getInstitutionEventContent() {
        return institutionEventContent;
    }

    public void setInstitutionEventContent(String institutionEventContent) {
        this.institutionEventContent = institutionEventContent;
    }

    public String getInstitutionEventExpiryDate() {
        return institutionEventExpiryDate;
    }

    public void setInstitutionEventExpiryDate(String institutionEventExpiryDate) {
        this.institutionEventExpiryDate = institutionEventExpiryDate;
    }

    public String getInstitutionEventTargetRangeTo() {
        return institutionEventTargetRangeTo;
    }

    public void setInstitutionEventTargetRangeTo(String institutionEventTargetRangeTo) {
        this.institutionEventTargetRangeTo = institutionEventTargetRangeTo;
    }

    public String getInstitutionEventLinkTitle() {
        return institutionEventLinkTitle;
    }

    public void setInstitutionEventLinkTitle(String institutionEventLinkTitle) {
        this.institutionEventLinkTitle = institutionEventLinkTitle;
    }

    public String getInstitutionEventLink() {
        return institutionEventLink;
    }

    public void setInstitutionEventLink(String institutionEventLink) {
        this.institutionEventLink = institutionEventLink;
    }

    public String getInstitutionEventCreationDate() {
        return institutionEventCreationDate;
    }

    public void setInstitutionEventCreationDate(String institutionEventCreationDate) {
        this.institutionEventCreationDate = institutionEventCreationDate;
    }

    public static boolean createEvent(
            String institutionProfileUniqueName,
            String institutionEventTitle,
            String institutionEventContent,
            String institutionEventExpiryDate,
            String institutionEventTargetRangeTo,
            String institutionEventLinkTitle,
            String institutionEventLink,
            String institutionEventCreationDate) throws SQLException, ClassNotFoundException {
        Connection con = DatabaseConnector.getConnection();
        String query = "INSERT INTO institutionevent VALUES (0,?,?,?,?,?,?,?,?)";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1,institutionProfileUniqueName);
        stmt.setString(2,institutionEventTitle);
        stmt.setString(3,institutionEventContent);
        stmt.setString(4,institutionEventExpiryDate);
        stmt.setString(5,institutionEventTargetRangeTo);
        stmt.setString(6,institutionEventLinkTitle);
        stmt.setString(7,institutionEventLink);
        stmt.setString(8,institutionEventCreationDate);
        return stmt.executeUpdate()!=0;
    }

    public static ArrayList<InstitutionEvent> getEvents(String institutionProfileUniqueName) throws SQLException, ClassNotFoundException, SQLException {
        Connection con= DatabaseConnector.getConnection();
        String query="SELECT * FROM institutionevent WHERE InstitutionProfile_UniqueName=? ORDER BY institutionEvent_id DESC";
        PreparedStatement prep =con.prepareStatement(query);
        prep.setString(1, institutionProfileUniqueName);    //Current Institution UniqueName
        ResultSet rs = prep.executeQuery();
        ArrayList<InstitutionEvent> allEvents = new ArrayList<>();
        while(rs.next()){
            allEvents.add(new InstitutionEvent(
                    rs.getInt("institutionEvent_id"),
                    rs.getString("institutionProfile_UniqueName"),
                    rs.getString("institutionEvent_Title"),
                    rs.getString("institutionEvent_Content"),
                    rs.getString("institutionEvent_ExpiryDate"),
                    rs.getString("institutionEvent_TargetRangeTo"),
                    rs.getString("institutionEvent_LinkTitle"),
                    rs.getString("institutionEvent_Link"),
                    rs.getString("institutionEvent_CreationDate")
            ));
        }
        return allEvents;
    }

    public static boolean removeEvent(int institutionEventId,String institutionProfileUniqueName) throws SQLException, ClassNotFoundException {
        Connection con = DatabaseConnector.getConnection();
        String query = "DELETE FROM institutionevent WHERE institutionEvent_id=? AND institutionProfile_UniqueName=?";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setInt(1,institutionEventId);
        stmt.setString(2,institutionProfileUniqueName);
        return stmt.executeUpdate()!=0;
    }

}
