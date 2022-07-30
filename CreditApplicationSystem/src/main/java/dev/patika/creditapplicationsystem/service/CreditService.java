package dev.patika.creditapplicationsystem.service;

import dev.patika.creditapplicationsystem.exception.NotFoundException;
import dev.patika.creditapplicationsystem.model.Credit;
import dev.patika.creditapplicationsystem.model.State;
import dev.patika.creditapplicationsystem.model.User;
import dev.patika.creditapplicationsystem.repository.CreditRepository;
import dev.patika.creditapplicationsystem.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class CreditService {
    private final UserRepository userRepository;
    private final CreditRepository creditRepository;

    public CreditService(UserRepository userRepository, CreditRepository creditRepository) {
        this.userRepository = userRepository;
        this.creditRepository = creditRepository;
    }

    public Credit askByIdentityNumber(long identityNumber) {
        User foundUser = userRepository.getUserByIdentityNumber(identityNumber);
        if(foundUser==null)
            throw new NotFoundException("Not found user with id "+identityNumber);

        Credit credit=defineCreditScore(identityNumber);
        credit.setUser(foundUser);

        setStateAndLimit(credit);

        return creditRepository.save(credit);

    }
    public Credit getCreditByUserIdentityNumber(long identityNumber){
        User foundUser = userRepository.getUserByIdentityNumber(identityNumber);
        if(foundUser==null)
            throw new NotFoundException("Not found user with id "+identityNumber);
        return foundUser.getCredit_info();
    }
    private Credit defineCreditScore(long identityNumber){
        String str_id = Long.toString(identityNumber);
        byte n= Byte.parseByte(String.valueOf(str_id.charAt(str_id.length()-1)));
        Credit credit = new Credit();
        /*
        the IDs that end by odd digits are assumed as wrong !
         */
        switch (n){
            case 0:
                credit.setCreditScore(2000);
                break;
            case 2:
                credit.setCreditScore(550);
                break;
            case 4:
                credit.setCreditScore(1000);
                break;
            case 6:
                credit.setCreditScore(400);
                break;
            case 8:
                credit.setCreditScore(900);
                break;
            default:
                credit.setCreditScore(0);
                throw new RuntimeException(identityNumber+" is WRONG ID !");
        }
        return credit;
    }
    private Credit setStateAndLimit(Credit credit){
        /*
        calculations according to the credit score
         */
        if(credit.getCreditScore()< 500)
            credit.setState(State.failure);
        else if(credit.getCreditScore()>= 500 && credit.getCreditScore()< 1000 && credit.getUser().getSalary()<5000)
        {
            credit.setState(State.success);
            credit.setCreditLimit(10_000);
        }
        else if(credit.getCreditScore()>= 500 && credit.getCreditScore()< 1000 && credit.getUser().getSalary()>=5000)
        {
            credit.setState(State.success);
            credit.setCreditLimit(20_000);
        }
        else if(credit.getCreditScore()>= 1000)
        {
            credit.setState(State.success);
            credit.setCreditLimit(credit.getUser().getSalary()* credit.getCreditLimitMultiplier());
        }
        return  credit;
    }
}
