
const getUserDetails = ()=>{
    $.get(
        "http://localhost:8080/heyjam_war_exploded/UserProfileDetails",
        {userParticipant: localStorage.getItem("userProfileDetail")},
        (responseText)=>{
            let details = JSON.parse(responseText);
            console.log(details);
            if(details.accessSpecifier==="true"){
                let body = `
                <div class="namearea">
                    <input type="text" id="name"  value="Name: ${details.userProfileName}" name="name" disabled>
                </div>
                <div class="usernamearea">
                    <input type="text" id="username" value="Username: ${details.userProfileUsername}" name="username" disabled>
                </div>
                <div class="date">
                    <input type="text" id="dob" value="Date of Birth: ${details.userProfileDOB}" name="dob" disabled>
                </div>
                <div class="mob">
                    <input type="text" id="mob" value="Mobile: ${details.userProfileMobile}"  name="mob" disabled>
                </div>
                <div class="instiname">
                    <input type="text" id="insti" value="Part of Institutions: ${details.userProfileInstitutions}"  name="insti" disabled>
                </div>
                <div class="year">
                    <input type="text" id="year" value="Email: ${details.userProfileEmail}"  name="year" disabled>
                </div>
                `;
                document.getElementById("details").innerHTML = body;
            }else{
                let body = `
                <div class="namearea">
                    <input type="text" id="name"  value="Name: ${details.userProfileName}" name="name" disabled>
                </div>
                <div class="usernamearea">
                    <input type="text" id="username" value="Username: ${details.userProfileUsername}" name="username" disabled>
                </div>
                <div class="date">
                    <input type="text" id="dob" value="Part of Institutions: ${details.userProfileInstitutions}"  name="dob" disabled>
                </div>
                <div class="mob">
                    <input type="text" id="mob" value="Email: ${details.userProfileEmail}"  name="mob" disabled>
                </div>
                <div class="instiname">
                    <input type="text" id="insti" value="You Cannot Access"  name="insti" disabled>
                </div>
                <div class="year">
                    <input type="text" id="year" value="Private Account"  name="year" disabled>
                </div>
                `;
                document.getElementById("details").innerHTML = body;
            }
        }
    )
}

window.onload = getUserDetails;