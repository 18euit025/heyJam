document.getElementById("addSurvey").addEventListener('click',()=>{
    let surveyTitle = document.getElementById("title").value;
    let surveyContent = document.getElementById("createvent").value;
    let surveyExpiryDate = document.getElementById("date").value;
    let surveyTarget = document.getElementById("To").value;
    let surveyOptionA = document.getElementById("option1").value;
    let surveyOptionB = document.getElementById("option2").value;
    let surveyCreationDate = document.getElementById("from").value;
    let value = JSON.stringify(
        {
            surveyTitle: surveyTitle,
            surveyContent: surveyContent,
            surveyExpiryDate: surveyExpiryDate,
            surveyTarget: surveyTarget,
            surveyOptionA: surveyOptionA,
            surveyOptionB: surveyOptionB,
            surveyCreationDate: surveyCreationDate
        }
    );
    const request = new Request(
        'http://localhost:8080/heyjam_war_exploded/InstitutionCreateSurvey',
        {
            method: 'POST',
            body: value
        }
    );
    fetch(request).
    then(response=>{
        if(response.status===200){
            return response.json();
        }else {
            throw new Error("Error in API");
        }
    }).then(response=>{
        window.location.href = "institutionSurvey.jsp";
    }).catch(error=>{
        alert(error);
    })
});