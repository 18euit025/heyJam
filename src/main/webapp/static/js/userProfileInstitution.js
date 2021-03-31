

const getUserInstitutionEvents = ()=>{
    let body = "<h2>No Events To Display</h2>";
    $.get(
        "http://localhost:8080/heyjam_war_exploded/UserInstitutionEvents",
        {institutionProfileUniqueName: localStorage.getItem('institution')},
        (responseText)=>{
           let events = JSON.parse(responseText);
           document.getElementById("institutionName").innerText = localStorage.getItem('institution')+" Events";
            if(events.length!==0) {
                let elements = "";
                events.forEach((event) => {
                    let sentToYear = event.institutionEventTargetRangeTo;
                    let replace = "all";
                    if(sentToYear!=="all"){
                        replace = sentToYear;
                    }
                    let consturct = `
                        <div class="container" style=" margin-left: 345px;margin-bottom: 0px;">
                            <div class="card" style="width: 990px; ">
                                <div class="card-body">
                                    <div class="row">
                                        <div class="col-7" >
                                            <h3 style="color: #184770;">${event.institutionEventTitle}</h3>
                                        </div>
                                        <div class="col-3">
                                            
                                        </div>
                                        <div style="margin-left:0px;" class="col-2">
                                            
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-9">
                                            <p>${event.institutionEventContent}</p><br>
                                            <p>${event.institutionEventLinkTitle} <span><a href="${event.institutionEventLink}">${event.institutionEventLink}</a></span></p> 
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <hr style="background-color:black; margin-left: 360px; width: 990px;">
                    `;
                    elements = elements + consturct;
                });
                document.getElementById("institutionEvents").innerHTML = elements;

            }
            else{
                document.getElementById("institutionEvents").innerHTML = body;
            }
        });
}

window.onload = getUserInstitutionEvents;

