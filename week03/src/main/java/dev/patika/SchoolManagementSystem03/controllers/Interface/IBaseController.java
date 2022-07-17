package dev.patika.SchoolManagementSystem03.controllers.Interface;

import org.springframework.http.ResponseEntity;

import java.util.List;
// this interface contains the common used methods in the inheritor controller (sub) classes.
// this interface uses the Generic type -here referenced by 'T' - to implement it in subClasses by the type we want to use.
public interface IBaseController<T>{
    ResponseEntity<List<T>> getAll();
    ResponseEntity<T> getById(int id);
    ResponseEntity<T> updateById( T t ,int id);
    ResponseEntity<String> deleteById(int id);
    ResponseEntity<T> post(T t);


}
