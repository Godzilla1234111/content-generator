package com.nexthink.ContentGenerator;

import com.nexthink.ContentGenerator.utils.ContentGeneratorUtils;
import io.micronaut.http.annotation.*;

import java.io.IOException;

@Controller("/api/Generate")
public class MainController {

    @Get("/PortalToken")
    public String profile() throws IOException {

        String token = com.nexthink.ContentGenerator.GetToken.getPortalToken();

        return token;
    }

    @Post("/CreateUser/{Tenant}/{email}/{fullname}/{password}/{username}")
    public String CreateUser(String email, String fullname, String password, String username, String Tenant ) throws IOException {

       String result =ContentGeneratorUtils.CreateUser(email, fullname, password, username, Tenant);
       return result;
    }

    @Post("/CreateUser/{Tenant}/{email}/{fullname}/{password}/{username}/{roles}")
    public String CreateUserWithRole(String email, String fullname, String password, String username, String Tenant, String roles) throws IOException {

        String result =ContentGeneratorUtils.CreateUserWithRole(email, fullname, password, username, Tenant, roles);

        return result;
    }

    @Post("/CreateProfile/{Tenant}/{name}/{description}")
    public String CreateProfileWithPermission (String name, String description, String Tenant) throws IOException {

        String result = ContentGeneratorUtils.CreateProfile(name, description, Tenant);

        return result;
    }

}