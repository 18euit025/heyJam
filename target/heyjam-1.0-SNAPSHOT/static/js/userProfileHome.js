let head = `
        <tr>
            <th>Institution</th>
            <th>Total participants</th>
            <th></th>
        </tr>`;


const getMyInstitution = ()=>{
    $.get(
        "http://localhost:8080/heyjam_war_exploded/GetMyInstitutions",
        (responseText)=>{
            document.getElementById("allInstitutions").innerHTML = head;
            let rows='';
            let response = JSON.parse(responseText);
            if(response.length!==0) {
                response.forEach((element) => {
                    console.log(element);
                    let body = `
                                <tr >
                                    <td>${element.institutionName}</td>
                                    <td>${element.institutionParticipantsCount}</td>
                                    <td><button class="btn-success btn-sm" onclick="eventInstitution(this.value)" value="${element.institutionUniqueName}"  style="margin-bottom: 3px;">Events</button>
                                    <button class="btn-danger btn-sm" onclick="surveyInstitution(this.value)" value="${element.institutionUniqueName}"  style="margin-bottom: 3px;">Surveys</button>
                                    <button class="btn-dark btn-sm" onclick="detailsInstitution(this.value)" value="${element.institutionUniqueName}"  style="margin-bottom: 3px;">Details</button></td>
                                </tr>`;
                    rows = rows + body;
                });
                document.getElementById("allInstitutions").innerHTML = head+rows;
            }else{
                document.getElementById("allInstitutions").innerHTML = head + `<tr><td>No</td><td>Contents to</td><td>Display</td></tr>`;
            }
        }
    );
}


window.onload = getMyInstitution;

const eventInstitution = (value)=>{
    localStorage.setItem("institution",value);
    window.location.href = "userProfileInstitution.jsp"
}

const surveyInstitution = (value)=>{
    localStorage.setItem("institution",value);
    window.location.href = "userProfileInsitutionSurvey.jsp"
}

const detailsInstitution = (value)=>{
    localStorage.setItem("institution",value);
    window.location.href = "userInstitutionDetails.jsp";
}