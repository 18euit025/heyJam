package com.miniproject.heyjam;

import com.miniproject.heyjam.services.databaseServices.UserProfile;
import com.miniproject.heyjam.services.otherServices.GetRequestBody;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


@WebServlet(name = "SignUpUserProfile", value = "/SignUpUserProfile")
public class SignUpUserProfile extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
        response.setContentType("text/html");
        String requestBody = GetRequestBody.getBody(request);
        JSONObject user = new JSONObject(requestBody);
        String name = user.getString("name");
        String username = user.getString("username");
        String email = user.getString("email");
        String mobile = user.getString("mobile");
        String date = user.getString("date");
        String gender = user.getString("gender");
        String password = user.getString("password");

            UserProfile userProfile = UserProfile.createUserProfile(username,name,email,date,gender,mobile,password);
            if(userProfile!=null){
                response.getWriter().write("{\"creation\":true}");
            }else{
                response.getWriter().write("{\"creation\":false");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}