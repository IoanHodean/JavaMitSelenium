package api.models;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * Represents a User entity in the system.
 * This class contains all the properties that define a user and their associated information.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    // Unique identifier for the user
    private Long id;
    
    // User's login username
    private String username;
    
    // User's email address
    private String email;
    
    // User's first name
    private String firstName;
    
    // User's last name
    private String lastName;
    
    // User's phone number
    private String phone;
    
    // User's website URL
    private String website;
    
    // User's address information
    private Address address;
    
    // User's company information
    private Company company;

    // Getters
    /**
     * Gets the user's unique identifier.
     * @return The user's ID
     */
    public Long getId() { return id; }
    
    /**
     * Gets the user's username.
     * @return The username
     */
    public String getUsername() { return username; }
    
    /**
     * Gets the user's email address.
     * @return The email address
     */
    public String getEmail() { return email; }
    
    /**
     * Gets the user's first name.
     * @return The first name
     */
    public String getFirstName() { return firstName; }
    
    /**
     * Gets the user's last name.
     * @return The last name
     */
    public String getLastName() { return lastName; }
    
    /**
     * Gets the user's phone number.
     * @return The phone number
     */
    public String getPhone() { return phone; }
    
    /**
     * Gets the user's website URL.
     * @return The website URL
     */
    public String getWebsite() { return website; }

    // Setters
    /**
     * Sets the user's unique identifier.
     * @param id The ID to set
     */
    public void setId(Long id) { this.id = id; }
    
    /**
     * Sets the user's username.
     * @param username The username to set
     */
    public void setUsername(String username) { this.username = username; }
    
    /**
     * Sets the user's email address.
     * @param email The email to set
     */
    public void setEmail(String email) { this.email = email; }
    
    /**
     * Sets the user's first name.
     * @param firstName The first name to set
     */
    public void setFirstName(String firstName) { this.firstName = firstName; }
    
    /**
     * Sets the user's last name.
     * @param lastName The last name to set
     */
    public void setLastName(String lastName) { this.lastName = lastName; }
    
    /**
     * Sets the user's phone number.
     * @param phone The phone number to set
     */
    public void setPhone(String phone) { this.phone = phone; }
    
    /**
     * Sets the user's website URL.
     * @param website The website URL to set
     */
    public void setWebsite(String website) { this.website = website; }
}

/**
 * Represents a physical address.
 * Contains all the components of a standard mailing address.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class Address {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Geo geo;
}

/**
 * Represents geographical coordinates.
 * Contains latitude and longitude information.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class Geo {
    private String lat;
    private String lng;
}

/**
 * Represents a company entity.
 * Contains basic company information.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class Company {
    private String name;
    private String catchPhrase;
    private String bs;
} 