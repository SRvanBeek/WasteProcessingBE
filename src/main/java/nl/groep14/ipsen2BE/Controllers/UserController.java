package nl.groep14.ipsen2BE.Controllers;

import lombok.Data;
import lombok.RequiredArgsConstructor;
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
import java.util.List;

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

    /**@author Roy van Delft
     * gets all users from the database with the /users endpoint
     *
     * @return returns a list with all the users, along with the API response
     */
    @GetMapping("")
    public ApiResponse<List<User>> getUsers() {
        return new ApiResponse<>(HttpStatus.ACCEPTED, userService.getGebruikers());
    }

    @GetMapping("/{username}")
    public ApiResponse<User> getUserByUsername(@PathVariable String username) {
        User user = userService.getGebruiker(username);
        return new ApiResponse<>(HttpStatus.ACCEPTED, username);
    }

    /**
     * saves a user to the database
     * @param user the user to save
     * @return returnsa confirmation message, together with the API response
     */
    @PostMapping("/save")
    public ApiResponse<String> saveUser(@RequestBody User user) {
        this.userService.saveGebruiker(user);
        this.userService.addRolAanGebruiker(user.getUsername(), "ROLE_USER");
        return new ApiResponse<>(HttpStatus.ACCEPTED, "User created!");
    }

    @GetMapping("/roles/{username}")
    public ApiResponse<Collection<Role>> getRolesByUser(@PathVariable String username) {
        return new ApiResponse<>(HttpStatus.ACCEPTED, userService.getGebruiker(username).getRoles());
    }


    @PostMapping("/roles/save")
    public ApiResponse<Role> saveRole(@RequestBody Role role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return new ApiResponse<>(HttpStatus.ACCEPTED, role);
    }

    /**
     * saveAdmin saves a user to the database with the admin role.
     * @param user model of the user that needs to be saved
     * @return ApiResponse with the response of the request, together with a confirmation message
     */
    @PostMapping("/admins/save")
    public ApiResponse<String> saveAdmin(@RequestBody User user) {
        this.userService.saveGebruiker(user);
        this.userService.addRolAanGebruiker(user.getUsername(), "ROLE_ADMIN");
        return new ApiResponse<>(HttpStatus.ACCEPTED, "User created!");
    }

    @PostMapping("/checkUsername")
    public ApiResponse<Boolean> checkUsername(@RequestBody String username) {
        boolean usernameExists = this.userService.getUsernameDuplicate(username);
        return new ApiResponse<>(HttpStatus.ACCEPTED, usernameExists);
    }

    @Data
    class RoleToUserForm {
        private String username;
        private String roleName;
    }
}
