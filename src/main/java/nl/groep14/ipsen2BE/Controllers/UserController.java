package nl.groep14.ipsen2BE.Controllers;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import nl.groep14.ipsen2BE.DAO.UserDAO;
import nl.groep14.ipsen2BE.Exceptions.NotFoundException;
import nl.groep14.ipsen2BE.Models.ApiResponse;
import nl.groep14.ipsen2BE.Models.Role;
import nl.groep14.ipsen2BE.Models.User;
import nl.groep14.ipsen2BE.Services.UserServiceImplement;
import nl.groep14.ipsen2BE.repository.RoleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;

/**
 * UserController is the controller for the User entity.
 * @author Dino Yang, Roy van Delft, Stijn van Beek
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users" )
public class UserController {
    private final UserServiceImplement userService;
    private final RoleRepository roleRepository;
    private final UserDAO userDAO;

    /**@author Roy van Delft
     * gets all users from the database with the /users endpoint
     *
     * @return returns all users
     */
    @GetMapping("")
    public ApiResponse getAllUsers() {
        return new ApiResponse<>(HttpStatus.ACCEPTED, this.userService.getGebruikers());
    }

    /**
     * Changes the password of a specific user
     * @param user takes the User object from the request body
     * @return the ApiResponse with a confirmation message
     */
    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ResponseBody
    public ApiResponse putUser(@RequestBody User user){
        this.userService.updateUser(user);
        return new ApiResponse<>(HttpStatus.ACCEPTED, "User information changed!");
    }

    /**
     * getemployeeNameById gets the Employee with the given UserId
     * @param id is the given UserID
     * @return an ApiResponse with in the payload the given employee name otherwise return a 404 error
     */
    @RequestMapping(value = "byId/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse<String> getEmployeeNameById(@PathVariable long id){
        try {
            return new ApiResponse<>(HttpStatus.ACCEPTED, this.userDAO.getUserNameById(id));
        }
        catch (NotFoundException e) {
            return new ApiResponse<>(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    /**
     * Finds an user by a given username
     * @param username the username to filter on
     * @return an API response, together with the found user
     */
    @GetMapping("/{username}")
    public ApiResponse<User> getUserByUsername(@PathVariable String username) {
        return new ApiResponse<>(HttpStatus.ACCEPTED, this.userService.getGebruiker(username));
    }

    /**
     * saves a user to the database
     * @param user the user to save
     * @return returns the user
     */
    @PostMapping("/save")
    public ApiResponse saveUser(@RequestBody User user) {
        this.userService.saveGebruiker(user);
        this.userService.addRolAanGebruiker(user.getUsername(), "ROLE_USER");
        return new ApiResponse<>(HttpStatus.ACCEPTED, "User created!");
    }

    /**
     * Finds all roles for a given user
     * @param username the username to find the roles by
     * @return an API response, together with the found roles
     */
    @GetMapping("/roles/{username}")
    public ApiResponse<Collection<Role>> getRolesByUser(@PathVariable String username) {
        return new ApiResponse<>(HttpStatus.ACCEPTED, userService.getGebruiker(username).getRoles());
    }

    /**
     * saves a role to the database
     * @param role the role to save
     * @return an API response, together with the role that was saved
     */
    @PostMapping("/roles/save")
    public ApiResponse<Role> saveRole(@RequestBody Role role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return new ApiResponse<>(HttpStatus.ACCEPTED, userService.saveRol(role));
    }

    /**
     * saveAdmin saves a user to the database with the admin role.
     * @param user model of the user that needs to be saved
     * @return ApiResponse with the response of the request
     */
    @PostMapping("/admins/save")
    public ApiResponse saveAdmin(@RequestBody User user) {
        this.userService.saveGebruiker(user);
        this.userService.addRolAanGebruiker(user.getUsername(), "ROLE_ADMIN");
        return new ApiResponse<>(HttpStatus.ACCEPTED, "User created!");
    }

    /**
     * Checks if the username already exists
     * @param username the username to check
     * @return the duplicate username
     */
    @PostMapping("/checkUsername")
    public boolean checkUsername(@RequestBody String username) {
        return this.userService.getUsernameDuplicate(username);
    }

    @Data
    class RoleToUserForm {
        private String username;
        private String roleName;
    }
}
