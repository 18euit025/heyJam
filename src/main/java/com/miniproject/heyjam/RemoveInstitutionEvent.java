package com.miniproject.heyjam;

import com.miniproject.heyjam.services.databaseServices.InstitutionEvent;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RemoveInstitutionEvent", value = "/RemoveInstitutionEvent")
public class RemoveInstitutionEvent extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String institutionProfileUniqueName = (String) session.getAttribute("username");
        int id = Integer.parseInt(request.getParameter("eventId"));
        try{
            if(InstitutionEvent.removeEvent(id,institutionProfileUniqueName)){
                response.getWriter().write("true");
            }else{
                response.getWriter().write("false");
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
