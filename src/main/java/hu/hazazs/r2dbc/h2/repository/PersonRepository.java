package hu.hazazs.r2dbc.h2.repository;

import hu.hazazs.r2dbc.h2.entity.Person;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface PersonRepository extends ReactiveCrudRepository<Person, String> {

    @Modifying
    @Query("INSERT INTO person (id, name, age) VALUES (:id, :name, :age)")
    Mono<Void> insertPerson(String id, String name, int age);

}