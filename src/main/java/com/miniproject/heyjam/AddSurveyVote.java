package com.miniproject.heyjam;

import com.miniproject.heyjam.services.databaseServices.InstitutionSurveyVotes;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AddSurveyVote", value = "/AddSurveyVote")
public class AddSurveyVote extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String id = request.getParameter("id");
        String option = request.getParameter("option");
        System.out.println(id+" hey "+option);
        try {
            InstitutionSurveyVotes.voteSurvey(Integer.parseInt(id),username,option);
            response.getWriter().write("true");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
