package dev.patika.SchoolManagementSystem03.controllers.Interface;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IBaseController<T>{
    ResponseEntity<List<T>> getAll();
    ResponseEntity<T> getById(int id);
    ResponseEntity<T> updateById( T t ,int id);
    ResponseEntity<String> deleteById(int id);
    ResponseEntity<T> post(T t);


}
