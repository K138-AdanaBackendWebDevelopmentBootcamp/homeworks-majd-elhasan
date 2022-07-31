package dev.patika.creditapplicationsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long databaseId;

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    //@JsonIgnore  look at the Credit side first
    // so here I shouldn't use @JsonIgnore annotation.
    private Credit credit_info;

    private long identityNumber;
    private String fullName;
    private int salary;
    private long phoneNumber;


    public  User(){}
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return databaseId != null && Objects.equals(databaseId, user.databaseId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
