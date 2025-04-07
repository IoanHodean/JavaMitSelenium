package api.tests;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseApiTest {
    protected static final Logger logger = LogManager.getLogger(BaseApiTest.class);
    protected RequestSpecification requestSpec;
    protected ResponseSpecification responseSpec;
    
    @BeforeClass
    public void setup() {
        String baseUrl = System.getProperty("api.base.url", "https://api.example.com");
        
        // Request specification
        requestSpec = new RequestSpecBuilder()
            .setBaseUri(baseUrl)
            .setContentType("application/json")
            .build();
            
        // Response specification
        responseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .build();
            
        RestAssured.requestSpecification = requestSpec;
        RestAssured.responseSpecification = responseSpec;
        
        logger.info("API Test setup completed with base URL: {}", baseUrl);
    }
    
    @AfterClass
    public void tearDown() {
        RestAssured.reset();
        logger.info("API Test teardown completed");
    }
} 