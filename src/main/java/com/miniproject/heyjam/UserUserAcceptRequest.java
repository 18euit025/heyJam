package com.miniproject.heyjam;

import com.miniproject.heyjam.services.databaseServices.UserUserRelation;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UserUserAcceptRequest", value = "/UserUserAcceptRequest")
public class UserUserAcceptRequest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String participantUsername = request.getParameter("participant");
        try{
            if(UserUserRelation.userUserRelationAcceptRequest(participantUsername,username)){
                response.getWriter().write("true");
            }else{
                response.getWriter().write("false");
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
