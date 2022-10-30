package services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.groep14.ipsen2BE.Models.Role;
import nl.groep14.ipsen2BE.Models.User;
import nl.groep14.ipsen2BE.repository.RoleRepository;
import nl.groep14.ipsen2BE.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImplement implements UserService{

    private final UserRepository gebruikerRepository;
    private final RoleRepository rolRepository;
    @Override
    public User saveGebruiker(User user) {
        return gebruikerRepository.save(user);
    }

    @Override
    public Role saveRol(Role role) {
        return rolRepository.save(role);
    }

    @Override
    //Voegt rol toe aan een gebruiker
    public void addRolAanGebruiker(String username, String roleName) {
        User user = gebruikerRepository.findByUsername(username);
        Role role = rolRepository.findByName(roleName);
        user.getRoles().add(role);


    }

    @Override
    public User getGebruiker(String username) {
        return gebruikerRepository.findByUsername(username);
    }

    @Override
    public List<User> getGebruikers() {
        return gebruikerRepository.findAll();
    }
}
