package nl.groep14.ipsen2BE.Controllers;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import nl.groep14.ipsen2BE.DAO.UserDAO;
import nl.groep14.ipsen2BE.Exceptions.NotFoundException;
import nl.groep14.ipsen2BE.Models.Role;
import nl.groep14.ipsen2BE.Models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import nl.groep14.ipsen2BE.Services.UserServiceImplement;
import nl.groep14.ipsen2BE.Models.ApiResponse;
import nl.groep14.ipsen2BE.repository.RoleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
     * putUser puts a User into the database
     * @param user to be put
     * @return ApiResponse with a string
     */
    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ResponseBody
    public ApiResponse putUser(@RequestBody User user){
        this.userService.saveGebruiker(user);
        this.userService.addRolAanGebruiker(user.getUsername(), "ROLE_USER");
        return new ApiResponse<>(HttpStatus.ACCEPTED, "User password changed!");
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
     * gets the user by the username
     * @param username an string wih the username
     * @return a string with the user in it
     */
    @GetMapping("/{username}")
    public User getUserByUsername(@PathVariable String username) {
        User user = userService.getGebruiker(username);
        return user;
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

    @GetMapping("/roles/{username}")
    public Collection<Role> getRolesByUser(@PathVariable String username) {
        return userService.getGebruiker(username).getRoles();
    }


    @PostMapping("/roles/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRol(role));
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
