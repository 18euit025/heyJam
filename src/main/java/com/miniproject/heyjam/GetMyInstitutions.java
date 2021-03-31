package com.miniproject.heyjam;

import com.miniproject.heyjam.services.Components.InstitutionRow;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "GetMyInstitutions", value = "/GetMyInstitutions")
public class GetMyInstitutions extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        try {
            ArrayList<InstitutionRow> myInstitutions = InstitutionRow.getAllMyInstitutions(username);
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(myInstitutions);
            response.getWriter().write(jsonString);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
