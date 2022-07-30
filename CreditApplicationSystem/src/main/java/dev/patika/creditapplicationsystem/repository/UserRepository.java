package dev.patika.creditapplicationsystem.repository;

import dev.patika.creditapplicationsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User getUserByIdentityNumber(long identityNumber);
}
