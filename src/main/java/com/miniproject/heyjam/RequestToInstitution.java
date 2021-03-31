package com.miniproject.heyjam;

import com.miniproject.heyjam.services.Components.RequestForm;
import com.miniproject.heyjam.services.databaseServices.InstitutionProfile;
import com.miniproject.heyjam.services.databaseServices.UserInstitutionRelation;
import com.miniproject.heyjam.services.databaseServices.UserProfile;
import com.miniproject.heyjam.services.otherServices.GetRequestBody;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "RequestToInstitution", value = "/RequestToInstitution")
public class RequestToInstitution extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String institutionProfileUniqueName = request.getParameter("institutionProfileUniqueName");
        try {
            UserProfile user = UserProfile.getUserProfile(username);
            InstitutionProfile profile = InstitutionProfile.getInstitutionProfile(institutionProfileUniqueName);
            if(user!=null && profile!=null) {
                RequestForm form = new RequestForm(
                        profile.getInstitutionProfile_UniqueName(),
                        profile.getInstitutionProfile_Name(),
                        profile.getInstitutionProfile_Description(),
                        user.getUserProfile_Username(),
                        user.getUserProfile_Name(),
                        user.getUserProfile_Email(),
                        user.getUserProfile_Phone(),
                        "",
                        "");
                ObjectMapper mapper = new ObjectMapper();
                String jsonString = mapper.writeValueAsString(form);
                response.getWriter().write(jsonString);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        System.out.println("hello post servlet");
        String requestBody = GetRequestBody.getBody(request);
        JSONObject user = null;
        try {
            user = new JSONObject(requestBody);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("after conversion "+requestBody);
        try {
            String userProfileUsername = user.getString("userProfileUsername");
            String institutionProfileUniqueName = user.getString("institutionProfileUniqueName");
            String userInstitutionRelation_JoiningYear = user.getString("userInstitutionRelation_JoiningYear");
            String userInstitutionRelation_Department = user.getString("userInstitutionRelation_Department");
            try {
                System.out.println("before update");
                boolean requestStatus = UserInstitutionRelation.userInstitutionRelationCreateRequest(
                        userProfileUsername,
                        institutionProfileUniqueName,
                        userInstitutionRelation_JoiningYear,
                        userInstitutionRelation_Department
                );
                System.out.println("after update "+requestStatus);
                if(requestStatus){
                    response.getWriter().write("{\"requestStatus\":true}");
                }else{
                    response.getWriter().write("{\"requestStatus\":false}");
                }
            }catch(Exception e){
                System.out.println(e);
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
