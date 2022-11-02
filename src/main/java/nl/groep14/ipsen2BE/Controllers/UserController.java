package nl.groep14.ipsen2BE.Controllers;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import nl.groep14.ipsen2BE.DAO.UserDAO;
import nl.groep14.ipsen2BE.Models.Role;
import nl.groep14.ipsen2BE.Models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nl.groep14.ipsen2BE.Services.UserService;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api" )


public class UserController {
   private final UserService userService;

   @GetMapping("/users")
    public ResponseEntity<List<User>>getUsers(){
       return ResponseEntity.ok(userService.getGebruikers());
    }

    @PostMapping("/users/save")
    public ResponseEntity<User>saveUser(@RequestBody User user){
       URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/save").toUriString());
       return ResponseEntity.created(uri).body(userService.saveGebruiker(user));
    }

    @PostMapping("/roles/save")
    public ResponseEntity<Role>saveRole(@RequestBody Role role){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
       return ResponseEntity.created(uri).body(userService.saveRol(role));
    }

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
