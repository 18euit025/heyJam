package com.miniproject.heyjam.services.Components;

import com.miniproject.heyjam.services.databaseServices.InstitutionSurvey;
import com.miniproject.heyjam.services.databaseServices.InstitutionSurveyVotes;

import java.sql.SQLException;
import java.util.ArrayList;

public class InstitutionSurveyRow {
    private int institutionSurveyId;
    private String institutionSurveyTitle;
    private String institutionSurveyContent;
    private String institutionSurveyTargetRangeTo;
    private String institutionSurveyOptionA;
    private String institutionSurveyOptionB;
    private int institutionSurveyOptionA_VoteCount;
    private int institutionSurveyOptionB_VoteCount;

    public InstitutionSurveyRow(int institutionSurveyId,String institutionSurveyTitle, String institutionSurveyContent, String institutionSurveyTargetRangeTo, String institutionSurveyOptionA, String institutionSurveyOptionB, int institutionSurveyOptionA_VoteCount, int institutionSurveyOptionB_VoteCount) {
        this.institutionSurveyId = institutionSurveyId;
        this.institutionSurveyTitle = institutionSurveyTitle;
        this.institutionSurveyContent = institutionSurveyContent;
        this.institutionSurveyTargetRangeTo = institutionSurveyTargetRangeTo;
        this.institutionSurveyOptionA = institutionSurveyOptionA;
        this.institutionSurveyOptionB = institutionSurveyOptionB;
        this.institutionSurveyOptionA_VoteCount = institutionSurveyOptionA_VoteCount;
        this.institutionSurveyOptionB_VoteCount = institutionSurveyOptionB_VoteCount;
    }

    public int getInstitutionSurveyId() {
        return institutionSurveyId;
    }

    public void setInstitutionSurveyId(int institutionSurveyId) {
        this.institutionSurveyId = institutionSurveyId;
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

    public String getInstitutionSurveyTargetRangeTo() {
        return institutionSurveyTargetRangeTo;
    }

    public void setInstitutionSurveyTargetRangeTo(String institutionSurveyTargetRangeTo) {
        this.institutionSurveyTargetRangeTo = institutionSurveyTargetRangeTo;
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

    public int getInstitutionSurveyOptionA_VoteCount() {
        return institutionSurveyOptionA_VoteCount;
    }

    public void setInstitutionSurveyOptionA_VoteCount(int institutionSurveyOptionA_VoteCount) {
        this.institutionSurveyOptionA_VoteCount = institutionSurveyOptionA_VoteCount;
    }

    public int getInstitutionSurveyOptionB_VoteCount() {
        return institutionSurveyOptionB_VoteCount;
    }

    public void setInstitutionSurveyOptionB_VoteCount(int institutionSurveyOptionB_VoteCount) {
        this.institutionSurveyOptionB_VoteCount = institutionSurveyOptionB_VoteCount;
    }

    public static ArrayList<InstitutionSurveyRow> getSurveys(String institutionProfileUniqueName) throws SQLException, ClassNotFoundException {
        ArrayList<InstitutionSurvey> surveys = InstitutionSurvey.getSurveys(institutionProfileUniqueName);
        ArrayList<InstitutionSurveyRow> surveysDetails = new ArrayList<>();
        for (InstitutionSurvey survey : surveys) {
            int institutionSurveyId = survey.getInstitutionSurvey_id();
            String institutionSurveyTitle = survey.getInstitutionSurvey_Title();
            String institutionSurveyContent = survey.getInstitutionSurvey_Content();
            String institutionSurveyTargetRangeTo = survey.getInstitutionSurvey_TargetRangeTo();
            String institutionSurveyOptionA = survey.getInstitutionSurvey_OptionA();
            String institutionSurveyOptionB = survey.getInstitutionSurvey_OptionB();
            int optionA_Count = InstitutionSurveyVotes.voteCount(institutionSurveyId, institutionSurveyOptionA);
            int optionB_Count = InstitutionSurveyVotes.voteCount(institutionSurveyId, institutionSurveyOptionB);
            surveysDetails.add(new InstitutionSurveyRow(
                    institutionSurveyId,
                    institutionSurveyTitle,
                    institutionSurveyContent,
                    institutionSurveyTargetRangeTo,
                    institutionSurveyOptionA,
                    institutionSurveyOptionB,
                    optionA_Count,
                    optionB_Count
            ));
        }
        return surveysDetails;
    }
}

