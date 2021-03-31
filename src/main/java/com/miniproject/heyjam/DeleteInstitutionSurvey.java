package com.miniproject.heyjam;

import com.miniproject.heyjam.services.databaseServices.InstitutionEvent;
import com.miniproject.heyjam.services.databaseServices.InstitutionSurvey;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DeleteInstitutionSurvey", value = "/DeleteInstitutionSurvey")
public class DeleteInstitutionSurvey extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String institutionProfileUniqueName = (String) session.getAttribute("username");
        int surveyId = Integer.parseInt(request.getParameter("surveyId"));
        try{
            if(InstitutionSurvey.removeSurvey(surveyId,institutionProfileUniqueName)){
                response.getWriter().write("true");
            }else{
                response.getWriter().write("false");
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
