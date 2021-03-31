package com.miniproject.heyjam;

import com.miniproject.heyjam.services.databaseServices.UserProfile;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "CheckUserProfile", value = "/CheckUserProfile")
public class CheckUserProfile extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        String username = request.getParameter("username");
        try {
            UserProfile profile = UserProfile.getUserProfile(username);
            if(profile==null){
                response.getWriter().write("true");
            }
            else{
                response.getWriter().write("false");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

