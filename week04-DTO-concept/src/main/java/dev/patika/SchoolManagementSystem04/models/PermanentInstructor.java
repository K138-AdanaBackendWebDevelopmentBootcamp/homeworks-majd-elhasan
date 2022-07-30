package dev.patika.SchoolManagementSystem04.models;

import lombok.*;

import javax.persistence.Entity;
import java.util.Objects;
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
//@NoArgsConstructor
@AllArgsConstructor
public class PermanentInstructor extends Instructor{
    // we don't have to create an ID to this entity because It will use the inherited one.
    int fixed_salary;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PermanentInstructor)) return false;
        if (!super.equals(o)) return false;
        PermanentInstructor that = (PermanentInstructor) o;
        return fixed_salary == that.fixed_salary;
    }
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), fixed_salary);
    }
}
