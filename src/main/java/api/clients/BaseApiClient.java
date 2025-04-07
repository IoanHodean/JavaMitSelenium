package api.clients;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Base API client that provides common functionality for all API clients.
 * This class handles the basic setup and configuration for REST Assured.
 */
public class BaseApiClient {
    protected static final Logger logger = LogManager.getLogger(BaseApiClient.class);
    protected RequestSpecification spec;
    protected String baseUrl;
    
    /**
     * Creates and configures a base request specification with common settings.
     * This includes setting the base URI and content type.
     * 
     * @return Configured RequestSpecification object
     */
    protected RequestSpecification getBaseRequest() {
        return RestAssured.given()
                .baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON);
    }
    
    /**
     * Performs a GET request to the specified endpoint.
     * 
     * @param endpoint The API endpoint to call
     * @return Response object containing the API response
     */
    protected Response get(String endpoint) {
        return getBaseRequest().get(endpoint);
    }
    
    /**
     * Performs a POST request to the specified endpoint with the given body.
     * 
     * @param endpoint The API endpoint to call
     * @param body The request body to send
     * @return Response object containing the API response
     */
    protected Response post(String endpoint, Object body) {
        return getBaseRequest().body(body).post(endpoint);
    }
    
    /**
     * Performs a PUT request to the specified endpoint with the given body.
     * 
     * @param endpoint The API endpoint to call
     * @param body The request body to send
     * @return Response object containing the API response
     */
    protected Response put(String endpoint, Object body) {
        return getBaseRequest().body(body).put(endpoint);
    }
    
    /**
     * Performs a DELETE request to the specified endpoint.
     * 
     * @param endpoint The API endpoint to call
     * @return Response object containing the API response
     */
    protected Response delete(String endpoint) {
        return getBaseRequest().delete(endpoint);
    }
    
    public BaseApiClient() {
        this.baseUrl = System.getProperty("api.base.url", "https://api.example.com");
        this.spec = getBaseRequest();
        
        logger.info("Initialized BaseApiClient with base URL: {}", baseUrl);
    }
    
    protected void logRequest(String method, String endpoint, Object body) {
        logger.info("API Request - Method: {}, Endpoint: {}, Body: {}", method, endpoint, body);
    }
    
    protected void logResponse(int statusCode, String responseBody) {
        logger.info("API Response - Status Code: {}, Body: {}", statusCode, responseBody);
    }
} 