
const loadData = ()=>{
    let data = JSON.parse(localStorage.getItem('requestForm'));
    document.getElementById("title").value = data.institutionProfileName;
    document.getElementById("description").value = data.institutionProfileDescription;
    document.getElementById("email").value = data.userProfileEmail;
    document.getElementById("mobnumber").value = data.userProfilePhone;
    document.getElementById("joinyear").value = data.userInstitutionRelationJoiningYear;
    document.getElementById("depart").value = data.userInstitutionRelationDepartment;
}

window.onload = loadData;

document.getElementById("sendRequest").addEventListener('click',()=>{
   let data = JSON.parse(localStorage.getItem('requestForm'));
   let userProfileUsername = data.userProfileUsername;
   let institutionProfileUniqueName = data.institutionProfileUniqueName;
   let userInstitutionRelation_JoiningYear =  document.getElementById("joinyear").value;
   let userInstitutionRelation_Department =  document.getElementById("depart").value;
   let requestBody = JSON.stringify(
       {
           userProfileUsername: userProfileUsername,
           institutionProfileUniqueName: institutionProfileUniqueName,
           userInstitutionRelation_JoiningYear: userInstitutionRelation_JoiningYear,
           userInstitutionRelation_Department: userInstitutionRelation_Department
       }
   );
   const request = new Request(
       'http://localhost:8080/heyjam_war_exploded/RequestToInstitution',
       {
           method: 'POST',
           body: requestBody
       }
    );
   fetch(request).
       then(response=>{
           if(response.status===200){
               return response.json()
           }else{
               throw new Error("Something went wrong");
           }
   }).then(response=>{
       if(response.requestStatus){
           alert("Request Sent");
           localStorage.removeItem("requestForm");
           window.location.href = "findInstitutions.jsp";
       }else{
           localStorage.removeItem("requestForm");
            alert("try again");
       }
   }).catch(error=>{
       localStorage.removeItem("requestForm");
       alert(error);
    });
});

window.onbeforeunload = function(){
    localStorage.removeItem("requestForm");
};