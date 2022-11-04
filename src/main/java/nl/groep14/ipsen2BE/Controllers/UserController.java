package nl.groep14.ipsen2BE.Controllers;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import nl.groep14.ipsen2BE.Models.Role;
import nl.groep14.ipsen2BE.Models.User;
import nl.groep14.ipsen2BE.Services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api" )


public class UserController {
   private final UserService userService;

    /**@author Roy van Delft
     * gets all users from the database with the /users endpoint
     *
     * @return returns all users
     */
   @GetMapping("/users")
    public ResponseEntity<List<User>>getUsers(){
       return ResponseEntity.ok(userService.getGebruikers());
    }

    /**
     * saves a user to the database
     * @param user the user to save
     * @return returns the user
     */
    @PostMapping("/users/save")
    public ResponseEntity<User>saveUser(@RequestBody User user){
       URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/save").toUriString());
       return ResponseEntity.created(uri).body(userService.saveGebruiker(user));
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

       return ResponseEntity.ok().build();
    }


}

@Data
class RoleToUserForm{
    private String username;
    private String roleName;
}
