package ru.netology.hibernate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.netology.hibernate.repository.PersonEntity.Person;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("select p from persons p where p.cityOfLiving = :city")
    List<Person> findByCity(@Param("city") String city);

    @Query("select p from persons p where p.age < :age order by p.age desc")
    List<Person> findByAgeLessThan(@Param("age") Integer age);

    @Query("select p from persons p where p.name = :name and p.surname = :surname")
    Optional<Person> findByNameAndSurname(@Param("name") String name,
                                          @Param("surname") String surname);
}
