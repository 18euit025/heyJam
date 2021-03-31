package com.miniproject.heyjam;

import com.miniproject.heyjam.services.databaseServices.InstitutionProfile;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CheckInstitutionProfile", value = "/CheckInstitutionProfile")
public class CheckInstitutionProfile extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        String uniqueName = request.getParameter("uniqueName");
        System.out.println(uniqueName);
        try {
            InstitutionProfile profile = InstitutionProfile.getInstitutionProfile(uniqueName);
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
