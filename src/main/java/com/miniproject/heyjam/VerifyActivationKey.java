package com.miniproject.heyjam;

import com.miniproject.heyjam.services.databaseServices.InstitutionProfile;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "VerifyActivationKey", value = "/VerifyActivationKey")
public class VerifyActivationKey extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String uniqueName = request.getParameter("uniqueName");
        String activationKey = request.getParameter("activationKey");
        try {
            if(InstitutionProfile.activateCheckInstitution(uniqueName,activationKey)){
                if (InstitutionProfile.activateInstitution(uniqueName)) {
                    response.getWriter().write("true");
                } else {
                    response.getWriter().write("false");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
