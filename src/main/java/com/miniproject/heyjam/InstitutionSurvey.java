package com.miniproject.heyjam;

import com.miniproject.heyjam.services.Components.InstitutionSurveyRow;
import com.miniproject.heyjam.services.databaseServices.InstitutionEvent;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "InstitutionSurvey", value = "/InstitutionSurvey")
public class InstitutionSurvey extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String institutionProfileUniqueName = (String) session.getAttribute("username");
        try{
            ArrayList<InstitutionSurveyRow> surveys = InstitutionSurveyRow.getSurveys(institutionProfileUniqueName);
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(surveys);
            response.getWriter().write(jsonString);
        }catch(Exception e){
            System.out.println(e);
        }
    }
}

