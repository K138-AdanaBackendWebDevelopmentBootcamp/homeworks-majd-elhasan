package dev.patika.creditapplicationsystem.service;

import dev.patika.creditapplicationsystem.exception.AlreadyExistsException;
import dev.patika.creditapplicationsystem.exception.BudgetUpdatedInfo;
import dev.patika.creditapplicationsystem.exception.Invalid_ID_NumberException;
import dev.patika.creditapplicationsystem.exception.NotFoundException;
import dev.patika.creditapplicationsystem.model.User;
import dev.patika.creditapplicationsystem.repository.CreditRepository;
import dev.patika.creditapplicationsystem.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class UserService{

    private final   UserRepository userRepository;
    private final CreditRepository creditRepository;

    public UserService(UserRepository userRepository, CreditRepository creditRepository) {
        this.userRepository = userRepository;
        this.creditRepository = creditRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    
    public User getUserByDatabaseId(long databaseId) throws NotFoundException {
        if(userRepository.findById(databaseId).isPresent()) {
            return  userRepository.findById(databaseId).orElse(null);
        }
        else throw new NotFoundException("User NOT FOUND with database id "+databaseId);
    }

    
    public User getUserByIdentityNumber(long identityNumber) throws NotFoundException{
        if(userRepository.getUserByIdentityNumber(identityNumber)!=null)
        return userRepository.getUserByIdentityNumber(identityNumber);
        else throw new NotFoundException("User NOT FOUND with national id "+identityNumber);
    }

    
    public User saveUser(User user) throws Invalid_ID_NumberException,AlreadyExistsException,BudgetUpdatedInfo{
        User foundUserByIdentityNumber = userRepository.getUserByIdentityNumber(user.getIdentityNumber());
        // the reason I set new user according to the Identity number not the database id is the newly posted user in saving process won't have a database id
        if(user.getDatabaseId()==null) {
            System.out.println("****************************************************************\n" +
                                "the posted user doesn't have Database ID ,so it's saving process\n" +
                                "****************************************************************");
            if(foundUserByIdentityNumber!= null)
                throw new AlreadyExistsException("a user with identity number " + user.getIdentityNumber() + " Already exists !");
        }
        else {
            System.out.println("****************************************************************\n" +
                    "the posted user does have Database ID ,so it's updating process\n" +
                    "****************************************************************");
            // if the user that found by identity number does exist ,there will be two possibilities
            // either the Identity number of the user we are trying to update didn't change or another user has the same Identity number that we are trying to assign to the current user
            // ,so we have to compare the db IDs of them ,if the IDs are the same that means that the first possibility occurs [the Identity number of the user we are trying to update didn't change]
            // else if the IDs are NOT the same that means that the second possibility occurs [another user has the same Identity number that we are trying to assign to the current user]
            User theOldData = userRepository.findById(user.getDatabaseId()).get();
            if (foundUserByIdentityNumber != null) {  // there is someone uses the Identity number perhaps  the posted user uses it (and that happens when we don't change theirs) , perhaps another user uses it.
                if (user.getDatabaseId() != foundUserByIdentityNumber.getDatabaseId()) // the second possibility occurs here ...  the other one uses the Identity number not the posted USER.
                    throw new AlreadyExistsException("a user with identity number " + user.getIdentityNumber() + " Already exists !");
                // ↓  here we'll handle the posted USER data
                //*********************************************************
                //I have to delete the Credit_info data from database  :)
                //*********************************************************/
                // let's discover what has changed !
                if(theOldData.getSalary()!=user.getSalary() && theOldData.getCredit_info()!=null){
                    creditRepository.delete(theOldData.getCredit_info()); // I shouldn't say " delete(user.getCredit_info()" here cause the user posted object doesn't have Credit_info in it
                    // so I delete the credit object that related to theOldData object which it recently created by the reference of the user database ID .
                }
            }else { // so no one uses the newly posted USER object's Identity number ,so since we define the credit score according to the Identity number we have to remove the old credit data that related to the old Identity number
                // in this case let's remove the credit from the database.
                if(theOldData.getCredit_info()!=null)
                    creditRepository.delete(theOldData.getCredit_info());
            }
        }
        // validation for user ID and throwing an exception for some reason :)
        if(user.getIdentityNumber()%2==1) throw new Invalid_ID_NumberException("the user's ID number is NOT VALID number it must end with even number !  :) ");
        return userRepository.save(user);
    }

    public User updateUserByDatabaseId(long id) throws NotFoundException {
       User user = userRepository.findById(id).orElseThrow(()->new NotFoundException("a user with identity number: "+id+" Not found !"));
        return userRepository.save(user);
    }

    
    public void deleteUserByDatabaseId(long id) throws NotFoundException{
        userRepository.findById(id).orElseThrow(()->new NotFoundException("a user with database Id: "+id+" Not found !"));
        userRepository.deleteById(id);
    }
}
