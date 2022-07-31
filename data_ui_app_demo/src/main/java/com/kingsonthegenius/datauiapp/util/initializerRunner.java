package com.kingsonthegenius.datauiapp.util;

import com.kingsonthegenius.datauiapp.model.Nationality;
import com.kingsonthegenius.datauiapp.repository.NationalityRepository;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

@Component
public class initializerRunner implements CommandLineRunner {
   private static final Logger logger = LoggerFactory.getLogger(initializerRunner.class);
   public static int number= 0;

   private final NationalityRepository repository;

    public initializerRunner(NationalityRepository repository) {
    this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        //Nationality nationality = new Nationality(1,"Syria","Damascus","Majd",)

            repository.save(Nationality.builder().name("Syria").capital("Damascus").updatedBy("Majd elhasan").updatedOn(new Date()).build());
            repository.save(Nationality.builder().name("Turkey").capital("Ankara").updatedBy("Majd elhasan").updatedOn(new Date()).build());
            repository.save(Nationality.builder().name("Iraq").capital("Baghdad").updatedBy("Majd elhasan").updatedOn(new Date()).build());
            repository.save(Nationality.builder().name("Pakistan").capital("Islamabad").updatedBy("Majd elhasan").updatedOn(new Date()).build());
            repository.save(Nationality.builder().name("China").capital("Beijing").updatedBy("Majd elhasan").updatedOn(new Date()).build());
            repository.save(Nationality.builder().name("Korea").capital("Soul").updatedBy("Majd elhasan").updatedOn(new Date()).build());
            repository.save(Nationality.builder().name("Egypt").capital("Cairo").updatedBy("Majd elhasan").updatedOn(new Date()).build());
            repository.save(Nationality.builder().name("America").capital("washington").updatedBy("Majd elhasan").updatedOn(new Date()).build());
            repository.save(Nationality.builder().name("Spain").capital("Madrid").updatedBy("Majd elhasan").updatedOn(new Date()).build());

            repository.findAll().forEach(O -> logger.info("{}", O));

    }
}
