package nl.groep14.ipsen2BE.Controllers;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import nl.groep14.ipsen2BE.Models.Role;
import nl.groep14.ipsen2BE.Models.User;

import nl.groep14.ipsen2BE.Services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import nl.groep14.ipsen2BE.Services.UserServiceImplement;
import nl.groep14.ipsen2BE.Models.ApiResponse;
import nl.groep14.ipsen2BE.repository.RoleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
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
@RequestMapping("/api" )

public class UserController {
    private final UserServiceImplement userService;
    private final RoleRepository roleRepository;

    /**@author Roy van Delft
     * gets all users from the database with the /users endpoint
     *
     * @return returns all users
     */
    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.getGebruikers());
    }

    @GetMapping("/users/{username}")
    public User getUserByUsername(@PathVariable String username) {
        User user = userService.getGebruiker(username);
        return user;
    }

    /**
     * saves a user to the database
     * @param user the user to save
     * @return returns the user
     */
    @PostMapping("/users/save")
    public ApiResponse saveUser(@RequestBody User user) {
        this.userService.saveGebruiker(user);
        this.userService.addRolAanGebruiker(user.getUsername(), "ROLE_USER");
        return new ApiResponse<>(HttpStatus.ACCEPTED, "User created!");
    }

    /**
     * saves a role to the database
     * @param role the role to save
     * @return the saved role
     */
    @PostMapping("/roles/save")
    public ResponseEntity<Role>saveRole(@RequestBody Role role){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
       return ResponseEntity.created(uri).body(userService.saveRol(role));
    }

    /**
     * adds a role to a user
     * @param form the form to add the role to
     * @return the user with the role
     */
    @PostMapping("/role/addToUser")
    public ResponseEntity<?>addRoleToUser(@RequestBody RoleToUserForm form){
       userService.addRolAanGebruiker(form.getUsername(), form.getRoleName());

    @GetMapping("/users/roles/{username}")
    public Collection<Role> getRolesByUser(@PathVariable String username) {
        Collection<Role> role  = userService.getGebruiker(username).getRoles();
        for (Role role1: role){
            System.out.println(role1.getName());
        }
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
    @PostMapping("/admin/save")
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
