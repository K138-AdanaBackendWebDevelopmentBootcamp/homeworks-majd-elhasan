package dev.patika.creditapplicationsystem.repository;

import dev.patika.creditapplicationsystem.model.Credit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditRepository extends JpaRepository<Credit, Long> {
}
