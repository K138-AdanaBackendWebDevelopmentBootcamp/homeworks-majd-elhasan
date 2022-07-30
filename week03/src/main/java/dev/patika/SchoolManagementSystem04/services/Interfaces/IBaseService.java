package dev.patika.SchoolManagementSystem04.services.Interfaces;

import java.util.List;
// this interface contains the common used methods in the inheritor service (sub) classes.
// this interface uses the Generic type -here referenced by 'T' - to implement it in subClasses by the type we want to use.
public interface IBaseService<T> {
    List<T> findAll();
    T findById(int id);
    T save(T object);
    void deleteById(int id);
    T update(T object,int id);
}
