package nl.groep14.ipsen2BE.Controllers;

import lombok.RequiredArgsConstructor;
import nl.groep14.ipsen2BE.Models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import services.UserService;

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
}
