document.getElementById("addEvent").addEventListener('click',()=>{
    let eventTitle = document.getElementById("title").value;
    let eventContent = document.getElementById("createvent").value;
    let eventExpiryDate = document.getElementById("date").value;
    let eventCreationDate = document.getElementById("To").value;
    let eventTargetYear = document.getElementById("from").value;
    let eventLinkTitle = document.getElementById("linktitle").value;
    let eventLink = document.getElementById("link").value;
    let values = JSON.stringify(
        {
            eventTitle: eventTitle,
            eventContent: eventContent,
            eventExpiryDate: eventExpiryDate,
            eventTargetYear: eventTargetYear,
            eventLinkTitle: eventLinkTitle,
            eventLink: eventLink,
            eventCreationDate: eventCreationDate
        }
    );
    const request = new Request(
        'http://localhost:8080/heyjam_war_exploded/InstitutionCreateEvent',
        {
            method: 'POST',
            body: values
        }
    );
    fetch(request).
        then(response=>{
            if(response.status===200){
                return response.json();
            }else {
                throw new Error("Error in API");
            }
    }).then(response=>{
        window.location.href = "institutionProfileHome.jsp";
    }).catch(error=>{
        alert(error);
    })
});