package hu.hazazs.r2dbc.h2.controller;

import hu.hazazs.r2dbc.h2.entity.Person;
import hu.hazazs.r2dbc.h2.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class PersonController {

    private final PersonRepository personRepository;
    private static final Random RANDOM = new Random();
    private static final List<String> NAMES = List.of(
                "Tibor",
                "Anita",
                "Bea",
                "Vince",
                "Edina",
                "László",
                "Margit",
                "Csaba",
                "Antal",
                "Viktória"
            );

    @GetMapping("/random-person")
    @SuppressWarnings("unused")
    public Mono<Person> getRandomPerson() {
        Person randomPerson = new Person(UUID.randomUUID().toString(), NAMES.get(RANDOM.nextInt(NAMES.size())), RANDOM.nextInt(111));

        return personRepository
                .deleteAll()
                .then(personRepository.insertPerson(randomPerson.getId(), randomPerson.getName(), randomPerson.getAge()))
                .thenReturn(randomPerson);
    }

}