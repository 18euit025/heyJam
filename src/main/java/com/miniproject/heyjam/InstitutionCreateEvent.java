package com.miniproject.heyjam;

import com.miniproject.heyjam.services.databaseServices.InstitutionEvent;
import com.miniproject.heyjam.services.otherServices.GetRequestBody;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "InstitutionCreateEvent", value = "/InstitutionCreateEvent")
public class InstitutionCreateEvent extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestBody = GetRequestBody.getBody(request);
        JSONObject event = null;
        try {
            event = new JSONObject(requestBody);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        try {
            InstitutionEvent.createEvent(
                    username,
                    event.getString("eventTitle"),
                    event.getString("eventContent"),
                    event.getString("eventExpiryDate"),
                    event.getString("eventTargetYear"),
                    event.getString("eventLinkTitle"),
                    event.getString("eventLink"),
                    event.getString("eventCreationDate")
            );
            response.getWriter().write("{}");
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
