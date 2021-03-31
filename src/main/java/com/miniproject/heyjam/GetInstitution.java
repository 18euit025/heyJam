package com.miniproject.heyjam;

import com.miniproject.heyjam.services.Components.InstitutionRow;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "GetInstitution", value = "/GetInstitution")
public class GetInstitution extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Servlet Entered");
        String name = request.getParameter("name");
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        System.out.println(username+" "+name);
        try {
            InstitutionRow element = InstitutionRow.findInstitution(name,username);
            if(element!=null) {
                ObjectMapper mapper = new ObjectMapper();
                String jsonString = mapper.writeValueAsString(element);
                String send = "{\"flag\":true,\"element\":"+jsonString+"}";
                response.getWriter().write(send);
            }else{
                String send = "{\"flag\":false,\"element\":{}}";
                response.getWriter().write(send);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
