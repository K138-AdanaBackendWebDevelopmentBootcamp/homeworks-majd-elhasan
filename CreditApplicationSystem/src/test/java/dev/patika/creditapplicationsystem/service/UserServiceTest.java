package dev.patika.creditapplicationsystem.service;

import dev.patika.creditapplicationsystem.exception.*;
import dev.patika.creditapplicationsystem.model.User;
import dev.patika.creditapplicationsystem.repository.CreditRepository;
import dev.patika.creditapplicationsystem.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.Objects;
import static org.mockito.Mockito.mock;

public class UserServiceTest {

    @MockBean
    private UserRepository Urepo = mock(UserRepository.class);
    @MockBean
    private CreditRepository Crepo = mock(CreditRepository.class);

    @Test()
    void saveUser() {
        User user = User.builder().fullName("Mecid").phoneNumber(9999999999L).identityNumber(22222222222L).salary(0).build();
        User id_n_u = new User();
        Mockito.when(Urepo.getUserByIdentityNumber(user.getIdentityNumber())).thenReturn(id_n_u);
        Mockito.when(user.getDatabaseId()==null && id_n_u!=null).thenThrow(new AlreadyExistsException("a user with identity number " + user.getIdentityNumber() + " Already exists !"));

        User theOldData = new User();
        if(user.getDatabaseId()!=null){
            Mockito.when(Urepo.findById(user.getDatabaseId()).get()).thenReturn(theOldData);
            if(id_n_u!=null){
                Mockito.when(!Objects.equals(user.getDatabaseId(), id_n_u.getDatabaseId())).thenThrow(new AlreadyExistsException("a user with identity number " + user.getIdentityNumber() + " Already exists !"));
            } else if (theOldData.getSalary() != user.getSalary() && theOldData.getCredit_info() != null) {
                Crepo.delete(theOldData.getCredit_info());
            }
        } else if (theOldData.getCredit_info() != null) {
            Crepo.delete(theOldData.getCredit_info());
        }
        Urepo.save(user);

    }
}