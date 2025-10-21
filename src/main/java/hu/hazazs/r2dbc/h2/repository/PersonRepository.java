package hu.hazazs.r2dbc.h2.repository;

import hu.hazazs.r2dbc.h2.entity.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

}