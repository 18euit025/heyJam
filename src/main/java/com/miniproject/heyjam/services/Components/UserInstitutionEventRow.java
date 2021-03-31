package com.miniproject.heyjam.services.Components;

import com.miniproject.heyjam.services.databaseServices.InstitutionEvent;
import com.miniproject.heyjam.services.databaseServices.UserInstitutionRelation;
import com.miniproject.heyjam.services.databaseServices.UserProfile;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserInstitutionEventRow {
    private int institutionEventId;
    private String institutionProfileUniqueName;
    private String institutionEventTitle;
    private String institutionEventContent;
    private String institutionEventTargetRange;
    private String institutionEventLinkTitle;
    private String institutionEventLink;

    public UserInstitutionEventRow(int institutionEventId, String institutionProfileUniqueName, String institutionEventTitle, String institutionEventContent, String institutionEventTargetRange, String institutionEventLinkTitle, String institutionEventLink) {
        this.institutionEventId = institutionEventId;
        this.institutionProfileUniqueName = institutionProfileUniqueName;
        this.institutionEventTitle = institutionEventTitle;
        this.institutionEventContent = institutionEventContent;
        this.institutionEventTargetRange = institutionEventTargetRange;
        this.institutionEventLinkTitle = institutionEventLinkTitle;
        this.institutionEventLink = institutionEventLink;
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

    public String getInstitutionEventTargetRange() {
        return institutionEventTargetRange;
    }

    public void setInstitutionEventTargetRange(String institutionEventTargetRange) {
        this.institutionEventTargetRange = institutionEventTargetRange;
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

    public static ArrayList<UserInstitutionEventRow> getEvents(String institutionProfileUniqueName,String userProfileUsername) throws SQLException, ClassNotFoundException {
        ArrayList<InstitutionEvent> allEvents = InstitutionEvent.getEvents(institutionProfileUniqueName);
        ArrayList<UserInstitutionEventRow> myEvents = new ArrayList<>();
        UserInstitutionRelation user = UserInstitutionRelation.getUserInstitutionRelationData(userProfileUsername,institutionProfileUniqueName);
        if(user!=null) {
            for (InstitutionEvent event : allEvents) {
                if(event.getInstitutionEventTargetRangeTo().equals(user.getUserInstitutionRelation_JoiningYear()) || event.getInstitutionEventTargetRangeTo().equals("all")){
                    myEvents.add(new UserInstitutionEventRow(
                            event.getInstitutionEventId(),
                            institutionProfileUniqueName,
                            event.getInstitutionEventTitle(),
                            event.getInstitutionEventContent(),
                            event.getInstitutionEventTargetRangeTo(),
                            event.getInstitutionEventLinkTitle(),
                            event.getInstitutionEventLink()
                    ));
                }
            }
        }
        return myEvents;
    }
}

