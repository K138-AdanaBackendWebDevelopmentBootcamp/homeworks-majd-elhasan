package dev.patika.creditapplicationsystem.service;

import dev.patika.creditapplicationsystem.exception.AlreadyExistsException;
import dev.patika.creditapplicationsystem.exception.NotFoundException;
import dev.patika.creditapplicationsystem.model.User;
import dev.patika.creditapplicationsystem.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class UserService{

    private final   UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    
    public User getUserByDatabaseId(long databaseId) {
        if(userRepository.findById(databaseId).isPresent())
            return userRepository.findById(databaseId).orElse(null);
        else throw new NotFoundException("Not found user with id "+databaseId);
    }

    
    public User getUserByIdentityNumber(long identityNumber) {
        if(userRepository.getUserByIdentityNumber(identityNumber)!=null)
        return userRepository.getUserByIdentityNumber(identityNumber);
        else throw new NotFoundException("Not found user with id "+identityNumber);
    }

    
    public User saveUser(User user) {
        User foundUser = userRepository.getUserByIdentityNumber(user.getIdentityNumber());
        if(foundUser!=null)
           throw new AlreadyExistsException("a user with identity number "+user.getIdentityNumber()+" Already exists !");
        return userRepository.save(user);
    }
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    
    public User updateUserByDatabaseId(long id) {
       User user = userRepository.findById(id).orElseThrow(()->new NotFoundException("a user with identity number: "+id+" Not found !"));
        return userRepository.save(user);
    }

    
    public void deleteUserByDatabaseId(long id) {
        userRepository.findById(id).orElseThrow(()->new NotFoundException("a user with database Id: "+id+" Not found !"));
        userRepository.deleteById(id);
    }

    ///// public get credit score
}
