package fi.jyu.tietokonekauppa.services;

import fi.jyu.tietokonekauppa.models.User;
import fi.jyu.tietokonekauppa.repositories.UserRepository;
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
}
