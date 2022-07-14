package repository;

import utils.EntityManagerUtils;

import javax.persistence.EntityManager;
import java.util.List;

// CRUD : Create , Read , Update , Delete
public interface CrudRepository<T> {
    EntityManager em = EntityManagerUtils.getEntityManager("mysqlPU");
    List<T> findAll();
    T findById(int id);
    void saveToDatabase(T object);
    void deleteFromDatabase(T object);
    void deleteFromDatabaseById(int id);
    void updateOnDatabase(T object);
}
