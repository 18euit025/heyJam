package com.miniproject.heyjam;

import com.miniproject.heyjam.services.databaseServices.InstitutionProfile;
import com.miniproject.heyjam.services.otherServices.GetRequestBody;
import com.miniproject.heyjam.services.otherServices.SendMail;
import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.UUID;

@WebServlet(name = "SignUpInstitutionProfile", value = "/SignUpInstitutionProfile")
public class SignUpInstitutionProfile extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
            String requestBody = GetRequestBody.getBody(request);
            JSONObject user = new JSONObject(requestBody);
            System.out.println(user);
            String uniqueName = user.getString("uniqueName");
            String name = user.getString("name");
            String description = user.getString("description");
            String email = user.getString("email");
            String password = user.getString("password");
            String phone = user.getString("phone");
            String uuid = String.valueOf(UUID.randomUUID());
            System.out.println(uniqueName);
            boolean sendMailStatus = SendMail.prepareMail(email,uuid);
            if(sendMailStatus){
                System.out.println(sendMailStatus);
                try {
                    InstitutionProfile profile = InstitutionProfile.createInstitutionProfile(
                            uniqueName,
                            name,
                            description,
                            email,
                            password,
                            phone,
                            0,
                            uuid,
                            String.valueOf(new java.sql.Date(System.currentTimeMillis()))
                    );
                    if(profile != null){
                        System.out.println(profile.toString());
                        response.getWriter().write("{\"creation\":true}");
                    }else{
                        System.out.println("empty");
                        response.getWriter().write("{\"creation\":false}");
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            }else{
                response.getWriter().write("{\"creation\":false}");
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
