let head = `
        <tr>
            <th>Institutions</th>
            <th>Username</th>
            <th>Name</th>
            <th>Email</th>
            <th></th>
        </tr>`;
const getFollowRequests = ()=>{
    document.getElementById("detailsHead").innerText = "Follow Requests";
    $.get(
        "http://localhost:8080/heyjam_war_exploded/UserNotifications",
        (responseText)=>{
            let individuals = JSON.parse(responseText);
            console.log(individuals);
            let body = "";
            individuals.forEach((individual)=>{
                let institutions = individual.institutionProfileUniqueName.split(" ");
                let institutionBadges = "";
                institutions.forEach((institution)=>{
                    institutionBadges = institutionBadges + `<button value=${institution} onclick="details(this.value)" class="badge bg-dark">${institution}</button> `;
                })
                buttonType = `<button value="${individual.userProfileUsernameParticipant}" class="btn btn-primary btn-sm" onclick="acceptRequest(this.value)">accept</button>`;
                buttonType = buttonType + `<button value="${individual.userProfileUsernameParticipant}" class="btn btn-danger btn-sm" onclick="rejectRequest(this.value)">reject</button>`
                let construct = `
                <tr>
                    <td>${institutionBadges}</td>
                    <td><button class="badge bg-dark" onclick="userDetails(this.value)" value="${individual.userProfileUsernameParticipant}">${individual.userProfileUsernameParticipant}</button></td>
                    <td>${individual.userProfileNameParticipant}</td>
                    <td>${individual.userProfileEmailParticipant}</td>
                    <td id="${individual.userProfileUsernameParticipant}">${buttonType}</td>
                </tr>
              `;
                body = body + construct;
            });
            document.getElementById("participants").innerHTML = head + body;
            });
}

window.onload = getFollowRequests;

const acceptRequest = (username) =>{
    $.get(
        "http://localhost:8080/heyjam_war_exploded/UserUserAcceptRequest",
        {participant:username},
        (responseText)=>{
            if(responseText==="true"){
                document.getElementById(username).innerText = 'Request Accepted';
            }
        }
    );
}

const rejectRequest = (username) =>{
    $.get(
        "http://localhost:8080/heyjam_war_exploded/UserUserRejectRequest",
        {participant:username},
        (responseText)=>{
            if(responseText==="true"){
                document.getElementById(username).innerText = 'Request Rejected';
            }
        }
    );
}

const details = (institution)=>{
    // localStorage.setItem('institution',institution);
    // window.location.href = "userInstitutionDetails.jsp";
}