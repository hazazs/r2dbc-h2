package hu.hazazs.r2dbc.h2.controller;

import hu.hazazs.r2dbc.h2.entity.Person;
import hu.hazazs.r2dbc.h2.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@RestController
@RequiredArgsConstructor
public class PersonController {

    private final PersonRepository personRepository;
    private static final Random RANDOM = new Random();
    private static final int NUMBER_OF_RANDOM_PEOPLE = 3;
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

    @GetMapping("/people")
    @SuppressWarnings("unused")
    public Flux<Person> getPeople() {
        return personRepository
                .saveAll(getRandomPeople())
                .thenMany(personRepository.findAll());
    }

    private List<Person> getRandomPeople() {
        return Stream
            .generate(() -> new Person(
                    null,
                    NAMES.get(RANDOM.nextInt(NAMES.size())),
                    RANDOM.nextInt(111)))
            .limit(NUMBER_OF_RANDOM_PEOPLE)
            .toList();
    }

}