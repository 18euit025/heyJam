let head = `
        <tr>
            <th>Institution ID</th>
            <th>Username</th>
            <th>Name</th>
            <th>Year joined</th>
            <th>Email</th>
            <th>Department</th>
        </tr>`;

const getInstitutionFollowers = ()=>{
    let institutionUniqueName = localStorage.getItem("institution");
    document.getElementById("detailsHead").innerText = institutionUniqueName+" Details";
    $.get(
        "http://localhost:8080/heyjam_war_exploded/InstitutionParticipants",
        {institutionUniqueName: institutionUniqueName},
        (responseTest)=>{
            let individuals = JSON.parse(responseTest);
            let body = "";
            individuals.forEach((individual)=>{
                let construct = `
                <tr>
                    <td>${individual.institutionProfileUniqueName}</td>
                    <td><button class="badge bg-dark" onclick="userDetails(this.value)" value="${individual.userProfileUsernameParticipant}">${individual.userProfileUsernameParticipant}</button></td>
                    <td>${individual.userProfileNameParticipant}</td>
                    <td>${individual.userInstitutionRelationYearJoinedParticipant}</td>
                    <td>${individual.userInstitutionRelationDepartmentParticipant}</td>
                    <td>${individual.userProfileEmailParticipant}</td>
                </tr>
              `;
                body = body + construct;
            });
            document.getElementById("participants").innerHTML = head + body;
        });
}

window.onload = getInstitutionFollowers;

let userDetails = (value)=>{
    console.log(value);
}