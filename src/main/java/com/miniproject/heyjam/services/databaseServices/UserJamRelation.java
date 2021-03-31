package com.miniproject.heyjam.services.databaseServices;

public class UserJamRelation {
    private int userJamRelation_id;
    private String jamProfile_UniqueName;
    private String jamProfile_Username;
    private int getUserJamRelation_Status;

    public UserJamRelation(int userJamRelation_id, String jamProfile_UniqueName, String jamProfile_Username, int getUserJamRelation_Status) {
        this.userJamRelation_id = userJamRelation_id;
        this.jamProfile_UniqueName = jamProfile_UniqueName;
        this.jamProfile_Username = jamProfile_Username;
        this.getUserJamRelation_Status = getUserJamRelation_Status;
    }

    public int getUserJamRelation_id() {
        return userJamRelation_id;
    }

    public void setUserJamRelation_id(int userJamRelation_id) {
        this.userJamRelation_id = userJamRelation_id;
    }

    public String getJamProfile_UniqueName() {
        return jamProfile_UniqueName;
    }

    public void setJamProfile_UniqueName(String jamProfile_UniqueName) {
        this.jamProfile_UniqueName = jamProfile_UniqueName;
    }

    public String getJamProfile_Username() {
        return jamProfile_Username;
    }

    public void setJamProfile_Username(String jamProfile_Username) {
        this.jamProfile_Username = jamProfile_Username;
    }

    public int getGetUserJamRelation_Status() {
        return getUserJamRelation_Status;
    }

    public void setGetUserJamRelation_Status(int getUserJamRelation_Status) {
        this.getUserJamRelation_Status = getUserJamRelation_Status;
    }
}

