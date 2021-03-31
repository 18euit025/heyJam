
const getUserDetails = ()=>{
    $.get("http://localhost:8080/heyjam_war_exploded/UserProfileEdit",
        (responseText)=>{
            console.log(JSON.parse(responseText));
            let details = JSON.parse(responseText);
            let body = `
                <div class="namearea">
                    <input type="text" id="name"  value="Name: ${details.userProfile_Name}" name="name" disabled>
                </div>
                <div class="usernamearea">
                    <input type="text" id="username" value="Username: ${details.userProfile_Username}" name="username" disabled>
                </div>
                <div class="date">
                    <input type="text" id="dob" value="Date of Birth: ${details.userProfile_Dob}" name="dob" disabled>
                </div>
                <div class="mob">
                    <input type="text" id="mob" placeholder="Mobile: ${details.userProfile_Phone} (Update Mobile)" value="${details.userProfile_Phone}" name="mob">
                </div>
                <div class="instiname">
                    <input type="text" id="insti" value="Gender: ${details.userProfile_Gender}"  name="insti" disabled>
                </div>
                <div class="year">
                    <input type="text" id="year" value="You Can Only Update Your Phone Number"  name="year" disabled>
                </div>
                <div class="btn">
                    <button onclick="updateDetails()" style="background-color: #184770; border-color:wheat; color: white; width: 150px; height: 50px; border: 4px solid #184770; border-radius: 12px;" type="button">Update</button>
                </div>
                `;
            document.getElementById("details").innerHTML = body;
        });
}


window.onload = getUserDetails();

const updateDetails = ()=>{
    let phone = document.getElementById("mob").value.trim();
    if(phone.length===10){
        let phoneSend = JSON.stringify({
            phone: phone
        });
        const request = new Request(
            "http://localhost:8080/heyjam_war_exploded/UserProfileEdit",
            {
                method: 'POST',
                body: phoneSend
            }
        );
        fetch(request).
            then(response=>{
                console.log(response);
                if(response.status===2000){
                    return response.json();
                }else{
                    throw new Error("Something Wrong with API");
                }
        }).then(response=>{
            if(response.update) {
                window.location.href = "userProfileHome.jsp"
            }else{
                window.location.href = "userProfileHome.jsp"
            }
        }).catch(error=>{
            window.location.href = "userProfileHome.jsp"
        })
    }else{
        alert('incorrect phone field');
    }
}