package com.miniproject.heyjam.services.Components;

public class RequestForm {
    private String institutionProfileUniqueName;
    private String institutionProfileName;
    private String institutionProfileDescription;
    private String userProfileUsername;
    private String userProfileName;
    private String userProfileEmail;
    private String userProfilePhone;
    private String userInstitutionRelationJoiningYear;
    private String userInstitutionRelationDepartment;

    public RequestForm(String institutionProfileUniqueName, String institutionProfileName, String institutionProfileDescription, String userProfileUsername, String userProfileName, String userProfileEmail, String userProfilePhone, String userInstitutionRelationJoiningYear, String userInstitutionRelationDepartment) {
        this.institutionProfileUniqueName = institutionProfileUniqueName;
        this.institutionProfileName = institutionProfileName;
        this.institutionProfileDescription = institutionProfileDescription;
        this.userProfileUsername = userProfileUsername;
        this.userProfileName = userProfileName;
        this.userProfileEmail = userProfileEmail;
        this.userProfilePhone = userProfilePhone;
        this.userInstitutionRelationJoiningYear = userInstitutionRelationJoiningYear;
        this.userInstitutionRelationDepartment = userInstitutionRelationDepartment;
    }

    public String getInstitutionProfileUniqueName() {
        return institutionProfileUniqueName;
    }

    public void setInstitutionProfileUniqueName(String institutionProfileUniqueName) {
        this.institutionProfileUniqueName = institutionProfileUniqueName;
    }

    public String getInstitutionProfileName() {
        return institutionProfileName;
    }

    public void setInstitutionProfileName(String institutionProfileName) {
        this.institutionProfileName = institutionProfileName;
    }

    public String getInstitutionProfileDescription() {
        return institutionProfileDescription;
    }

    public void setInstitutionProfileDescription(String institutionProfileDescription) {
        this.institutionProfileDescription = institutionProfileDescription;
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

    public String getUserInstitutionRelationJoiningYear() {
        return userInstitutionRelationJoiningYear;
    }

    public void setUserInstitutionRelationJoiningYear(String userInstitutionRelationJoiningYear) {
        this.userInstitutionRelationJoiningYear = userInstitutionRelationJoiningYear;
    }

    public String getUserInstitutionRelationDepartment() {
        return userInstitutionRelationDepartment;
    }

    public void setUserInstitutionRelationDepartment(String userInstitutionRelationDepartment) {
        this.userInstitutionRelationDepartment = userInstitutionRelationDepartment;
    }
}

