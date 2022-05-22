package apiTests;

import apiEngine.constants.EndPoints;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    @BeforeSuite
    void setup()
    {
        RestAssured.baseURI= EndPoints.BASE_URL;
    }
}
