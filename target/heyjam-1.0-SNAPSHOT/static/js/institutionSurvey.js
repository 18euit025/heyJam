

const getSurveyElements = ()=>{
    $.get(
        "http://localhost:8080/heyjam_war_exploded/InstitutionSurvey",
        (responseText)=>{
            let surveys = JSON.parse(responseText);
            let body = "";
            surveys.forEach((survey)=>{
                let replace = "" ;
                if(survey.institutionSurveyTargetRangeTo==="all"){
                    replace = "all";
                }else{
                    replace = survey.institutionSurveyTargetRangeTo;
                }
                let consturct = `
                        <div class="container" style=" margin-left: 345px;margin-bottom: 0px;">
                            <div class="card" style="width: 990px; ">
                                <div class="card-body">
                                    <div class="row">
                                        <div class="col-7" >
                                            <h3 style="color: #184770;">${survey.institutionSurveyTitle}  <span class="badge bg-success"> ${replace}</span></h3>
                                        </div>
                                        <div class="col-3">
                                            
                                        </div>
                                        <div style="margin-left:0px;" class="col-2">
                                            <button style="width: 85px" value="${survey.institutionSurveyId}" onclick="removeSurvey(this.value)" class="badge bg-danger">X</button>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-9">
                                            <p>${survey.institutionSurveyContent}</p><br>
                                            <p><span class="badge bg-success"> ${survey.institutionSurveyOptionA}  ${survey.institutionSurveyOptionA_VoteCount}</span>   <span class="badge bg-danger"> ${survey.institutionSurveyOptionB}  ${survey.institutionSurveyOptionB_VoteCount}</span></p> 
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <hr style="background-color:black; margin-left: 360px; width: 990px;">
                    `;
                body = body + consturct;
            });
            document.getElementById("allSurveys").innerHTML = body;
        }
        );
}

window.onload = getSurveyElements;

let removeSurvey = (value)=>{
    $.get(
        "http://localhost:8080/heyjam_war_exploded/DeleteInstitutionSurvey",
        {surveyId:value},
        ()=> getSurveyElements()
    )
}

document.getElementById("createSurvey").addEventListener('click',()=>{
    window.location.href = "institutionCreateSurvey.jsp";
})