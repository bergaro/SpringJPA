package ru.netology.hibernate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.netology.hibernate.repository.PersonEntity.Person;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findByCityOfLiving(String city);
    List<Person> findByAgeLessThanOrderByAgeDesc(Integer Age);
    Optional<Person> findByNameAndSurname(String name, String surname);
}
