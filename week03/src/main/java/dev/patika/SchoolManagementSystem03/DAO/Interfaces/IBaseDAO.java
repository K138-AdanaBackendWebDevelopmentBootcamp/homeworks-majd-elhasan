package dev.patika.SchoolManagementSystem03.DAO.Interfaces;

import java.util.List;
public interface IBaseDAO<T> {
    List<T> findAll();
    T findById(int id);
    T save(T object);
    void deleteById(int id);
    T update(T object,int id);
}
