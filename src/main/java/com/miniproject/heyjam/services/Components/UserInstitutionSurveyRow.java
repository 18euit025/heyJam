package com.miniproject.heyjam.services.Components;

import com.miniproject.heyjam.services.databaseServices.InstitutionSurvey;
import com.miniproject.heyjam.services.databaseServices.InstitutionSurveyVotes;
import com.miniproject.heyjam.services.databaseServices.UserInstitutionRelation;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserInstitutionSurveyRow {
    private int institutionSurveyId;
    private String institutionProfileUniqueName;
    private String institutionSurveyTitle;
    private String institutionSurveyContent;
    private String institutionSurveyTargetRange;
    private String institutionSurveyOptionA;
    private String institutionSurveyOptionB;
    private String institutionSurveySelectedOption;

    public UserInstitutionSurveyRow(int institutionSurveyId, String institutionProfileUniqueName, String institutionSurveyTitle, String institutionSurveyContent, String institutionSurveyTargetRange, String institutionSurveyOptionA, String institutionSurveyOptionB, String institutionSurveySelectedOption) {
        this.institutionSurveyId = institutionSurveyId;
        this.institutionProfileUniqueName = institutionProfileUniqueName;
        this.institutionSurveyTitle = institutionSurveyTitle;
        this.institutionSurveyContent = institutionSurveyContent;
        this.institutionSurveyTargetRange = institutionSurveyTargetRange;
        this.institutionSurveyOptionA = institutionSurveyOptionA;
        this.institutionSurveyOptionB = institutionSurveyOptionB;
        this.institutionSurveySelectedOption = institutionSurveySelectedOption;
    }

    public int getInstitutionSurveyId() {
        return institutionSurveyId;
    }

    public void setInstitutionSurveyId(int institutionSurveyId) {
        this.institutionSurveyId = institutionSurveyId;
    }

    public String getInstitutionProfileUniqueName() {
        return institutionProfileUniqueName;
    }

    public void setInstitutionProfileUniqueName(String institutionProfileUniqueName) {
        this.institutionProfileUniqueName = institutionProfileUniqueName;
    }

    public String getInstitutionSurveyTitle() {
        return institutionSurveyTitle;
    }

    public void setInstitutionSurveyTitle(String institutionSurveyTitle) {
        this.institutionSurveyTitle = institutionSurveyTitle;
    }

    public String getInstitutionSurveyContent() {
        return institutionSurveyContent;
    }

    public void setInstitutionSurveyContent(String institutionSurveyContent) {
        this.institutionSurveyContent = institutionSurveyContent;
    }

    public String getInstitutionSurveyTargetRange() {
        return institutionSurveyTargetRange;
    }

    public void setInstitutionSurveyTargetRange(String institutionSurveyTargetRange) {
        this.institutionSurveyTargetRange = institutionSurveyTargetRange;
    }

    public String getInstitutionSurveyOptionA() {
        return institutionSurveyOptionA;
    }

    public void setInstitutionSurveyOptionA(String institutionSurveyOptionA) {
        this.institutionSurveyOptionA = institutionSurveyOptionA;
    }

    public String getInstitutionSurveyOptionB() {
        return institutionSurveyOptionB;
    }

    public void setInstitutionSurveyOptionB(String institutionSurveyOptionB) {
        this.institutionSurveyOptionB = institutionSurveyOptionB;
    }

    public String getInstitutionSurveySelectedOption() {
        return institutionSurveySelectedOption;
    }

    public void setInstitutionSurveySelectedOption(String institutionSurveySelectedOption) {
        this.institutionSurveySelectedOption = institutionSurveySelectedOption;
    }

    public static ArrayList<UserInstitutionSurveyRow> getSurveys(String institutionProfileUniqueName,String userProfileUsername) throws SQLException, ClassNotFoundException {
        ArrayList<InstitutionSurvey> allSurvey = InstitutionSurvey.getSurveys(institutionProfileUniqueName);
        ArrayList<UserInstitutionSurveyRow> mySurveys = new ArrayList<>();
        UserInstitutionRelation user = UserInstitutionRelation.getUserInstitutionRelationData(userProfileUsername,institutionProfileUniqueName);
        if(user!=null){
            for(InstitutionSurvey survey: allSurvey){
                if(survey.getInstitutionSurvey_TargetRangeTo().equals(user.getUserInstitutionRelation_JoiningYear()) || survey.getInstitutionSurvey_TargetRangeTo().equals("all")){
                    InstitutionSurveyVotes voted = InstitutionSurveyVotes.isVoted(survey.getInstitutionSurvey_id(),userProfileUsername);
                    if(voted==null){
                        mySurveys.add(new UserInstitutionSurveyRow(
                                survey.getInstitutionSurvey_id(),
                                institutionProfileUniqueName,
                                survey.getInstitutionSurvey_Title(),
                                survey.getInstitutionSurvey_Content(),
                                survey.getInstitutionSurvey_TargetRangeTo(),
                                survey.getInstitutionSurvey_OptionA(),
                                survey.getInstitutionSurvey_OptionB(),
                                null));
                    }else{
                        mySurveys.add(new UserInstitutionSurveyRow(
                                survey.getInstitutionSurvey_id(),
                                institutionProfileUniqueName,
                                survey.getInstitutionSurvey_Title(),
                                survey.getInstitutionSurvey_Content(),
                                survey.getInstitutionSurvey_TargetRangeTo(),
                                survey.getInstitutionSurvey_OptionA(),
                                survey.getInstitutionSurvey_OptionB(),
                                voted.getInstitutionSurveyVoteOption()));
                    }
                }
            }
        }
        return mySurveys;
    }
}

