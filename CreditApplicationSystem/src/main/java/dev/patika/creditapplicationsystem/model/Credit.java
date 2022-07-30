package dev.patika.creditapplicationsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class Credit {
    @Id
    private Long id ;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    @JsonIgnore  // we have to use Json ignore in one entity to avoid circular dependency,
    // so in the user side I will not use that to be able to get the Credit_info data from user.
    private User user;

    public int creditLimitMultiplier = 4;   // this value set as default multiplier number
    private State state;
    private int creditScore;
    private int creditLimit;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Credit credit = (Credit) o;
        return id != null && Objects.equals(id, credit.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

