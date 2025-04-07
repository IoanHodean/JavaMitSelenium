package api.clients;

import api.models.User;
import io.restassured.response.Response;

/**
 * Client for interacting with the User API endpoints.
 * Extends BaseApiClient to inherit common HTTP request functionality.
 */
public class UserApiClient extends BaseApiClient {
    
    // API endpoint paths
    private static final String USERS_ENDPOINT = "/users";
    private static final String USER_BY_ID_ENDPOINT = "/users/{id}";
    
    /**
     * Retrieves all users from the API.
     * 
     * @return Response containing the list of all users
     */
    public Response getAllUsers() {
        logger.info("Fetching all users");
        return get(USERS_ENDPOINT);
    }
    
    /**
     * Retrieves a specific user by their ID.
     * 
     * @param userId The ID of the user to retrieve
     * @return Response containing the user details
     */
    public Response getUserById(Long userId) {
        logger.info("Fetching user with ID: {}", userId);
        return get(USER_BY_ID_ENDPOINT.replace("{id}", userId.toString()));
    }
    
    /**
     * Creates a new user.
     * 
     * @param user The user object containing the details to create
     * @return Response containing the created user details
     */
    public Response createUser(User user) {
        logger.info("Creating new user: {}", user.getUsername());
        return post(USERS_ENDPOINT, user);
    }
    
    /**
     * Updates an existing user.
     * 
     * @param userId The ID of the user to update
     * @param user The updated user details
     * @return Response containing the updated user details
     */
    public Response updateUser(Long userId, User user) {
        logger.info("Updating user with ID: {}", userId);
        return put(USER_BY_ID_ENDPOINT.replace("{id}", userId.toString()), user);
    }
    
    /**
     * Deletes a user.
     * 
     * @param userId The ID of the user to delete
     * @return Response indicating the result of the deletion
     */
    public Response deleteUser(Long userId) {
        logger.info("Deleting user with ID: {}", userId);
        return delete(USER_BY_ID_ENDPOINT.replace("{id}", userId.toString()));
    }
} 