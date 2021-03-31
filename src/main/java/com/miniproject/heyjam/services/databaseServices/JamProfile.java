package com.miniproject.heyjam.services.databaseServices;

public class JamProfile {
    private int jamProfile_id;
    private String jamProfile_UniqueName;
    private String jamProfile_Name;
    private String jamProfile_Description;
    private String jamProfile_UniqueNameParent;
    private String jamProfile_UsernameAdmin;

    public JamProfile(int jamProfile_id, String jamProfile_UniqueName, String jamProfile_Name, String jamProfile_Description, String jamProfile_UniqueNameParent, String jamProfile_UsernameAdmin) {
        this.jamProfile_id = jamProfile_id;
        this.jamProfile_UniqueName = jamProfile_UniqueName;
        this.jamProfile_Name = jamProfile_Name;
        this.jamProfile_Description = jamProfile_Description;
        this.jamProfile_UniqueNameParent = jamProfile_UniqueNameParent;
        this.jamProfile_UsernameAdmin = jamProfile_UsernameAdmin;
    }

    public int getJamProfile_id() {
        return jamProfile_id;
    }

    public void setJamProfile_id(int jamProfile_id) {
        this.jamProfile_id = jamProfile_id;
    }

    public String getJamProfile_UniqueName() {
        return jamProfile_UniqueName;
    }

    public void setJamProfile_UniqueName(String jamProfile_UniqueName) {
        this.jamProfile_UniqueName = jamProfile_UniqueName;
    }

    public String getJamProfile_Name() {
        return jamProfile_Name;
    }

    public void setJamProfile_Name(String jamProfile_Name) {
        this.jamProfile_Name = jamProfile_Name;
    }

    public String getJamProfile_Description() {
        return jamProfile_Description;
    }

    public void setJamProfile_Description(String jamProfile_Description) {
        this.jamProfile_Description = jamProfile_Description;
    }

    public String getJamProfile_UniqueNameParent() {
        return jamProfile_UniqueNameParent;
    }

    public void setJamProfile_UniqueNameParent(String jamProfile_UniqueNameParent) {
        this.jamProfile_UniqueNameParent = jamProfile_UniqueNameParent;
    }

    public String getJamProfile_UsernameAdmin() {
        return jamProfile_UsernameAdmin;
    }

    public void setJamProfile_UsernameAdmin(String jamProfile_UsernameAdmin) {
        this.jamProfile_UsernameAdmin = jamProfile_UsernameAdmin;
    }
}
