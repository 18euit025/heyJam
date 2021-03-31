package com.miniproject.heyjam;

import com.miniproject.heyjam.services.databaseServices.UserProfile;
import com.miniproject.heyjam.services.otherServices.GetRequestBody;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@WebServlet(name = "UserLogin", value = "/UserLogin")
public class UserLogin extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
        response.setContentType("text/html");
        String requestBody = GetRequestBody.getBody(request);
        JSONObject user = new JSONObject(requestBody);
        String username = user.getString("username");
        String password = user.getString("password");

            UserProfile profile = UserProfile.verifyUserProfile(username,password);
            if(profile!=null){
                ObjectMapper mapper = new ObjectMapper();
                String jsonString = mapper.writeValueAsString(profile);
                String send = "{\"account\": true,\"user\":"+jsonString+"}";
                HttpSession session = request.getSession();
                session.setAttribute("username",username);
                response.getWriter().write(send);
            }else{
                String send = "{\"account\": false,\"user\":{}}";
                response.getWriter().write(send);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
