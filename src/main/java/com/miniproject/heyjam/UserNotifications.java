package com.miniproject.heyjam;

import com.miniproject.heyjam.services.Components.UserInstitutionParticipantsRow;
import com.miniproject.heyjam.services.databaseServices.UserUserRelation;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "UserNotifications", value = "/UserNotifications")
public class UserNotifications extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        try{
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(UserInstitutionParticipantsRow.userUserNotifications(username));
            response.getWriter().write(jsonString);
        }catch(Exception e){
            System.out.println(e);
        }
    }
}

