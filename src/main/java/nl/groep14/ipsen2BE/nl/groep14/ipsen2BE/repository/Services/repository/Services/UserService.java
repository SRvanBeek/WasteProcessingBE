package nl.groep14.ipsen2BE.nl.groep14.ipsen2BE.repository.Services.repository.Services;

import nl.groep14.ipsen2BE.Models.Role;
import nl.groep14.ipsen2BE.Models.User;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserService {
    /**@author Roy van Delft
     * saves an user
     * @param user takes the user to save
     * @return returns the saved user
     */
    User saveGebruiker(User user);

    /**
     * saves a role
     * @param role takes the role to save
     * @return returns the saved role
     */
    Role saveRol(Role role);

    /**
     * adds a role to an user
     * @param username takes the username
     * @param roleName takes the rolename
     */
    void addRolAanGebruiker(String username, String roleName);

    /**
     * gets a single user from the database
     * @param username the user to find
     * @return the found user
     */
    User getGebruiker(String username);

    /**
     * gets all users from the database, and puts them in a list
     * @return the list with all users
     */
    List<User> getGebruikers();
}
