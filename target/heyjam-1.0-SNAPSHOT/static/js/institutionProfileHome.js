const getEvents = ()=>{
    let body = "<h2>No Events To Display</h2>";

    $.get(
        "http://localhost:8080/heyjam_war_exploded/GetEventsOfInstitution",
        (responseText)=>{
           let events = JSON.parse(responseText);
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
                                            <h3 style="color: #184770;">${event.institutionEventTitle}<span class="badge bg-success"> ${replace}</span></h3>
                                        </div>
                                        <div class="col-3">
                                            
                                        </div>
                                        <div style="margin-left:0px;" class="col-2">
                                            <button style="width: 85px" value="${event.institutionEventId}" onclick="removeEvent(this.value)" class="badge bg-danger">X</button>
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
               document.getElementById("allEvents").innerHTML = elements;

           }
           else{
               document.getElementById("allEvents").innerHTML = body;
           }
        });
}

window.onload = getEvents;

const removeEvent = (value)=>{
    $.get(
        "http://localhost:8080/heyjam_war_exploded/RemoveInstitutionEvent",
        {eventId: value},
        ()=>{
            getEvents();
        }
    )
}

document.getElementById("createEvent").addEventListener('click',()=>{
    window.location.href = "institutionCreateEvent.jsp";
})