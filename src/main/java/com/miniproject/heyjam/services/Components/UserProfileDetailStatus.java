package com.miniproject.heyjam.services.Components;

import com.miniproject.heyjam.services.databaseServices.InstitutionProfile;
import com.miniproject.heyjam.services.databaseServices.UserInstitutionRelation;
import com.miniproject.heyjam.services.databaseServices.UserProfile;
import com.miniproject.heyjam.services.databaseServices.UserUserRelation;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserProfileDetailStatus {
    private String accessSpecifier;
    private String userProfileUsername;
    private String userProfileName;
    private String userProfileDOB;
    private String userProfileMobile;
    private String userProfileEmail;
    private String userProfileInstitutions;

    public UserProfileDetailStatus(String accessSpecifier, String userProfileUsername, String userProfileName, String userProfileDOB, String userProfileMobile, String userProfileEmail, String userProfileInstitutions) {
        this.accessSpecifier = accessSpecifier;
        this.userProfileUsername = userProfileUsername;
        this.userProfileName = userProfileName;
        this.userProfileDOB = userProfileDOB;
        this.userProfileMobile = userProfileMobile;
        this.userProfileEmail = userProfileEmail;
        this.userProfileInstitutions = userProfileInstitutions;
    }

    public String getAccessSpecifier() {
        return accessSpecifier;
    }

    public String getUserProfileUsername() {
        return userProfileUsername;
    }

    public String getUserProfileName() {
        return userProfileName;
    }

    public String getUserProfileDOB() {
        return userProfileDOB;
    }

    public String getUserProfileMobile() {
        return userProfileMobile;
    }

    public String getUserProfileEmail() {
        return userProfileEmail;
    }

    public String getUserProfileInstitutions() {
        return userProfileInstitutions;
    }

    public static UserProfileDetailStatus getUserDetails(String participantUsername,String username) throws SQLException, ClassNotFoundException {
        int followStatus = UserUserRelation.userUserRelationFollowStatus(username,participantUsername);
        UserProfile participant = UserProfile.getUserProfile(participantUsername);
        assert participant != null;
        if(followStatus==1){
            String institutions = "";
            ResultSet relationProfile = UserInstitutionRelation.userInstitutions(participantUsername);
            while(relationProfile.next()){
                institutions = institutions + relationProfile.getString("institutionProfileUniqueName")+" ";
            }
            return new UserProfileDetailStatus(
                    "true",
                    participant.getUserProfile_Username(),
                    participant.getUserProfile_Name(),
                    participant.getUserProfile_Dob(),
                    participant.getUserProfile_Phone(),
                    participant.getUserProfile_Email(),
                    institutions
            );
        }else{
            String institutions = "";
            ResultSet relationProfile = UserInstitutionRelation.userInstitutions(participantUsername);
            while(relationProfile.next()){
                institutions = institutions + relationProfile.getString("institutionProfileUniqueName")+" ";
            }
            return new UserProfileDetailStatus(
                    "false",
                    participant.getUserProfile_Username(),
                    participant.getUserProfile_Name(),
                    "",
                    "",
                    participant.getUserProfile_Email(),
                    institutions
            );
        }
    }
}
