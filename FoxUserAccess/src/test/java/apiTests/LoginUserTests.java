package apiTests;

import apiEngine.DTOs.ErrorResponse;
import apiEngine.DTOs.LoginResponse;
import apiEngine.DTOs.NoHeaderResponse;
import apiEngine.RestResponse;
import apis.LoginApi;
import com.fasterxml.jackson.databind.ObjectMapper;
import dataproviders.DataProvidersClass;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.*;

public class LoginUserTests extends BaseTest{

    @Test(description = "User is logging in to the application with valid credentials",dataProvider = "RegisterValid",dataProviderClass = DataProvidersClass.class,dependsOnGroups = { "register" },groups = { "login" })
    void LoginWithValidData(String firstName,String lastName,String username,String gender,String password) {
        LoginApi login = new LoginApi();
        RestResponse<LoginResponse> loginRes = login.loginUser(username, password);
        assertEquals(loginRes.getStatusCode(), 200);
        assertTrue(loginRes.isSuccessful());
        System.out.println(loginRes.getContent());
        ObjectMapper mapper = new ObjectMapper();
        LoginResponse res = null;
        try {
            res = mapper.readValue(loginRes.getContent(), LoginResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (res != null) {
            assertTrue(res.getHasEmail());
            assertEquals(res.getUserType(), "email");
            assertEquals(res.getEmail(), username);
        } else {
            throw new AssertionError("Response is null");
        }
    }

    @Test(description = "User is logging in to the application with invalid credentials",dataProvider = "LoginInValid",dataProviderClass = DataProvidersClass.class,groups = { "login" })
    void LoginWithInValidData(String username,String password) {
        LoginApi login= new LoginApi();
        RestResponse<LoginResponse> loginRes=login.loginUser(username, password);
        System.out.println(loginRes.getContent());
        if(loginRes.getStatusCode()==401)
        {
            inValidDataResponseValidation(loginRes);

        }
        else if(loginRes.getStatusCode()==429)
        {
            System.out.println("Please try after some time ");

        }
        else {
            throw new AssertionError("some other error code is coming");
        }
    }

    @Test(description = "User is logging in to the application with valid credentials but with no headers",dataProvider = "NoHeaders",dataProviderClass = DataProvidersClass.class,groups = { "login" })
    void LoginWithNoHeaders(String username,String password)
    {
        LoginApi login= new LoginApi();
        RestResponse<LoginResponse> loginRes=login.loginUserWithInvalidHeaders(username, password);
        System.out.println(loginRes.getContent());
        Assert.assertTrue(loginRes.verifyStatusCodeWithNoHeaders());
        ObjectMapper mapper=new ObjectMapper();
        NoHeaderResponse res= null;
        try {
            res = mapper.readValue(loginRes.getContent(), NoHeaderResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(res!=null)
        {
            assertEquals(res.getErrorMessage(), "InvalidKey");
            assertEquals(res.getErrorType(),"Unauthorized");
        }
        else throw new AssertionError("some other error code is coming");
    }

    @Test(description = "User is logging in to the application with valid credentials but with some missing headers",dataProvider = "NoHeaders",dataProviderClass = DataProvidersClass.class,groups = { "login" })
    void LoginWithMissingHeaders(String username,String password)
    {
        LoginApi login= new LoginApi();
        RestResponse<LoginResponse> loginRes=login.loginUserWithMissingHeaders(username, password);
        System.out.println(loginRes.getContent());
        Assert.assertTrue(loginRes.verifyStatusCodeWithNoHeaders());
        ObjectMapper mapper=new ObjectMapper();
        NoHeaderResponse res= null;
        try {
            res = mapper.readValue(loginRes.getContent(), NoHeaderResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(res!=null)
        {
            assertEquals(res.getErrorMessage(), "InvalidKey");
            assertEquals(res.getErrorType(),"Unauthorized");
        }
        else throw new AssertionError("some other error code is coming");
    }

    private void inValidDataResponseValidation(RestResponse<LoginResponse> loginRes) {
        assertEquals(loginRes.getStatusCode(),401);
        ObjectMapper mapper=new ObjectMapper();
        ErrorResponse res= null;
        try {
            res = mapper.readValue(loginRes.getContent(), ErrorResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(res.getMessage(), "Invalid LoginId");
        assertEquals("Invalid login credentials",res.getDetail());
        assertEquals(401,res.getErrorCode());
    }

}
