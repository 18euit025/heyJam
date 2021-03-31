package com.miniproject.heyjam.services.Components;

import com.miniproject.heyjam.services.databaseServices.UserInstitutionRelation;
import com.miniproject.heyjam.services.databaseServices.UserProfile;
import com.miniproject.heyjam.services.databaseServices.UserUserRelation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserInstitutionParticipantsRow {
    private String institutionProfileUniqueName;
    private String userProfileUsernameParticipant;
    private String userProfileNameParticipant;
    private String userInstitutionRelationYearJoinedParticipant;
    private String userInstitutionRelationDepartmentParticipant;
    private String userProfileEmailParticipant;
    private int userUserFollowStatus;

    public UserInstitutionParticipantsRow(String institutionProfileUniqueName, String userProfileUsernameParticipant, String userProfileNameParticipant, String userInstitutionRelationYearJoinedParticipant, String userInstitutionRelationDepartmentParticipant, String userProfileEmailParticipant, int userUserFollowStatus) {
        this.institutionProfileUniqueName = institutionProfileUniqueName;
        this.userProfileUsernameParticipant = userProfileUsernameParticipant;
        this.userProfileNameParticipant = userProfileNameParticipant;
        this.userInstitutionRelationYearJoinedParticipant = userInstitutionRelationYearJoinedParticipant;
        this.userInstitutionRelationDepartmentParticipant = userInstitutionRelationDepartmentParticipant;
        this.userProfileEmailParticipant = userProfileEmailParticipant;
        this.userUserFollowStatus = userUserFollowStatus;
    }

    public String getInstitutionProfileUniqueName() {
        return institutionProfileUniqueName;
    }

    public void setInstitutionProfileUniqueName(String institutionProfileUniqueName) {
        this.institutionProfileUniqueName = institutionProfileUniqueName;
    }

    public String getUserProfileUsernameParticipant() {
        return userProfileUsernameParticipant;
    }

    public void setUserProfileUsernameParticipant(String userProfileUsernameParticipant) {
        this.userProfileUsernameParticipant = userProfileUsernameParticipant;
    }

    public String getUserProfileNameParticipant() {
        return userProfileNameParticipant;
    }

    public void setUserProfileNameParticipant(String userProfileNameParticipant) {
        this.userProfileNameParticipant = userProfileNameParticipant;
    }

    public String getUserInstitutionRelationYearJoinedParticipant() {
        return userInstitutionRelationYearJoinedParticipant;
    }

    public void setUserInstitutionRelationYearJoinedParticipant(String userInstitutionRelationYearJoinedParticipant) {
        this.userInstitutionRelationYearJoinedParticipant = userInstitutionRelationYearJoinedParticipant;
    }

    public String getUserInstitutionRelationDepartmentParticipant() {
        return userInstitutionRelationDepartmentParticipant;
    }

    public void setUserInstitutionRelationDepartmentParticipant(String userInstitutionRelationDepartmentParticipant) {
        this.userInstitutionRelationDepartmentParticipant = userInstitutionRelationDepartmentParticipant;
    }

    public String getUserProfileEmailParticipant() {
        return userProfileEmailParticipant;
    }

    public void setUserProfileEmailParticipant(String userProfileEmailParticipant) {
        this.userProfileEmailParticipant = userProfileEmailParticipant;
    }

    public int getUserUserFollowStatus() {
        return userUserFollowStatus;
    }

    public void setUserUserFollowStatus(int userUserFollowStatus) {
        this.userUserFollowStatus = userUserFollowStatus;
    }

    public static ArrayList<UserInstitutionParticipantsRow> userInstitutionParticipants(String userProfileUsername,String institutionProfileUniqueName) throws SQLException, ClassNotFoundException {
        ArrayList<UserInstitutionRelation> allParticipants = UserInstitutionRelation.getInstitutionParticipants(institutionProfileUniqueName);
        ArrayList<UserInstitutionParticipantsRow> participants = new ArrayList<>();
        for(UserInstitutionRelation individual: allParticipants){
            if(!individual.getUserProfileUsername().equals(userProfileUsername)){
                UserProfile profile = UserProfile.getUserProfile(individual.getUserProfileUsername());
                if(profile != null) {
                    String userProfileUsernameParticipant = individual.getUserProfileUsername();
                    String userProfileNameParticipant = profile.getUserProfile_Name();
                    String userInstitutionJoiningYearParticipants = individual.getUserInstitutionRelation_JoiningYear();
                    String userInstitutionEmailParticipant = profile.getUserProfile_Email();
                    String userInstitutionDepartment = individual.getUserInstitutionRelation_Department();
                    int userUserFollowStatus = UserUserRelation.userUserRelationFollowStatus(userProfileUsername,individual.getUserProfileUsername());
                    participants.add(new UserInstitutionParticipantsRow(
                            institutionProfileUniqueName,
                            userProfileUsernameParticipant,
                            userProfileNameParticipant,
                            userInstitutionJoiningYearParticipants,
                            userInstitutionEmailParticipant,
                            userInstitutionDepartment,
                            userUserFollowStatus)
                    );
                }

            }
        }
        return participants;
    }

    public static ArrayList<UserInstitutionParticipantsRow> userUserNotifications(String userProfileUsername) throws SQLException, ClassNotFoundException {
        ArrayList<UserUserRelation> followRequests = UserUserRelation.userUserRelationUserRequests(userProfileUsername);
        ArrayList<UserInstitutionParticipantsRow> followRequestSet = new ArrayList<>();
        for(UserUserRelation user: followRequests){
            UserProfile profile = UserProfile.getUserProfile(user.getUserProfile_Username_follower());
            String institutions = "";
            ResultSet relationProfile = UserInstitutionRelation.userInstitutions(user.getUserProfile_Username_follower());
            while(relationProfile.next()){
                institutions = institutions + relationProfile.getString("institutionProfileUniqueName")+" ";
            }
            if(profile != null) {
                String userProfileUsernameParticipant = profile.getUserProfile_Username();
                String userProfileNameParticipant = profile.getUserProfile_Name();
                String userInstitutionJoiningYearParticipants = "";
                String userInstitutionEmailParticipant = profile.getUserProfile_Email();
                String userInstitutionDepartment = "";
                int userUserFollowStatus = 4;
                followRequestSet.add(new UserInstitutionParticipantsRow(
                        institutions,
                        userProfileUsernameParticipant,
                        userProfileNameParticipant,
                        userInstitutionJoiningYearParticipants,
                        userInstitutionDepartment,
                        userInstitutionEmailParticipant,
                        userUserFollowStatus)
                );
            }
        }
        return followRequestSet;
    }

    public static ArrayList<UserInstitutionParticipantsRow> userUserFollowings(String userProfileUsername) throws SQLException, ClassNotFoundException {
        ArrayList<UserUserRelation> followRequests = UserUserRelation.userFollowing(userProfileUsername);
        ArrayList<UserInstitutionParticipantsRow> followRequestSet = new ArrayList<>();
        for(UserUserRelation user: followRequests){
            UserProfile profile = UserProfile.getUserProfile(user.getUserProfile_Username_followee());
            String institutions = "";
            ResultSet relationProfile = UserInstitutionRelation.userInstitutions(user.getUserProfile_Username_followee());
            while(relationProfile.next()){
                institutions = institutions + relationProfile.getString("institutionProfileUniqueName")+" ";
            }
            if(profile != null) {
                String userProfileUsernameParticipant = profile.getUserProfile_Username();
                String userProfileNameParticipant = profile.getUserProfile_Name();
                String userInstitutionJoiningYearParticipants = "";
                String userInstitutionEmailParticipant = profile.getUserProfile_Email();
                String userInstitutionDepartment = "";
                int userUserFollowStatus = UserUserRelation.userUserRelationFollowStatus(userProfileUsername,user.getUserProfile_Username_followee());
                followRequestSet.add(new UserInstitutionParticipantsRow(
                        institutions,
                        userProfileUsernameParticipant,
                        userProfileNameParticipant,
                        userInstitutionJoiningYearParticipants,
                        userInstitutionDepartment,
                        userInstitutionEmailParticipant,
                        userUserFollowStatus)
                );
            }
        }
        return followRequestSet;
    }


    public static ArrayList<UserInstitutionParticipantsRow> userUserFollower(String userProfileUsername) throws SQLException, ClassNotFoundException {
        ArrayList<UserUserRelation> followRequests = UserUserRelation.userFollower(userProfileUsername);
        ArrayList<UserInstitutionParticipantsRow> followRequestSet = new ArrayList<>();
        for(UserUserRelation user: followRequests){
            UserProfile profile = UserProfile.getUserProfile(user.getUserProfile_Username_follower());
            String institutions = "";
            ResultSet relationProfile = UserInstitutionRelation.userInstitutions(user.getUserProfile_Username_follower());
            while(relationProfile.next()){
                institutions = institutions + relationProfile.getString("institutionProfileUniqueName")+" ";
            }
            if(profile != null) {
                String userProfileUsernameParticipant = profile.getUserProfile_Username();
                String userProfileNameParticipant = profile.getUserProfile_Name();
                String userInstitutionJoiningYearParticipants = "";
                String userInstitutionEmailParticipant = profile.getUserProfile_Email();
                String userInstitutionDepartment = "";
                int userUserFollowStatus = UserUserRelation.userUserRelationFollowStatus(user.getUserProfile_Username_followee(),user.getUserProfile_Username_follower());
                followRequestSet.add(new UserInstitutionParticipantsRow(
                        institutions,
                        userProfileUsernameParticipant,
                        userProfileNameParticipant,
                        userInstitutionJoiningYearParticipants,
                        userInstitutionDepartment,
                        userInstitutionEmailParticipant,
                        userUserFollowStatus)
                );
            }
        }
        return followRequestSet;
    }

}

