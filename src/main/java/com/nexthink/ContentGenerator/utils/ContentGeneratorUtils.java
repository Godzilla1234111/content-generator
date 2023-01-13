package com.nexthink.ContentGenerator.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nexthink.ContentGenerator.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ContentGeneratorUtils {
     static Permission permission1;
     static Permission permission2;
     static Permission permission3;
     static Permission permission4;
     static Permission permission5;
     static Permission permission6;
     static Permission permission7;
     static Permission permission8;
     static Permission permission9;
     static Permission permission10;
     static Permission permission11;
     static Permission permission12;
     static Permission permission13;
     static Permission permission14;
     static Permission permission15;
     static Permission permission16;
     static Permission permission17;
     static Permission permission18;
     static Permission permission19;
     static Permission permission20;
     static Permission permission21;
     static Permission permission22;
     static Permission permission23;
     static Permission permission24;
     static Permission permission25;
     static Permission permission26;
     static Permission permission27;
     static Permission permission28;
     static Permission permission29;
     static Permission permission30;
     static Permission permission31;
     static Permission permission32;

    public static String CreateUser(String email, String fullname, String password, String username, String Tenant) throws IOException {
        //auth with bearer in Create class
        Create create = new Create();

        ArrayList <Object> additionalRoles = new ArrayList <Object> ();

        //json to post to usersave
       UserPOJO userPOJO = new UserPOJO(

                 additionalRoles,
                "" + email,
                0,
                "" + fullname ,
                "" + password,
                "" + password,
                "39",
                // profileParams
                new String[]{ },
                false,
                "" + username);

        //json object mapper
        ObjectMapper objectMapper = new ObjectMapper();
        //converts string value to json format
        String jsonSTR = objectMapper.writeValueAsString(userPOJO);
        //do post request to the link uploading the converted string to json from UserPojo calss
        String response = create.post("https://"+Tenant+".dev.nexthink.cloud/nxarmproxy/ui/usersave", jsonSTR);
        //returns the response of the url and the json that has been sent
        return response + jsonSTR;
        //return null;
    }

    public  static String CreateUserWithRole (String email, String fullname, String password, String username, String Tenant, String roles) throws IOException{

        //auth with bearer in Create class
        Create create = new Create();

        // StringTokenizer st = new StringTokenizer(roles, ",");

        //  while (st.hasMoreTokens()){

        //    st.nextToken();
        // }

        StringBuilder str = new StringBuilder();
        ArrayList <Object> additionalRoles = new ArrayList <Object> ();

        additionalRoles.add(Integer.parseInt("" + roles ));
        additionalRoles.add(Integer.parseInt("" + roles ));
        additionalRoles.add(Integer.parseInt("" + roles ));


        for (Object eachstring : additionalRoles) {
            str.append(eachstring).append(",");
        }
        String commaseparatedlist = str.toString();

        if(commaseparatedlist.length() > 0){

            commaseparatedlist =
                    commaseparatedlist.substring(0,commaseparatedlist.length()-1);
        }

        //json to post to usersave
        UserPOJO userPOJO = new UserPOJO(

                additionalRoles,
                "" + email,
                0,
                "" + fullname ,
                "" + password,
                "" + password,
                "1",
                // profileParams
                new String[]{ },
                false,
                "" + username);

        //json object mapper
        ObjectMapper objectMapper = new ObjectMapper();
        //converts string value to json format
        String jsonSTR = objectMapper.writeValueAsString(userPOJO);
        //do post request to the link uploading the converted string to json from UserPojo calss
        String response = create.post("https://"+Tenant+".dev.nexthink.cloud/nxarmproxy/ui/usersave", jsonSTR);
        //returns the response of the url and the json that has been sent
        return response + jsonSTR;
        //return null;
    }

    public static String CreateProfile(String name, String description, String Tenant ) throws IOException {

        Create create = new Create();

       List<Permission> permissions = new java.util.ArrayList<>();

        permissions.add(new Permission(1089, "true"));

        permissions.add(permission1 = new Permission(1088, "true"));
        permissions.add(permission2 = new Permission(1090, "true"));
        permissions.add(permission3 = new Permission(1086, "true"));
        permissions.add(permission4 = new Permission(1085, "true"));
        permissions.add(permission5 = new Permission(1084, "true"));
        permissions.add(permission6 = new Permission(1083, "true"));
        permissions.add(permission7 = new Permission(1294, "true"));
        permissions.add(permission8 = new Permission(1293, "true"));
        permissions.add(permission9 = new Permission(1082, "true"));
        permissions.add(permission10 = new Permission(1246, "true"));
        permissions.add(permission11 = new Permission(1245, "true"));
        permissions.add(permission12 = new Permission(1081, "true"));
        permissions.add(permission13 = new Permission(1079, "true"));
        permissions.add(permission14 = new Permission(1080, "true"));
        permissions.add(permission15 = new Permission(1078, "true"));
        permissions.add(permission16 = new Permission(1077, "true"));
        permissions.add(permission17= new Permission(1069, "false"));
        permissions.add(permission18 = new Permission(1070, "false"));
        permissions.add(permission19 = new Permission(1076, "false"));
        permissions.add(permission20 = new Permission(1068, "false"));
        permissions.add(permission21 = new Permission(1074, "true"));
        permissions.add(permission22 = new Permission(1075, "false"));
        permissions.add(permission23 = new Permission(1071, "false"));
        permissions.add(permission24 = new Permission(1066, "false"));
        permissions.add(permission25 = new Permission(1065, "personal"));
        permissions.add(permission26 = new Permission(1061, "true"));
        permissions.add(permission27 = new Permission(1072, "false"));
        permissions.add(permission28 = new Permission(1063, "roles_only"));
        permissions.add(permission29 = new Permission(1067, "false"));
        permissions.add(permission30 = new Permission(1073, "normal"));
        permissions.add(permission31 = new Permission(1062, "true"));
        permissions.add(permission32 = new Permission(1064, "false"));


        ProfilePojo profilePojo = new ProfilePojo("" + name,
                "" + description, permissions,
                6, new String[]{}, Collections.singletonList(new ViewDomain(1, false, new All("Entity"))),
                "europe/zurich");

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonSTR = objectMapper.writeValueAsString(profilePojo);
        String response = create.post("https://"+Tenant+".dev.nexthink.cloud/nxarmproxy/ui/profilesave", jsonSTR);

        return response + jsonSTR;
    }

}