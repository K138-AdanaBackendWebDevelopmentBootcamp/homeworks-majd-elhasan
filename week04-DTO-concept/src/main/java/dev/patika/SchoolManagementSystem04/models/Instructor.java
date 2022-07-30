package dev.patika.SchoolManagementSystem04.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
//@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public abstract class Instructor {
    //by this @Id annotation spring boot will generate an id to be the primary Key of the object(entity) in the database
    // and By the annotation @GeneratedValue(strategy = GenerationType.IDENTITY)
    // we can specify GenerationType 'TABLE','SEQUENCE','IDENTITY',and we can leave the selection to spring boot by making the type 'AUTO'.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    protected String name;
    protected String address;
    protected long phoneNumber;
    @OneToMany(mappedBy = "instructor")
    @ToString.Exclude
    protected List<Course> courseList = new ArrayList<>();
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Instructor)) return false;
        Instructor that = (Instructor) o;
        return phoneNumber == that.phoneNumber && Objects.equals(name, that.name);
    }
    @Override
    public int hashCode() {
        return Objects.hash(name,  phoneNumber);
    }

}
