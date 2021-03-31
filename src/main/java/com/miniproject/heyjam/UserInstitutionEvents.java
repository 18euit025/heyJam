package com.miniproject.heyjam;

import com.miniproject.heyjam.services.Components.UserInstitutionEventRow;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "UserInstitutionEvents", value = "/UserInstitutionEvents")
public class UserInstitutionEvents extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String userProfileUsername = (String) session.getAttribute("username");
        String institutionName = request.getParameter("institutionProfileUniqueName");
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = null;
        try {
            jsonString = mapper.writeValueAsString(UserInstitutionEventRow.getEvents(institutionName,userProfileUsername));
            response.getWriter().write(jsonString);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

