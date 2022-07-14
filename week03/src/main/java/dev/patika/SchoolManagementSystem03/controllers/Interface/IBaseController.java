package dev.patika.SchoolManagementSystem03.controllers.Interface;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IBaseController<T>{
    ResponseEntity<List<T>> getAll();
    ResponseEntity<T> get(int id);
    ResponseEntity<T> update( int id, T t);
    ResponseEntity<String> delete(int id);
    ResponseEntity<T> post(T t);


}
