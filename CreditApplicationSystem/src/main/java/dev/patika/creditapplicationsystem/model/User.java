package dev.patika.creditapplicationsystem.model;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
@Table(name = "_user_")
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

    @Range(min = 10000000000L,max = 99999999999L)
    private long identityNumber;

    @NotBlank
    private String fullName;

    @Range()
    private int salary;

    @Range(min = 1000000000L ,max = 9999999999L)
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
