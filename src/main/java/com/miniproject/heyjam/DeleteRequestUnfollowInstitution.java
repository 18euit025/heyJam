package com.miniproject.heyjam;

import com.miniproject.heyjam.services.databaseServices.UserInstitutionRelation;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "DeleteRequestUnfollowInstitution", value = "/DeleteRequestUnfollowInstitution")
public class DeleteRequestUnfollowInstitution extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        String institutionUniqueName = request.getParameter("institutionProfileUniqueName");
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        try {
            boolean success = UserInstitutionRelation.userInstitutionRelationRejectRequest(institutionUniqueName,username);
            System.out.println(institutionUniqueName);
            if (success){
                response.getWriter().write("true");
            }else{
                response.getWriter().write("false");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

