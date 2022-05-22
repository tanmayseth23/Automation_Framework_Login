package apiTests;
import apiEngine.DTOs.NoHeaderResponse;
import apiEngine.DTOs.ResetResponse;
import apiEngine.RestResponse;
import apis.ResetApi;
import com.fasterxml.jackson.databind.ObjectMapper;
import dataproviders.DataProvidersClass;
import org.testng.annotations.Test;
import java.io.IOException;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ResetUserTests {

    @Test(description = "User is resetting the field for the application",dataProvider = "ResetValid",dataProviderClass = DataProvidersClass.class)
    void ResetWithValidData(String username) {
        ResetApi reset= new ResetApi();
        RestResponse<ResetResponse> resetRes = reset.Reset(username);
        assertEquals(resetRes.getStatusCode(), 200);
        assertTrue(resetRes.isSuccessful());
        System.out.println(resetRes.getContent());
        ObjectMapper mapper = new ObjectMapper();
        ResetResponse res = null;
        try {
            res = mapper.readValue(resetRes.getContent(), ResetResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (res != null) {
            assertEquals(res.getMessage(), "Reset Email Sent");
            assertEquals(res.getDetail(), "Please check your inbox");
        } else {
            throw new AssertionError("Response is not correct");
        }
    }

    @Test(description = "User is resetting the field for the application with invalid data",dataProvider = "ResetInValid",dataProviderClass = DataProvidersClass.class)
    void ResetWithInValidData(String username) {
        ResetApi reset= new ResetApi();
        RestResponse<ResetResponse> resetRes = reset.Reset(username);
        System.out.println(resetRes.getContent());
        if(resetRes.getStatusCode()==404)
        {
            inValidDataResponseValidation(resetRes);

        }
        else if(resetRes.getStatusCode()==429)
        {
            System.out.println("Please try after some time ");

        }
        else {
            throw new AssertionError("some other error code is coming");
        }
    }

    @Test(description = "User is resetting the field for the application with no headers data",dataProvider = "NoHeadersReset",dataProviderClass = DataProvidersClass.class)
    void ResetWithNoHeaderData(String username) {
        ResetApi reset= new ResetApi();
        RestResponse<ResetResponse> resetRes = reset.ResetWithNoHeaders(username);
        System.out.println(resetRes.getContent());
        assertTrue(resetRes.verifyStatusCodeWithNoHeaders());
        if(resetRes.getStatusCode()==404|| resetRes.getStatusCode()==401 || resetRes.getStatusCode()==400)
        {
            noHeaderDataResponseValidation(resetRes);
        }
        else {
            throw new AssertionError("some other error code is coming");
        }
    }

    @Test(description = "User is resetting the field for the application with no headers data",dataProvider = "NoHeadersReset",dataProviderClass = DataProvidersClass.class)
    void ResetWithMissingHeaderData(String username) {
        ResetApi reset= new ResetApi();
        RestResponse<ResetResponse> resetRes = reset.ResetWithMissingHeaders(username);
        System.out.println(resetRes.getContent());
        assertTrue(resetRes.verifyStatusCodeWithNoHeaders());
        if(resetRes.getStatusCode()==404|| resetRes.getStatusCode()==401 || resetRes.getStatusCode()==400 )
        {
            noHeaderDataResponseValidation(resetRes);
        }
        else {
            throw new AssertionError("some other error code is coming");
        }
    }

    private void inValidDataResponseValidation(RestResponse<ResetResponse> loginRes) {
        ObjectMapper mapper=new ObjectMapper();
        NoHeaderResponse res= null;
        try {
            res = mapper.readValue(loginRes.getContent(), NoHeaderResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(res.getErrorType(), "Not Found");
        boolean flag=res.getErrorMessage().equals("user not found") || res.getErrorMessage().equals("error while looking for user");
        assertTrue(flag);
    }

    private void noHeaderDataResponseValidation(RestResponse<ResetResponse> resetRes) {
        ObjectMapper mapper=new ObjectMapper();
        NoHeaderResponse res= null;
        try {
            res = mapper.readValue(resetRes.getContent(), NoHeaderResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(res.getErrorType(), "Bad Request");
        assertTrue(res.getErrorMessage().equals("missing brand configuration"));
    }

}
