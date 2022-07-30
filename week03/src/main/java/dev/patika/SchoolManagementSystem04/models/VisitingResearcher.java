package dev.patika.SchoolManagementSystem04.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.Objects;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisitingResearcher extends Instructor{
    // we don't have to create an ID to this entity because It will use the inherited one.
    int hourly_salary;

}
