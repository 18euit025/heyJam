package com.miniproject.heyjam.services.Components;

import com.miniproject.heyjam.services.databaseServices.InstitutionProfile;
import com.miniproject.heyjam.services.databaseServices.UserInstitutionRelation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InstitutionRow {
    private String institutionName;
    private String institutionUniqueName;
    private int institutionParticipantsCount;
    private int institutionFollowStatus;

    public InstitutionRow(String institutionName, String institutionUniqueName, int institutionParticipantsCount, int institutionFollowStatus) {
        this.institutionName = institutionName;
        this.institutionUniqueName = institutionUniqueName;
        this.institutionParticipantsCount = institutionParticipantsCount;
        this.institutionFollowStatus = institutionFollowStatus;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public String getInstitutionUniqueName() {
        return institutionUniqueName;
    }

    public void setInstitutionUniqueName(String institutionUniqueName) {
        this.institutionUniqueName = institutionUniqueName;
    }

    public int getInstitutionParticipantsCount() {
        return institutionParticipantsCount;
    }

    public void setInstitutionParticipantsCount(int institutionParticipantsCount) {
        this.institutionParticipantsCount = institutionParticipantsCount;
    }

    public int getInstitutionFollowStatus() {
        return institutionFollowStatus;
    }

    public void setInstitutionFollowStatus(int institutionFollowStatus) {
        this.institutionFollowStatus = institutionFollowStatus;
    }

    public static InstitutionRow findInstitution(String institutionName,String username) throws SQLException, ClassNotFoundException {
        InstitutionProfile profile = InstitutionProfile.getInstitutionProfile(institutionName);
        if(profile!=null) {
            int participantCount = UserInstitutionRelation.getParticipantCount(institutionName);
            int userStatus = UserInstitutionRelation.userFollowStatus(username,institutionName);
            return new InstitutionRow(
                    profile.getInstitutionProfile_Name(),
                    profile.getInstitutionProfile_UniqueName(),
                    participantCount,
                    userStatus
            );
        }
        return null;
    }

    public static ArrayList<InstitutionRow> getAllMyInstitutions(String username) throws SQLException, ClassNotFoundException {
        ArrayList<InstitutionRow> myInstitutions = new ArrayList<>();
        ResultSet rs = UserInstitutionRelation.userInstitutions(username);
        while(rs.next()){
            InstitutionProfile profile = InstitutionProfile.getInstitutionProfile(rs.getString("institutionProfileUniqueName"));
            if(profile!=null){
                int participantCount = UserInstitutionRelation.getParticipantCount(profile.getInstitutionProfile_UniqueName());
                int userStatus = UserInstitutionRelation.userFollowStatus(username,profile.getInstitutionProfile_UniqueName());
                myInstitutions.add(new InstitutionRow(profile.getInstitutionProfile_Name(),profile.getInstitutionProfile_UniqueName(),participantCount,userStatus));
            }
        }
        return myInstitutions;
    }

}
