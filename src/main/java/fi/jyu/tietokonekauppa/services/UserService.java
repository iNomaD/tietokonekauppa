package fi.jyu.tietokonekauppa.services;

import fi.jyu.tietokonekauppa.models.User;
import fi.jyu.tietokonekauppa.repositories.UserRepository;
import fi.jyu.tietokonekauppa.web.exceptions.DataExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAll() {
        List<User> result = new ArrayList<>();
        userRepository.findAll().forEach(result::add);
        return result;
    }

    public User get(long id) {
        return userRepository.findOne(id);
    }

    public boolean isUserExist(User item) {
        return userRepository.exists(item.getId());
    }

    public User add(User item) {
        item.setId(new Long(0));
        return userRepository.save(item);
    }

    public User update(User item) {
        return userRepository.save(item);
    }

    public void remove(long id) {
        userRepository.delete(id);
    }

    public boolean userCredentialExists(String login, String password) {
        User user = getUser(login);
        if(user != null && user.getPassword().equals(password)){
            return true;
        }else{
            return false;
        }
    }

    public User getUser(String login) {
        List<User> all = getAll();
        for(User item : all){
            if(item.getLogin() != null && item.getLogin().equalsIgnoreCase(login)){
                return item;
            }
        }
        return null;
    }

    public User getUserByEmail(String email) {
        List<User> all = getAll();
        for(User item : all){
            if(item.getEmail() != null && item.getEmail().equalsIgnoreCase(email)){
                return item;
            }
        }
        return null;
    }


    public User signUp(String login, String password, String firstName, String lastName, String email) throws DataExistsException{
        return register(login, password, firstName, lastName, email, new ArrayList<String>() {{ add("customer"); }});
    }

    public User createAdmin(String login, String password, String firstName, String lastName, String email) throws DataExistsException{
        return register(login, password, firstName, lastName, email, new ArrayList<String>() {{ add("admin"); }});
    }

    private User register(String login, String password, String firstName, String lastName, String email, List<String> role) throws DataExistsException{
        if(getUser(login) != null){
            throw new DataExistsException("User with username '"+login+"' already exists");
        }
        if(getUserByEmail(email) != null){
            throw new DataExistsException("User with email '"+email+"' already exists");
        }
        User user = new User(login, email, password, firstName, lastName, role);
        user = add(user);
        return user;
    }
}
