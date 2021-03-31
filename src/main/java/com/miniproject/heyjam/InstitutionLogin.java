package com.miniproject.heyjam;

import com.miniproject.heyjam.services.databaseServices.InstitutionProfile;
import com.miniproject.heyjam.services.otherServices.GetRequestBody;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "InstitutionLogin", value = "/InstitutionLogin")
public class InstitutionLogin extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
            response.setContentType("text/html");
            String requestBody = GetRequestBody.getBody(request);
            JSONObject user = new JSONObject(requestBody);
            String username = user.getString("username");
            String password = user.getString("password");
            InstitutionProfile profile = InstitutionProfile.verifyInstitutionProfile(username, password);
            if(profile!=null){
                ObjectMapper mapper = new ObjectMapper();
                String jsonString = mapper.writeValueAsString(profile);
                String send = "{\"account\": true,\"user\":"+jsonString+"}";
                HttpSession session = request.getSession();
                session.setAttribute("username",username);
                response.getWriter().write(send);
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
}

