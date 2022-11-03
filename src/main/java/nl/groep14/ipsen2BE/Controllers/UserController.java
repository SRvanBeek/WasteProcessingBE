package nl.groep14.ipsen2BE.Controllers;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import nl.groep14.ipsen2BE.DAO.UserDAO;
import nl.groep14.ipsen2BE.Models.ApiResponse;
import nl.groep14.ipsen2BE.Models.Role;
import nl.groep14.ipsen2BE.Models.User;
import nl.groep14.ipsen2BE.repository.RoleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nl.groep14.ipsen2BE.Services.UserService;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api" )


public class UserController {
   private final UserService userService;
   private final RoleRepository roleRepository;

   @GetMapping("/users")
    public ResponseEntity<List<User>>getUsers(){
       return ResponseEntity.ok(userService.getGebruikers());
    }

    @PostMapping("/users/save")
    public ApiResponse saveUser(@RequestBody User user){
       this.userService.saveGebruiker(user);
       this.userService.addRolAanGebruiker(user.getUsername(),"ROLE_USER");
       return new ApiResponse<>(HttpStatus.ACCEPTED, "User created!");
    }

    @PostMapping("/admin/save")
    public ApiResponse saveAdmin(@RequestBody User user){
        this.userService.saveGebruiker(user);
        this.userService.addRolAanGebruiker(user.getUsername(),"ROLE_ADMIN");
        return new ApiResponse<>(HttpStatus.ACCEPTED, "User created!");
    }

    @PostMapping("/checkUsername")
    public boolean checkUsername(@RequestBody String username){
        return this.userService.getUsernameDuplicate(username);
    }



}

@Data
class RoleToUserForm{
    private String username;
    private String roleName;
}
