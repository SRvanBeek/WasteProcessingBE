package nl.groep14.ipsen2BE.Services;

import lombok.extern.slf4j.Slf4j;

import nl.groep14.ipsen2BE.DAO.UserDAO;
import nl.groep14.ipsen2BE.Models.Role;
import nl.groep14.ipsen2BE.Models.User;
import nl.groep14.ipsen2BE.repository.RoleRepository;
import nl.groep14.ipsen2BE.repository.UserRepository;
import org.springframework.context.annotation.Bean;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Service  @Transactional @Slf4j
public class UserServiceImplement implements UserService, UserDetailsService {

    private final UserRepository gebruikerRepository;
    private final RoleRepository rolRepository;

    private final UserDAO userDAO;

    /**@author Roy van Delft, Stijn van Beek
     * Constructor
     * @return new passwordencoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Constructor
     * @param gebruikerRepository takes the user repository
     * @param rolRepository takes the rolerepository
     * @param userDAO takes the userDAO
     */
    public UserServiceImplement(UserRepository gebruikerRepository, RoleRepository rolRepository, UserDAO userDAO) {
        this.gebruikerRepository = gebruikerRepository;
        this.rolRepository = rolRepository;

        this.userDAO = userDAO;

    }

    /**
     * checks if the user exists in the databases, and grants authorities to the user
     * @param username takes the username
     * @return rteurns the user, with the details
     * @throws UsernameNotFoundException when the user is not found
     */
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

    /**
     * saves an user
      * @param user takes the user to save
     * @return returns the saved user
     */
    @Override
    public User saveGebruiker(User user) {
        log.info("Slaat een nieuwe gebruiker {} op naar de database", user.getName());//Logs om te checken of de methodes werken naar behoren

        user.setPassword(passwordEncoder().encode(user.getPassword()));
        return userDAO.saveUserToDatabase(user);

    }

    /**
     * saves a role
     * @param role takes the role to save
     * @return returns the saved role
     */
    @Override
    public Role saveRol(Role role) {
        log.info("Slaat een nieuwe rol  {} op naar de database", role.getName()); //Logs om te checken of de methodes werken naar behoren
        return userDAO.saveRoleToDatabase(role);

    }

    /**
     * adds a role to an user
     * @param username takes the username
     * @param roleName takes the rolename
     */
    @Override
    //Voegt rol toe aan een gebruiker
    public void addRolAanGebruiker(String username, String roleName) {
        User user = gebruikerRepository.findByUsername(username);
        Role role = rolRepository.findByName(roleName);
        user.getRoles().add(role);


    }

    /**
     * Finds a single user
     * @param username takes the name to find
     * @return returns the user
     */
    @Override
    public User getGebruiker(String username) {
        log.info("Haalt gebruiker {} op uit de database", username); //Logs om te checken of de methodes werken naar behoren
        return gebruikerRepository.findByUsername(username);
    }

    /**
     * Finds all users
     * @return returns a list of users
     */
    @Override
    public List<User> getGebruikers() {
        log.info("Haalt alle gemaakte gebruikers op uit de database"); //Logs om te checken of de methodes werken naar behoren
        return gebruikerRepository.findAll();
    }


}
