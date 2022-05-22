package apis;

import apiEngine.DTOs.Register;
import apiEngine.RestResponse;
import dataproviders.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;

public class RegisterApi {

    public RestResponse<RegisterApi> registerUser(String username,String password,String gender,String firstName,String lastName)
    {
        Headers header = getHeaders();
        Register register = Register.builder().email(username).password(password)
                .gender(gender).firstName(firstName)
                .lastName(lastName).build();
        ObjectMapper objectMapper=new ObjectMapper();
        Response response=null;
        try {
            String json=objectMapper.writeValueAsString(register);
            System.out.println(json);
            String formatJson=objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(register);
            System.out.println(formatJson);
        response= getResponse(header, formatJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new RestResponse(Register.class,response);
    }

    public RestResponse<RegisterApi> registerUserWithMissingHeader(String username,String password,String gender,String firstName,String lastName)
    {
        Headers header = getMissingHeaders();
        Register register = Register.builder().email(username).password(password)
                .gender(gender).firstName(firstName)
                .lastName(lastName).build();
        ObjectMapper objectMapper=new ObjectMapper();
        Response response=null;
        try {
            String json=objectMapper.writeValueAsString(register);
            System.out.println(json);
            String formatJson=objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(register);
            System.out.println(formatJson);
            response= getResponse(header, formatJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new RestResponse(Register.class,response);
    }

    public RestResponse<RegisterApi> registerUserWithNoHeader(String username,String password,String gender,String firstName,String lastName)
    {
        Register register = Register.builder().email(username).password(password)
                .gender(gender).firstName(firstName)
                .lastName(lastName).build();
        ObjectMapper objectMapper=new ObjectMapper();
        Response response=null;
        try {
            String json=objectMapper.writeValueAsString(register);
            System.out.println(json);
            String formatJson=objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(register);
            System.out.println(formatJson);
            response= getResponse(new Headers(), formatJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new RestResponse(Register.class,response);
    }

    private Response getResponse(Headers header, String formatJson) {
        return RestAssured.given().when().headers(header).log().all().body(formatJson)
                .basePath(ConfigReader.getInstance().getBasePath()).post(ConfigReader.getInstance().getRegisterUrl())
                .then().log().all().extract().response();
    }

    private Headers getHeaders() {
        Header h1 = new Header("Content-Type","application/json");
        Header h2 = new Header("x-api-key","DEFAULT");
        Header h3 = new Header("Postman-Token","a74249b3-97f1-45c0-999c-66d7841bed8a");
        List<Header> headerList= new ArrayList<>();
        headerList.add(h1);
        headerList.add(h2);
        headerList.add(h3);
        Headers header= new Headers(headerList);
        return header;
    }

    private Headers getMissingHeaders() {
        Header h1 = new Header("Content-Type","application/json");
        List<Header> headerList= new ArrayList<>();
        headerList.add(h1);
        Headers header= new Headers(headerList);
        return header;
    }
}
