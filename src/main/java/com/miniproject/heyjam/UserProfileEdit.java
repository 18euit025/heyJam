package com.miniproject.heyjam;

import com.miniproject.heyjam.services.Components.UserProfileDetailStatus;
import com.miniproject.heyjam.services.databaseServices.UserProfile;
import com.miniproject.heyjam.services.otherServices.DatabaseConnector;
import com.miniproject.heyjam.services.otherServices.GetRequestBody;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "UserProfileEdit", value = "/UserProfileEdit")
public class UserProfileEdit extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        try{
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(UserProfile.getUserProfile(username));
            response.getWriter().write(jsonString);
        }catch(Exception e){
            System.out.println(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
        response.setContentType("text/html");
        String requestBody = GetRequestBody.getBody(request);
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        JSONObject phone = new JSONObject(requestBody);
        String phoneNumber = phone.getString("phone");

            Connection con = DatabaseConnector.getConnection();
            String query = "UPDATE userprofile SET userProfile_Phone=? WHERE userProfile_Username=?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1,phoneNumber);
            stmt.setString(2,username);
            int n = stmt.executeUpdate();
            if(n==1){
                response.getWriter().write("{\"update\":true}");
            }else{
                response.getWriter().write("{\"update\":false}");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
