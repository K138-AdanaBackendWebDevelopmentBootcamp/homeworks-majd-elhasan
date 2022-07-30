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
public class VisitingResearcher extends Instructor {
    // we don't have to create an ID to this entity because It will use the inherited one.
    int hourly_salary;

     @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VisitingResearcher)) return false;
        if (!super.equals(o)) return false;
        VisitingResearcher that = (VisitingResearcher) o;
        return hourly_salary == that.hourly_salary;
    }
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), hourly_salary);
    }
}
