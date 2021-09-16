package ru.netology.hibernate.repository;

import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Repository;
import ru.netology.hibernate.repository.PersonEntity.Person;
import java.util.*;

@Data
@Builder
@Repository
public class AppRepository {

    private final PersonRepository personRepository;

    public AppRepository(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    protected void initPersonsTable() {
        String name;
        String surname;
        for(Person person : getPersonList()) {
            name = person.getName();
            surname = person.getSurname();
            if(personRepository.findByNameAndSurname(name, surname).isEmpty()) {
                personRepository.save(person);
            }
        }
    }

    public List<Person> getPersonByCity(String city) {
        initPersonsTable(); //Only Test
        return personRepository.findByCityOfLiving(city);
    }

    public List<Person> getPersonWhoseAgeLessThan(int age) {
        initPersonsTable(); //Only Test
        return personRepository.findByAgeLessThanOrderByAgeDesc(age);
    }

    public Person getPersonByNameAndSurname(String name, String surname) {
        initPersonsTable(); //Only Test
        Person person;
        try {
            person = personRepository.findByNameAndSurname(name, surname).get();
        } catch (NoSuchElementException ex) {
            person = null;
            System.out.println("Person obj: No value present in database");
        }
        return person;
    }

    private List<Person> getPersonList() {
        List<Person> personList = new ArrayList<>();
        personList.add(Person.builder()
                .name("Alexey")
                .surname("Ivanov")
                .age(25)
                .cityOfLiving("Moscow")
                .phoneNumber("1234567")
                .build());
        personList.add(Person.builder()
                .name("Ivan")
                .surname("Petrov")
                .age(28)
                .cityOfLiving("St.Peterburg")
                .phoneNumber("1234567")
                .build());
        personList.add(Person.builder()
                .name("Petr")
                .surname("Petrov")
                .age(34)
                .cityOfLiving("Sochi")
                .phoneNumber("1234567")
                .build());
        return personList;
    }

}
