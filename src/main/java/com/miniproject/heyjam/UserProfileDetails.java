package com.miniproject.heyjam;

import com.miniproject.heyjam.services.Components.UserInstitutionParticipantsRow;
import com.miniproject.heyjam.services.Components.UserProfileDetailStatus;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UserProfileDetails", value = "/UserProfileDetails")
public class UserProfileDetails extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String participantName = request.getParameter("userParticipant");
        try{
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(UserProfileDetailStatus.getUserDetails(participantName,username));
            response.getWriter().write(jsonString);
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
