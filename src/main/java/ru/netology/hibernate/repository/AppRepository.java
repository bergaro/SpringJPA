package ru.netology.hibernate.repository;

import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.netology.hibernate.repository.PersonEntity.Person;
import ru.netology.hibernate.repository.PersonEntity.PersonPK;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.*;

@Data
@Builder
@Repository
public class AppRepository {

    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public List<Person> personTrans(String city) {
        initPersonsTable();
        return getPersonByCity(city);
    }

    protected void initPersonsTable() {
        Query query = entityManager.createQuery("select p from persons p" +
                " where p.personPK = :personPK");
        for(Person person : getPersonList()) {
            query.setParameter("personPK", person.getPersonPK());
            if(query.getResultList().isEmpty()) {
                entityManager.persist(person);
            }
        }
    }

    protected List<Person> getPersonByCity(String city) {
        Query query = entityManager.createQuery("select p from persons p " +
                "where p.cityOfLiving = :city");
        query.setParameter("city", city);
        return query.getResultList();
    }

    private List<Person> getPersonList() {
        List<Person> personList = new ArrayList<>();
        personList.add(Person.builder()
                .personPK(PersonPK.builder()
                        .name("Alexey")
                        .surname("Ivanov")
                        .age(22)
                        .build())
                .cityOfLiving("Moscow")
                .phoneNumber("1234567")
                .build());
        personList.add(Person.builder()
                .personPK(PersonPK.builder()
                        .name("Ivan")
                        .surname("Petrov")
                        .age(28)
                        .build())
                .cityOfLiving("St.Peterburg")
                .phoneNumber("1234567")
                .build());
        personList.add(Person.builder()
                .personPK(PersonPK.builder()
                        .name("Petr")
                        .surname("Petrov")
                        .age(34)
                        .build())
                .cityOfLiving("Sochi")
                .phoneNumber("1234567")
                .build());
        return personList;
    }

}
