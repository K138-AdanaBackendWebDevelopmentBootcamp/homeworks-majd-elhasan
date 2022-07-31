package com.kingsonthegenius.datauiapp.service;

import com.kingsonthegenius.datauiapp.model.Nationality;
import com.kingsonthegenius.datauiapp.repository.NationalityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NationalityService {
    private final NationalityRepository repository;

    public NationalityService(NationalityRepository repository) {
        this.repository = repository;
    }

    public List<Nationality> findAllNationalities(){
        return (List<Nationality>) repository.findAll();
    }
    public Optional<Nationality> getNationalityById(int id){
        return repository.findById(id);
    }
    public void addNationality(Nationality entity){
        repository.save(entity);
    }
    public void updateNationality(Nationality entity){
        repository.save(entity);
    }
    public void deleteNationalityById(Integer id){
        repository.deleteById(id);
    }
}
