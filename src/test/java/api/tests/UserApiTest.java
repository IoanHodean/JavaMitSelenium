package api.tests;

import api.clients.UserApiClient;
import api.models.User;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test class for User API endpoints.
 * Contains test methods for CRUD operations on users.
 */
public class UserApiTest extends BaseApiTest {
    private UserApiClient userClient;
    
    /**
     * Sets up the test environment before each test method.
     * Initializes the UserApiClient instance.
     */
    @BeforeMethod
    public void setUp() {
        userClient = new UserApiClient();
    }
    
    /**
     * Tests the GET /users endpoint.
     * Verifies that:
     * 1. The response status code is 200 (OK)
     * 2. The response body is not null
     */
    @Test
    public void testGetAllUsers() {
        Response response = userClient.getAllUsers();
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertNotNull(response.getBody().asString());
    }
    
    /**
     * Tests the GET /users/{id} endpoint.
     * Verifies that:
     * 1. The response status code is 200 (OK)
     * 2. The returned user has the correct ID
     */
    @Test
    public void testGetUserById() {
        Long userId = 1L;
        Response response = userClient.getUserById(userId);
        Assert.assertEquals(response.getStatusCode(), 200);
        User user = response.as(User.class);
        Assert.assertNotNull(user);
        Assert.assertEquals(user.getId(), userId);
    }
    
    /**
     * Tests the POST /users endpoint.
     * Verifies that:
     * 1. The response status code is 201 (Created)
     * 2. The created user has the correct username
     */
    @Test
    public void testCreateUser() {
        User newUser = new User();
        newUser.setUsername("testuser");
        newUser.setEmail("test@example.com");
        newUser.setFirstName("Test");
        newUser.setLastName("User");
            
        Response response = userClient.createUser(newUser);
        Assert.assertEquals(response.getStatusCode(), 201);
        User createdUser = response.as(User.class);
        Assert.assertNotNull(createdUser.getId());
        Assert.assertEquals(createdUser.getUsername(), newUser.getUsername());
    }
    
    /**
     * Tests the PUT /users/{id} endpoint.
     * Verifies that:
     * 1. The response status code is 200 (OK)
     * 2. The updated user has the correct username
     */
    @Test
    public void testUpdateUser() {
        Long userId = 1L;
        User updatedUser = new User();
        updatedUser.setId(userId);
        updatedUser.setUsername("updateduser");
        updatedUser.setEmail("updated@example.com");
        updatedUser.setFirstName("Updated");
        updatedUser.setLastName("User");
            
        Response response = userClient.updateUser(userId, updatedUser);
        Assert.assertEquals(response.getStatusCode(), 200);
        User user = response.as(User.class);
        Assert.assertEquals(user.getUsername(), updatedUser.getUsername());
    }
    
    /**
     * Tests the DELETE /users/{id} endpoint.
     * Verifies that:
     * 1. The response status code is 200 (OK)
     */
    @Test
    public void testDeleteUser() {
        Long userId = 1L;
        Response response = userClient.deleteUser(userId);
        Assert.assertEquals(response.getStatusCode(), 200);
    }
} 