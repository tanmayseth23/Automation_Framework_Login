package apis;

import apiEngine.DTOs.*;
import apiEngine.RestResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dataproviders.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.List;

public class ResetApi {

    public RestResponse<ResetResponse> Reset(String username)
    {
        Headers header = getHeaders();
        ResetField resetReq =ResetField.builder().email(username).build();
        ObjectMapper objectMapper=new ObjectMapper();
        Response response=null;
        try {
            String json=objectMapper.writeValueAsString(resetReq);
            System.out.println(json);
            String formatJson=objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(resetReq);
            System.out.println(formatJson);
            response= getResponse(header, formatJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new RestResponse(ResetResponse.class,response);
    }

    public RestResponse<ResetResponse> ResetWithNoHeaders(String username)
    {
        ResetField resetReq =ResetField.builder().email(username).build();
        ObjectMapper objectMapper=new ObjectMapper();
        Response response=null;
        try {
            String json=objectMapper.writeValueAsString(resetReq);
            System.out.println(json);
            String formatJson=objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(resetReq);
            System.out.println(formatJson);
            response= getResponse(new Headers(), formatJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new RestResponse(ResetResponse.class,response);
    }

    public RestResponse<ResetResponse> ResetWithMissingHeaders(String username)
    {
        Headers header = getMissingHeaders();
        ResetField resetReq =ResetField.builder().email(username).build();
        ObjectMapper objectMapper=new ObjectMapper();
        Response response=null;
        try {
            String json=objectMapper.writeValueAsString(resetReq);
            System.out.println(json);
            String formatJson=objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(resetReq);
            System.out.println(formatJson);
            response= getResponse(header, formatJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new RestResponse(ResetResponse.class,response);
    }

    private Response getResponse(Headers header, String formatJson) {
        return RestAssured.given().when().headers(header).log().all().body(formatJson)
                .basePath(ConfigReader.getInstance().getBasePath()).post(ConfigReader.getInstance().getResetUrl())
                .then().extract().response();
    }

    private Headers getHeaders() {
        Header h1 = new Header("Content-Type","application/json");
        Header h2 = new Header("x-api-key","6E9S4bmcoNnZwVLOHywOv8PJEdu76cM9");
        Header h3 = new Header("Postman-Token","dd063a04-d4fa-4ed4-aa6f-363a887f94e3");
        List<Header> headerList= new ArrayList<>();
        headerList.add(h1);
        headerList.add(h2);
        headerList.add(h3);
        Headers header= new Headers(headerList);
        return header;
    }

    private Headers getMissingHeaders() {
        Header h1 = new Header("Content-Type","application/json");
        Header h3 = new Header("Postman-Token","dd063a04-d4fa-4ed4-aa6f-363a887f94e3");
        List<Header> headerList= new ArrayList<>();
        headerList.add(h1);
        headerList.add(h3);
        Headers header= new Headers(headerList);
        return header;
    }

}
