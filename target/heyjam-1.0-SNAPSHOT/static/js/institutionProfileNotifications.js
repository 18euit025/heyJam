let head = `
         <tr>
            <th>Name</th>
            <th>Year joined</th>
            <th>Department</th>
            <th>Email ID</th>
            <th></th>
        </tr>`;

const getNotifications = ()=>{
    $.get(
        "http://localhost:8080/heyjam_war_exploded/InstitutionProfileNotifications",
        (responseText)=>{
            let data = JSON.parse(responseText);
            if(data.length === 0){
                let body = `
                <td>No</td>
                <td>Requests</td>
                <td>For</td>
                <td>Your</td>
                <td>Institutions</td>
                `;
                document.getElementById("notifications").innerHTML = head+body;
            }else{
                let body = "";
                data.forEach((user)=>{
                    let accept = user.userProfileUsername;
                    let reject = user.userProfileUsername;
                    let insert = `
                        <tr>
                        <td>${user.userProfileName}</td>
                        <td>${user.userInstitutionRelationYearJoined}</td>
                        <td>${user.getUserInstitutionRelationDepartment}</td>
                        <td>${user.userProfileEmail}</td>
                        <td><button type="button" value="${accept}" onclick="acceptRequest(this.value)" class="btn btn-success">accept</button>  <button value="${reject}" onclick="rejectRequest(this.value)" type="button" class="btn btn-danger">reject</button></td>
                        </tr>
                    `;
                    body = body + insert;
                    console.log(user);
                });
                document.getElementById("notifications").innerHTML = head + body;
            }
        }
    );
}

window.onload = getNotifications;

const acceptRequest = (value) =>{
    $.get(
        "http://localhost:8080/heyjam_war_exploded/AcceptUserRequest",
        {userProfileUsername:value},
        ()=>{
            let x = document.getElementById("snackbar");
            document.getElementById("snackbar").innerText = "Request Accepted"
            x.className = "show";
            setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
            getNotifications();
        }
        );
}

const rejectRequest = (value) =>{
    $.get(
        "http://localhost:8080/heyjam_war_exploded/InstitutionRejectRequest",
        {userProfileUsername:value},
        ()=>{
            let x = document.getElementById("snackbar");
            document.getElementById("snackbar").innerText = "Request Rejected"
            x.className = "show";
            setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
            getNotifications();
        }
    );
}

