let head = `
        <tr>
            <th>Institution</th>
            <th>Total participants</th>
            <th>Your status</th>
        </tr>`;


const getInstitutions = (name) =>{
    if(name.trim().length!==0){
        $.get("http://localhost:8080/heyjam_war_exploded/GetInstitution",
            {name: name},
            function (responseText){
                let response = JSON.parse(responseText);
                if(response.flag){
                    let classValue = "btn btn-success";
                    let value = "follow";
                    if(response.element.institutionFollowStatus!==0){
                        if(response.element.institutionFollowStatus===1){
                            classValue = "btn btn-primary";
                            value = "requested";
                        }else{
                            classValue = "btn btn-danger";
                            value = "unfollow";
                        }
                    }
                    let body = `
                                <tr>
                                    <td>${response.element.institutionName}</td>
                                    <td>${response.element.institutionParticipantsCount}</td>
                                    <td><button id="${response.element.institutionUniqueName}" class="${classValue} btn-sm" value="${response.element.institutionUniqueName}-${response.element.institutionFollowStatus}" onclick="joinRequest(this.value)" style="margin-bottom: 3px;">${value}</button></td>
                                </tr>`;
                    document.getElementById("allInstitutions").innerHTML = head + body;
                }else{
                    document.getElementById("allInstitutions").innerHTML = head + `<tr><td>No</td><td>Contents to</td><td>Display</td></tr>`;
                }
            });
    }else{
        document.getElementById("allInstitutions").innerHTML = head + `<tr><td>No</td><td>Contents to</td><td>Display</td></tr>`;
    }
}


const joinRequest = (value)=>{
    let key = value.split("-");
    if(key[1]==="0") {
        console.log("request to follow");
        $.get(
            "http://localhost:8080/heyjam_war_exploded/RequestToInstitution",
            {institutionProfileUniqueName: key[0]},
            (responseText)=>{
                localStorage.setItem("requestForm",responseText);
                window.location.href = "requestInstitutionFollow.jsp"
            }
        );
    }else{
        console.log("delete follow request or unfollow");
        $.get(
            "http://localhost:8080/heyjam_war_exploded/DeleteRequestUnfollowInstitution",
            {institutionProfileUniqueName: key[0]},
            (responseText)=>{
                if(responseText==="true"){
                    getInstitutions(key[0]);
                }
            }
        );
    }
}

