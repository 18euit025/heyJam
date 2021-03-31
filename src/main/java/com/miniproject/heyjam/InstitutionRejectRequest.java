package com.miniproject.heyjam;

import com.miniproject.heyjam.services.databaseServices.UserInstitutionRelation;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "InstitutionRejectRequest", value = "/InstitutionRejectRequest")
public class InstitutionRejectRequest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        String userProfileUsername = request.getParameter("userProfileUsername");
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        try{
            if(UserInstitutionRelation.userInstitutionRelationRejectRequest(username,userProfileUsername)){
                System.out.println("true");
                response.getWriter().write("true");
            }else{
                System.out.println("false");
                response.getWriter().write("false");
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

}
