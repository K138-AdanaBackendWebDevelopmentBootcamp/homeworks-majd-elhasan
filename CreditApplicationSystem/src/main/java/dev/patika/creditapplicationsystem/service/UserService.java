package dev.patika.creditapplicationsystem.service;

import dev.patika.creditapplicationsystem.exception.*;
import dev.patika.creditapplicationsystem.model.User;
import dev.patika.creditapplicationsystem.repository.CreditRepository;
import dev.patika.creditapplicationsystem.repository.UserRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

@Service
public class UserService{
    public static String budgetUpdatedInfo= "";
    private final   UserRepository userRepository;
    private final CreditRepository creditRepository;
    List<User> userList;

    public UserService(UserRepository userRepository, CreditRepository creditRepository) {
        this.userRepository = userRepository;
        this.creditRepository = creditRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }
    public List<User> getUsersSorted(String sortBy,Boolean direction){
        switch (sortBy){
            case "fullName":
            case "identityNumber":
            case "salary":
            case"phoneNumber" :
            case"databaseId" :
                this.userList = userRepository.findAll(Sort.by(direction?Sort.Direction.ASC:Sort.Direction.DESC,sortBy));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + sortBy);
        }
        return userList;
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

    
    public User saveUser(User user) {
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
            // here we get the user data with a database ID , throw the frontend side by form structure has hidden ID field
            System.out.println("****************************************************************\n" +
                    "the posted user does have Database ID ,so it's updating process\n" +
                    "****************************************************************");
            // if the user that found by identity number does exist ,there will be two possibilities
            // either the Identity number of the user we are trying to update didn't change or another user has the same Identity number that we are trying to assign to the current user
            // ,so we have to compare the db IDs of them ,if the IDs are the same that means that the first possibility occurs [the Identity number of the user we are trying to update didn't change]
            // else if the IDs are NOT the same that means that the second possibility occurs [another user has the same Identity number that we are trying to assign to the current user]
            User theOldData = userRepository.findById(user.getDatabaseId()).get();
            if (foundUserByIdentityNumber != null) {  // there is someone uses the Identity number perhaps  the posted user uses it (and that happens when we don't change theirs) , perhaps another user uses it.
                if (!Objects.equals(user.getDatabaseId(), foundUserByIdentityNumber.getDatabaseId())) // the second possibility occurs here ...  the other one uses the Identity number not the posted USER.
                    throw new AlreadyExistsException("a user with identity number " + user.getIdentityNumber() + " Already exists !");
                // ↓  here we'll handle the posted USER data
                //*********************************************************
                //I have to delete the Credit_info data from database  :)
                //*********************************************************/
                // let's discover what has changed !
                else if(theOldData.getSalary()!=user.getSalary() && theOldData.getCredit_info()!=null){
                    creditRepository.delete(theOldData.getCredit_info()); // I shouldn't say " delete(user.getCredit_info()" here cause the user posted object doesn't have Credit_info in it
                    // so I delete the credit object that related to theOldData object which it recently created by the reference of the user database ID .
                    //throw new BudgetUpdatedInfo("This user's credit data have to be updated because of changing occurred in user's salary !");
                    budgetUpdatedInfo = "This user's credit data have to be updated because of changing occurred in user's salary !";
                }
            }else { // so no one uses the newly posted USER object's Identity number ,so since we define the credit score according to the Identity number we have to remove the old credit data that related to the old Identity number
                // in this case let's remove the credit from the database.
                if(theOldData.getCredit_info()!=null) {
                    creditRepository.delete(theOldData.getCredit_info());
                    //throw new BudgetUpdatedInfo("This user's credit data have to be updated because of changing occurred in user's identity number !");
                    budgetUpdatedInfo = "This user's credit data have to be updated because of changing occurred in user's identity number !";
                }
            }
        }
        // validation for user ID and throwing an exception for some reason :)

        validation(user);
        return userRepository.save(user);
    }

    public User updateUserByDatabaseId(long id,User user){
       User found = userRepository.findById(id).orElseThrow(()->new NotFoundException("a user with identity number: "+id+" Not found !"));
       validation(user);
       found.setCredit_info(user.getCredit_info());
       found.setFullName(user.getFullName());
       found.setIdentityNumber(user.getIdentityNumber());
       found.setSalary(user.getSalary());
       found.setPhoneNumber(user.getPhoneNumber());
       if(userRepository.getUserByIdentityNumber(user.getIdentityNumber()).getDatabaseId()!=found.getDatabaseId())
           throw new AlreadyExistsException("a user with identity number " + user.getIdentityNumber() + " Already exists !");
       return userRepository.save(found); // no need to save because it will be saved automatically because of using set methods on data fetched from JPA repository
    }

    
    public void deleteUserByDatabaseId(long id) throws NotFoundException{
        userRepository.findById(id).orElseThrow(()->new NotFoundException("a user with database Id: "+id+" Not found !"));
        userRepository.deleteById(id);
    }
    private void validation(User user){
        if(user.getIdentityNumber()%2==1) throw new Invalid_ID_NumberException("the user's ID number is NOT VALID number it must end with even digit !    :)");

        if (user.getIdentityNumber()<10000000000L || user.getIdentityNumber()>99999999999L)
            throw new IdentityNumber11digitException("Identity number must have 11 digit like : 12345678901");
        if(Objects.equals(user.getFullName(), ""))
            throw new FullnameEmptyException("Full name should have at least one character");
        if (user.getPhoneNumber()<1000000000|| user.getPhoneNumber()>9999999999L)
            throw new PhoneNumber10digitException("Phone number must have 10 digit like : 1234567890");
    }
}
