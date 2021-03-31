package com.miniproject.heyjam.services.Components;

import com.miniproject.heyjam.services.databaseServices.UserInstitutionRelation;
import com.miniproject.heyjam.services.databaseServices.UserProfile;
import com.miniproject.heyjam.services.otherServices.DatabaseConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserInstitutionRow {
    private String institutionProfileUniqueName;
    private String userProfileUsername;
    private String userProfileName;
    private String userInstitutionRelationYearJoined;
    private String getUserInstitutionRelationDepartment;
    private String userProfileEmail;
    private String userProfilePhone;

    public UserInstitutionRow(String institutionProfileUniqueName, String userProfileUsername, String userProfileName, String userInstitutionRelationYearJoined, String getUserInstitutionRelationDepartment, String userProfileEmail, String userProfilePhone) {
        this.institutionProfileUniqueName = institutionProfileUniqueName;
        this.userProfileUsername = userProfileUsername;
        this.userProfileName = userProfileName;
        this.userInstitutionRelationYearJoined = userInstitutionRelationYearJoined;
        this.getUserInstitutionRelationDepartment = getUserInstitutionRelationDepartment;
        this.userProfileEmail = userProfileEmail;
        this.userProfilePhone = userProfilePhone;
    }

    public String getInstitutionProfileUniqueName() {
        return institutionProfileUniqueName;
    }

    public void setInstitutionProfileUniqueName(String institutionProfileUniqueName) {
        this.institutionProfileUniqueName = institutionProfileUniqueName;
    }

    public String getUserProfileUsername() {
        return userProfileUsername;
    }

    public void setUserProfileUsername(String userProfileUsername) {
        this.userProfileUsername = userProfileUsername;
    }

    public String getUserProfileName() {
        return userProfileName;
    }

    public void setUserProfileName(String userProfileName) {
        this.userProfileName = userProfileName;
    }

    public String getUserInstitutionRelationYearJoined() {
        return userInstitutionRelationYearJoined;
    }

    public void setUserInstitutionRelationYearJoined(String userInstitutionRelationYearJoined) {
        this.userInstitutionRelationYearJoined = userInstitutionRelationYearJoined;
    }

    public String getGetUserInstitutionRelationDepartment() {
        return getUserInstitutionRelationDepartment;
    }

    public void setGetUserInstitutionRelationDepartment(String getUserInstitutionRelationDepartment) {
        this.getUserInstitutionRelationDepartment = getUserInstitutionRelationDepartment;
    }

    public String getUserProfileEmail() {
        return userProfileEmail;
    }

    public void setUserProfileEmail(String userProfileEmail) {
        this.userProfileEmail = userProfileEmail;
    }

    public String getUserProfilePhone() {
        return userProfilePhone;
    }

    public void setUserProfilePhone(String userProfilePhone) {
        this.userProfilePhone = userProfilePhone;
    }

    public static ArrayList<UserInstitutionRow> getInstitutionNotifications(String institutionProfileUniqueName) throws SQLException, ClassNotFoundException {
        ResultSet rs = UserInstitutionRelation.getAllNotifications(institutionProfileUniqueName);
        ArrayList<UserInstitutionRow> all = new ArrayList<>();
        while(rs.next()){
            String userProfileUsername = rs.getString("userProfileUsername");
            UserProfile profile = UserProfile.getUserProfile(userProfileUsername);
            if(profile != null){
                String name = profile.getUserProfile_Name();
                String yearJoined = rs.getString("userInstitutionRelation_JoiningYear");
                String department = rs.getString("userInstitutionRelation_Department");
                String email = profile.getUserProfile_Email();
                String phone = profile.getUserProfile_Phone();
                all.add(new UserInstitutionRow(
                        institutionProfileUniqueName,
                        userProfileUsername,
                        name,
                        yearJoined,
                        department,
                        email,
                        phone)
                );
            }
        }
        return all;
    }
}
