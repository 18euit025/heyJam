// User Signup Form - Registration and it's Validations
let usernameValidator = false;
let nameValidator = false;
let emailValidator = false;
let phoneValidator = false;
let dateValidator = false;
let passwordValidator = false;
let rePasswordValidator = false;
$("#registerUser").click(()=>{
    let name = $("#signName").val();
    let userName = $("#signUsername").val();
    let email = $("#signEmail").val();
    let mobile = $("#signMobile").val();
    let date = $("#signDate").val();
    let gender = $('input[name="gender"]:checked').val();
    let password = $("#signPassword").val();
    if(usernameValidator && nameValidator && emailValidator && phoneValidator && dateValidator && rePasswordValidator && rePasswordValidator && (gender==="M" || gender==="F" || gender==="O")){
        // console.log(`${name} ${userName} ${email} ${mobile} ${date} ${gender} ${password}`);
        let values = JSON.stringify(
            {
                name: name,
                username: userName,
                email: email,
                mobile: mobile,
                date: date,
                gender: gender,
                password: password
            }
        );
        const request = new Request(
            'http://localhost:8080/heyjam_war_exploded/SignUpUserProfile',
            {
                method: 'POST',
                body: values
            }
            );
        fetch(request).
            then(response => {
                if(response.status === 200){
                    return response.json();
                }else{
                    throw new Error('Something Wrong With API');
                }
        }).then(response =>{
            if(response.creation){
                $("#signName").val("");
                $("#signUsername").val("");
                $("#signEmail").val("");
                $("#signMobile").val("");
                $("#signDate").val("");
                $("input[name='"+gender+"'][value='"+$('input[name="gender"]:checked').val()+"']").prop('checked', false);
                $("#signPassword").val("");
                $("#signRePassword").val("");
                alert("User Creation Successful: Now you can Login");
            }
        }).catch(error =>{
            alert("Try Again to Register "+error);
        });
    }else{
        $("#warning").html(`<p style="color: burlywood;margin-left: 10px">*Make Sure That You Have Properly Filled All Fields</p>`)
    }
});

const verifyName = (name)=>{
    let compare = /^[a-zA-Z]+$/;
    if(!compare.exec(name.trim())){
        $("#nameWarning").html(`<p style="color: burlywood;margin-left: 10px">*Name cannot be empty or Name can contain only alphabets</p>`);
        nameValidator = false;
    }else{
        $("#nameWarning").html("");
        nameValidator = true;
    }
}

const verifyUsername = (userName)=>{
    if(userName.trim().length>5){
        $.get("http://localhost:8080/heyjam_war_exploded/CheckUserProfile",
            {username: userName},
            (responseText)=>{
                if(responseText==="true"){
                    $("#usernameWarning").html(`<p style="color: burlywood; margin-left: 10px">*Username available</p>`)
                    usernameValidator = true;
                }else {
                    $("#usernameWarning").html(`<p style="color: burlywood; margin-left: 10px">*Username not available</p>`)
                    usernameValidator = false;
                }
            }
        );

    }else{
        $("#usernameWarning").html(`<p style="color: burlywood;margin-left: 10px">*length of Username should be greater than 5</p>`);
        usernameValidator = false;
    }
}

const verifyEmail = (email)=>{
    let compare = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
    if(!compare.exec(email.trim())){
        $("#emailWarning").html(`<p style="color: burlywood;margin-left: 10px">*email format wrong</p>`);
        emailValidator = false;
    }else{
        $("#emailWarning").html("");
        emailValidator = true;
    }
}

const verifyPhone = (phone)=>{
    let compare = /^[789]\d{9}$/;
    if(!compare.exec(phone.trim())){
        $("#phoneWarning").html(`<p style="color: burlywood;margin-left: 10px">*phone format wrong</p>`);
        phoneValidator = false;
    }else{
        $("#phoneWarning").html("");
        phoneValidator = true;
    }
}

const verifyDate = (date)=>{
    let compare = /([12]\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\d|3[01]))/;
    if(!compare.exec(date)){
        $("#dateWarning").html(`<p style="color: burlywood;margin-left: 10px">*date format wrong</p>`);
        dateValidator = false;
    }else{
        $("#dateWarning").html("");
        dateValidator = true;
    }
}

const verifyPassword = (password)=>{
    if(password.trim().length<6){
        $("#passwordWarning").html(`<p style="color: burlywood;margin-left: 10px">*password length should be greater than 6</p>`);
        passwordValidator = false;
    }
    else{
        $("#passwordWarning").html("");
        passwordValidator = true;
    }
}

const verifyRePassword = (rePassword)=>{
    let password = $("#signPassword").val();
    if(password === rePassword){
        $("#rePasswordWarning").html("");
        rePasswordValidator = true;
    }else{
        $("#rePasswordWarning").html(`<p style="color: burlywood;margin-left: 10px">*password does not match</p>`);
        rePasswordValidator = false;
    }
}

// User & Institution Login Form and it's validations
$("#login").click(()=>{
   let username = $("#loginUsername").val();
   let password = $("#loginPassword").val();
   let profile = $("#profileOption").val();
   if(!((username.trim().length>5) && (password.trim().length>5))){
       $("#loginWarning").text("Verify Username and Password");
   }else{
       let details = JSON.stringify({
               username: username,
               password: password
           }
       );
       if(profile==="1"){
           login('http://localhost:8080/heyjam_war_exploded/UserLogin',details,profile,username);
       }else{
           login('http://localhost:8080/heyjam_war_exploded/InstitutionLogin',details,profile,username);
       }
   }
});


const login = (url,details,key,username) =>{
    const request = new Request(
        url,
        {
            method: 'POST',
            body: details
        }
    );
    fetch(request).
    then(response => {
        if(response.status===200){
            return response.json();
        }else{
            throw new Error("Something wrong with API");
        }
    }).then(response => {
        console.log(username);
        localStorage.setItem('institution',username);
        if(response.account){
            if(key==="1") {
                window.location.href = "http://localhost:8080/heyjam_war_exploded/userProfileHome.jsp";
            }else{
                window.location.href = "http://localhost:8080/heyjam_war_exploded/institutionProfileHome.jsp";
            }
        }else{
            alert("incorrect username or password");
        }
    }).catch(error =>{
        alert(error);
    })
}


