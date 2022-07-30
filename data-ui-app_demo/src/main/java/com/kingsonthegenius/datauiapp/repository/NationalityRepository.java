package com.kingsonthegenius.datauiapp.repository;

import com.kingsonthegenius.datauiapp.model.Nationality;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NationalityRepository extends CrudRepository<Nationality,Integer> {
}
