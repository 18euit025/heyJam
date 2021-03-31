let institutionProfileUniqueNameValidator = false;
let institutionProfileNameValidator = false;
let institutionProfileDescriptionValidator = false;
let institutionProfileEmailValidator = false;
let institutionProfilePasswordValidator = false;
let institutionProfileRePasswordValidator = false;
let institutionProfilePhoneValidator = false;
let institutionProfileActivationKayValidator = false;
let uniqueNameFinal;

document.getElementById("createInstitution").addEventListener('click',()=>{
   if(institutionProfileUniqueNameValidator&&institutionProfileNameValidator&&institutionProfileDescriptionValidator&&institutionProfileEmailValidator&&institutionProfilePasswordValidator&&institutionProfileRePasswordValidator&&institutionProfilePhoneValidator){
       let name = document.getElementById("institutionName").value;
       let uniqueName = document.getElementById("institutionUniqueName").value;
       let description = document.getElementById("institutionDescription").value;
       let email = document.getElementById("institutionEmail").value;
       let password = document.getElementById("institutionPassword").value;
       let phone = document.getElementById("institutionPhone").value;
       console.log(name+" "+uniqueName+" "+description+" "+email+" "+password+" "+phone);
       document.getElementById("loadingBar").innerHTML = `
       <div class="spinner-border text-primary" style="align-items: center" role="status">
           <span class="sr-only"></span>
       </div>
       `;
       let values = JSON.stringify(
           {
               uniqueName: uniqueName,
               name: name,
               description: description,
               email: email,
               password: password,
               phone: phone
           }
       );
       const request = new Request(
           'http://localhost:8080/heyjam_war_exploded/SignUpInstitutionProfile',
           {
               method: 'POST',
               body: values
           }
       );
       fetch(request).
           then(response =>{
               if(response.status===200){
                   return response.json();
               }else{
                   throw new Error("Error in Creation");
           }
       }).then(response =>{
           if(response.creation){
                document.getElementById("loadingBar").innerHTML = "";
                document.getElementById("activationKey").style.visibility = "visible";
                uniqueNameFinal = uniqueName;
           }else{
               document.getElementById("activationKey").innerHTML = "<h3>Error</h3>";
           }
       }).catch(error=>{
          alert(error);
       });
   }else{
       $("#warning").html(`<p style="color: burlywood;margin-left: 10px">*Make Sure That You Have Properly Filled All Fields</p>`)
   }
});

const institutionProfileUniqueName = (uniqueName) => {
    if(uniqueName.length<6){
        document.getElementById("uniqueNameWarning").innerHTML = "<p id=\"uniqueNameWarning\" style=\"color: burlywood; margin-left: 10px\">*length should be greater than 5</p>";
        institutionProfileUniqueNameValidator = false;
    }else{
        $.get(
            "http://localhost:8080/heyjam_war_exploded/CheckInstitutionProfile",
            {uniqueName: uniqueName},
            (responseText)=>{
                if(responseText==="true"){
                    $("#uniqueNameWarning").html(`<p style="color: burlywood; margin-left: 10px">*Username available</p>`);
                    institutionProfileUniqueNameValidator = true;
                }else{
                    $("#uniqueNameWarning").html(`<p style="color: burlywood; margin-left: 10px">*Username not available</p>`);
                    institutionProfileUniqueNameValidator = false;
                }
            });
        document.getElementById("uniqueNameWarning").innerHTML = "";
    }
}

const institutionProfileName = (name) => {
    let compare = /^[a-zA-Z_ ]*$/;
    if(!compare.exec(name.trim())){
        $("#warning").html(`<p style="color: burlywood;margin-left: 10px">*Name cannot be empty or Name can contain only alphabets</p>`);
        institutionProfileNameValidator = false;
    }else{
        $("#warning").html("");
        institutionProfileNameValidator = true;
    }
}

const institutionProfileDescription = (description) => {
    if(description.trim().length===0){
        document.getElementById("warning").innerHTML = `<p style="color: burlywood;margin-left: 10px">*Description Cannot be Empty</p>`;
        institutionProfileDescriptionValidator = false;
    }
    else{
        document.getElementById("warning").innerHTML = "";
        institutionProfileDescriptionValidator = true;
    }
}

const institutionProfileEmail = (email) => {
    let compare = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
    if(!compare.exec(email.trim())){
        $("#warning").html(`<p style="color: burlywood;margin-left: 10px">*email format wrong</p>`);
        institutionProfileEmailValidator = false;
    }else{
        $("#warning").html("");
        institutionProfileEmailValidator = true;
    }
}

const institutionProfilePassword = (password) => {
    if(password.trim().length<6){
        $("#passwordWarning").html(`<p style="color: burlywood;margin-left: 10px">*password length should be greater than 6</p>`);
        institutionProfilePasswordValidator = false;
    }
    else{
        $("#passwordWarning").html("");
        institutionProfilePasswordValidator = true;
    }
}

const institutionProfileRePassword = (rePassword) => {
    let password = $("#institutionPassword").val();
    if(password === rePassword){
        $("#warning").html("");
        institutionProfileRePasswordValidator = true;
    }else{
        $("#warning").html(`<p style="color: burlywood;margin-left: 10px">*password does not match</p>`);
        institutionProfileRePasswordValidator = false;
    }
}

const institutionProfilePhone = (phone) => {
    let compare = /^[789]\d{9}$/;
    if(!compare.exec(phone.trim())){
        $("#warning").html(`<p style="color: burlywood;margin-left: 10px">*phone format wrong</p>`);
        institutionProfilePhoneValidator = false;
    }else{
        $("#warning").html("");
        institutionProfilePhoneValidator = true;
    }
}

document.getElementById("verifyActivation").addEventListener('click',()=>{
    let activationKey = document.getElementById("actikey").value;
    $.get(
        "http://localhost:8080/heyjam_war_exploded/VerifyActivationKey",
        {
            uniqueName: uniqueNameFinal,
            activationKey: activationKey
        },
        (responseText)=>{
            if(responseText==="true"){
                alert("creation Successful");
                window.location.href = "http://localhost:8080/heyjam_war_exploded/index.jsp";
            }else{
                document.getElementById("activationWarning").innerText = "Activation Key might be Wrong check again";
            }
        }
    );
});