let head = `
        <tr>
            <th>Institutions</th>
            <th>Username</th>
            <th>Name</th>
            <th>Email</th>
            <th></th>
        </tr>`;
const getFollowing = ()=>{
    $.get(
        "http://localhost:8080/heyjam_war_exploded/UserProfileFollower",
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
                let buttonType = "";
                let status = individual.userUserFollowStatus;
                if(status===4){
                    buttonType = `<button value="${individual.userProfileUsernameParticipant}-${individual.userUserFollowStatus}" class="btn btn-primary btn-sm" onclick="followStatusProcess(this.value)">accept</button>`;
                    buttonType = buttonType + `<button value="${individual.userProfileUsernameParticipant}-6" class="btn btn-danger btn-sm" onclick="followStatusProcess(this.value)">reject</button>`
                }else if(status===1){
                    buttonType = `<button value="${individual.userProfileUsernameParticipant}-${individual.userUserFollowStatus}" class="btn btn-danger btn-sm" onclick="followStatusProcess(this.value)">unfollow</button>`
                }else if(status===2){
                    buttonType = `<button value="${individual.userProfileUsernameParticipant}-${individual.userUserFollowStatus}" class="btn btn-primary btn-sm" onclick="followStatusProcess(this.value)">requested</button>`;
                }else if(status===3){
                    buttonType = `<button value="${individual.userProfileUsernameParticipant}-${individual.userUserFollowStatus}" class="btn btn-success btn-sm" onclick="followStatusProcess(this.value)">follow back</button>`;
                }else{
                    buttonType = `<button value="${individual.userProfileUsernameParticipant}-${individual.userUserFollowStatus}" class="btn btn-success btn-sm" onclick="followStatusProcess(this.value)">follow</button>`;
                }
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

window.onload = getFollowing;


const followStatusProcess = (value)=>{
    let usernameParticipant = value.split("-")[0];
    let followStatus = value.split("-")[1];
    console.log(followStatus);
    if(followStatus==="1" || followStatus==="2"){
        $.get(
            "http://localhost:8080/heyjam_war_exploded/UserUserRemoveFollow",
            {participant:usernameParticipant},
            (responseText)=>{
                if(responseText==="true"){
                    getFollowing();
                }
            }
        );
    }else if((followStatus==="3") || (followStatus==="5")){
        $.get(
            "http://localhost:8080/heyjam_war_exploded/UserUserMakeFollowRequest",
            {participant:usernameParticipant},
            (responseText)=>{
                if(responseText==="true"){
                    getFollowing();
                }
            }
        );
    }else if(followStatus==="6"){
        $.get(
            "http://localhost:8080/heyjam_war_exploded/UserUserRejectRequest",
            {participant:usernameParticipant},
            (responseText)=>{
                if(responseText==="true"){
                    getFollowing();
                }
            }
        );
    } else{
        $.get(
            "http://localhost:8080/heyjam_war_exploded/UserUserAcceptRequest",
            {participant:usernameParticipant},
            (responseText)=>{
                if(responseText==="true"){
                    getFollowing();
                }
            }
        );
    }
}

const userDetails = (value)=>{
    localStorage.setItem('userProfileDetail',value);
    window.location.href = 'userProfileDetails.jsp';
}