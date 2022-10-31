package nl.groep14.ipsen2BE.Services;

import nl.groep14.ipsen2BE.Models.Role;
import nl.groep14.ipsen2BE.Models.User;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserService {
    User saveGebruiker(User user);
    Role saveRol(Role role);
    void addRolAanGebruiker(String username, String roleName);
    User getGebruiker(String username);
    List<User> getGebruikers();
}
