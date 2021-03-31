package com.miniproject.heyjam;

import com.miniproject.heyjam.services.databaseServices.UserUserRelation;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UserUserRemoveFollow", value = "/UserUserRemoveFollow")
public class UserUserRemoveFollow extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String participantUsername = request.getParameter("participant");
        try{
            if(UserUserRelation.userUserRelationRejectRequest(username,participantUsername)){
                response.getWriter().write("true");
            }else{
                response.getWriter().write("false");
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
