package nl.groep14.ipsen2BE.DAO;

import nl.groep14.ipsen2BE.Models.Role;
import nl.groep14.ipsen2BE.Models.User;
import nl.groep14.ipsen2BE.repository.RoleRepository;
import nl.groep14.ipsen2BE.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserDAO {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserDAO(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public User saveUserToDatabase(User user){
        this.userRepository.save(user);

        return user;
    }

    public Role saveRoleToDatabase(Role role){
        this.roleRepository.save(role);

        return role;
    }
}