package apiTests;

import apiEngine.DTOs.ErrorResponse;
import apiEngine.DTOs.LoginResponse;
import apiEngine.DTOs.NoHeaderResponse;
import apiEngine.RestResponse;
import apis.RegisterApi;
import com.fasterxml.jackson.databind.ObjectMapper;
import dataproviders.DataProvidersClass;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
/**
 * class RegisterUserTests
 *
 */
public class RegisterUserTests extends BaseTest  {

    @Test(description = "User is Registering to the application with valid data",dataProvider = "RegisterValid",dataProviderClass = DataProvidersClass.class,groups = { "register" })
    void RegisterWithValidData(String firstName,String lastName,String username,String gender,String password)
    {
        RegisterApi register= new RegisterApi();
        RestResponse<RegisterApi> Regres=register.registerUser(username, password, gender, firstName, lastName);
        Assert.assertEquals(200, Regres.getStatusCode());
        Assert.assertTrue(Regres.isSuccessful());
        System.out.println(Regres.getContent());
        ObjectMapper mapper = new ObjectMapper();
        LoginResponse res = null;
        try {
            res = mapper.readValue(Regres.getContent(), LoginResponse.class);
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

    @Test(description = "User is Registering to the application with Invalid Data",dataProvider = "RegisterInValidPassword",dataProviderClass = DataProvidersClass.class,groups = { "register" })
    void RegisterWithInValidData(String firstName,String lastName,String username,String gender,String password)
    {
        RegisterApi register= new RegisterApi();
        RestResponse<RegisterApi> Regres=register.registerUser(username, password, gender, firstName, lastName);
        Assert.assertEquals(400, Regres.getStatusCode());
        System.out.println(Regres.getContent());
        ObjectMapper mapper=new ObjectMapper();
        ErrorResponse res= null;
        try {
            res = mapper.readValue(Regres.getContent(), ErrorResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (res != null) {
            assertEquals(res.getType(), "Error");
        } else {
            throw new AssertionError("Response is null");
        }
    }

    @Test(description = "User is Registering to the application with no Headers",dataProvider = "NoRegHeaders",dataProviderClass = DataProvidersClass.class,groups = { "register" })
    void RegisterWithNoHeaderData(String firstName,String lastName,String username,String gender,String password)
    {
        RegisterApi register= new RegisterApi();
        RestResponse<RegisterApi> Regres=register.registerUserWithNoHeader(username, password, gender, firstName, lastName);
        System.out.println(Regres.getContent());
        Assert.assertTrue(Regres.verifyStatusCodeWithNoHeaders());
        ObjectMapper mapper = new ObjectMapper();
        NoHeaderResponse res = null;
        try {
            res = mapper.readValue(Regres.getContent(), NoHeaderResponse.class);
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

    @Test(description = "User is Registering to the application with missing Headers",dataProvider = "NoRegHeaders",dataProviderClass = DataProvidersClass.class,groups = { "register" })
    void RegisterWithMissingHeaderData(String firstName,String lastName,String username,String gender,String password)
    {
        RegisterApi register= new RegisterApi();
        RestResponse<RegisterApi> Regres=register.registerUserWithMissingHeader(username, password, gender, firstName, lastName);
        System.out.println(Regres.getContent());
        Assert.assertTrue(Regres.verifyStatusCodeWithNoHeaders());
        ObjectMapper mapper = new ObjectMapper();
        NoHeaderResponse res = null;
        try {
            res = mapper.readValue(Regres.getContent(), NoHeaderResponse.class);
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
}
