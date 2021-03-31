

const getUserInstitutionSurveys = ()=>{
    $.get(
        "http://localhost:8080/heyjam_war_exploded/UserProfileInstitutionSurvey",
        {institution: localStorage.getItem('institution')},
        (responseText)=>{
            let surveys = JSON.parse(responseText);
            document.getElementById("institutionName").innerText = localStorage.getItem('institution')+" Survey";
            console.log(surveys);
            let body = "";
            surveys.forEach((survey)=>{
                let options = "";
                if(survey.institutionSurveySelectedOption===null){
                    options = `<button value="${survey.institutionSurveyOptionA}-${survey.institutionSurveyId}" onclick="addVote(this.value)" class="btn btn-sm btn-success"> ${survey.institutionSurveyOptionA}</button>   <button onclick="addVote(this.value)" value="${survey.institutionSurveyOptionB}-${survey.institutionSurveyId}" class="btn btn-sm btn-danger"> ${survey.institutionSurveyOptionB}</button>`;
                }else{
                    options = `<span class="badge bg-dark">you have voted for: ${survey.institutionSurveySelectedOption}</span>`;
                }
                let consturct = `
                        <div class="container" style=" margin-left: 345px;margin-bottom: 0px;">
                            <div class="card" style="width: 990px; ">
                                <div class="card-body">
                                    <div class="row">
                                        <div class="col-7" >
                                            <h3 style="color: #184770;">${survey.institutionSurveyTitle}</h3>
                                        </div>
                                        <div class="col-3">

                                        </div>
                                        <div style="margin-left:0px;" class="col-2">
                                            
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-9">
                                            <p>${survey.institutionSurveyContent}</p><br>
                                            <p> ${options}  </p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <hr style="background-color:black; margin-left: 360px; width: 990px;">
                    `;
                body = body + consturct;
            });
            document.getElementById("institutionSurvey").innerHTML = body;
        }
    );
}

window.onload = getUserInstitutionSurveys;

const addVote = (value)=>{
    let detail = value.split("-");
    $.get(
        "http://localhost:8080/heyjam_war_exploded/AddSurveyVote",
        {option: detail[0],id: detail[1]},
        ()=>{
            getUserInstitutionSurveys();
        }
    )
}