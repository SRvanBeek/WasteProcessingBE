package services;

import lombok.extern.slf4j.Slf4j;
import nl.groep14.ipsen2BE.Models.Role;
import nl.groep14.ipsen2BE.Models.User;
import nl.groep14.ipsen2BE.repository.RoleRepository;
import nl.groep14.ipsen2BE.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Service  @Transactional @Slf4j
public class UserServiceImplement implements UserService, UserDetailsService {

    private final UserRepository gebruikerRepository;
    private final RoleRepository rolRepository;

    public UserServiceImplement(UserRepository gebruikerRepository, RoleRepository rolRepository) {
        this.gebruikerRepository = gebruikerRepository;
        this.rolRepository = rolRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = gebruikerRepository.findByUsername(username);
        if(user == null) throw new UsernameNotFoundException("Gebruiker niet gevonden");
        else log.info("Gebruiker aanwezig in database");
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
    @Override
    public User saveGebruiker(User user) {
        log.info("Slaat een nieuwe gebruiker {} op naar de database", user.getName());//Logs om te checken of de methodes werken naar behoren
        return gebruikerRepository.save(user);
    }

    @Override
    public Role saveRol(Role role) {
        log.info("Slaat een nieuwe rol  {} op naar de database", role.getName()); //Logs om te checken of de methodes werken naar behoren
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
        log.info("Haalt gebruiker {} op uit de database", username); //Logs om te checken of de methodes werken naar behoren
        return gebruikerRepository.findByUsername(username);
    }

    @Override
    public List<User> getGebruikers() {
        log.info("Haalt alle gemaakte gebruikers op uit de database"); //Logs om te checken of de methodes werken naar behoren
        return gebruikerRepository.findAll();
    }


}
